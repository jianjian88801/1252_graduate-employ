package com.xunmaw.job.mapper;

import com.xunmaw.job.entity.Post;

import java.util.List;
import java.util.Map;

public interface PostMapper {

	int create(Post post);

	int delete(Integer id);

	int update(Post post);

	List<Post> query(Post post);

	Post detail(Integer id);

	int count(Post post);

	//根据公司查询公司对应的职位信息
	List<Map<String,Object>> getPostList(Map<String,Object> map);
}
