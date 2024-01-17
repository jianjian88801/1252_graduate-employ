package com.xunmaw.job.front;

import com.github.pagehelper.PageInfo;
import com.xunmaw.job.service.ArticleService;
import com.xunmaw.job.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.xunmaw.job.entity.Article;

import java.util.Map;

@RestController
@RequestMapping("/f/article")
public class ArticleFrontController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/detail")
    public Result detail(Integer id) {
        //每点击一次浏览量加一（每调用一次方法，浏览量加一）
        Double views = redisTemplate.opsForZSet().incrementScore("views", id, 1);
        Article article = articleService.detail(id);
        if(views != null) {
            article.setViews(views.intValue());
        } else {
            article.setViews(0);
        }
        return Result.success(article);
    }

    @PostMapping("/query")
    public Map<String, Object> query(@RequestBody Article article) {
        PageInfo<Article> pageInfo = articleService.getArticlesByChannelId(article);
        return Result.success(pageInfo);
    }


}
