package com.xunmaw.job.mapper;

import com.xunmaw.job.entity.Article;

import java.util.List;

public interface ArticleMapper {

	int create(Article article);

	int delete(Integer id);

	int update(Article article);

	List<Article> query(Article article);

	Article detail(Integer id);

	int count(Article article);

	List<Article> getArticlesByChannelId(Integer channelId);
}
