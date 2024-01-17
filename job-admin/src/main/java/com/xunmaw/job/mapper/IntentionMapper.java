package com.xunmaw.job.mapper;

import com.xunmaw.job.entity.Intention;

import java.util.List;

public interface IntentionMapper {

	int create(Intention intention);

	int delete(Integer id);

	int update(Intention intention);

	List<Intention> query(Intention intention);

	Intention detail(Integer id);

	int count(Intention intention);
}
