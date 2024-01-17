package com.xunmaw.job.mapper;

import com.xunmaw.job.entity.Type;

import java.util.List;

public interface TypeMapper {

	int create(Type type);

	int delete(Integer id);

	int update(Type type);

	List<Type> query(Type type);

	Type detail(Integer id);

	int count(Type type);
}
