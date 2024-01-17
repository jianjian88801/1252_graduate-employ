package com.xunmaw.job.front;

import com.github.pagehelper.PageInfo;
import com.xunmaw.job.service.PostService;
import com.xunmaw.job.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/f/post")
public class PostFrontController {

    @Autowired
    private PostService postService;

    @PostMapping("/query")
    public Map<String,Object> getPostList(@RequestBody Map<String,Object> map) {
        PageInfo<Map<String, Object>> postList = postService.getPostList(map);
        return Result.success(postList);
    }
}
