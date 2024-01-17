package com.xunmaw.job.mapper;

import com.xunmaw.job.entity.Train;

import java.util.List;

public interface TrainMapper {

	int create(Train train);

	int delete(Integer id);

	int update(Train train);

	List<Train> query(Train train);

	Train detail(Integer id);

	int count(Train train);
}
