package com.xunmaw.job.mapper;

import com.xunmaw.job.entity.Profession;

import java.util.List;

public interface ProfessionMapper {

	int create(Profession profession);

	int delete(Integer id);

	int update(Profession profession);

	List<Profession> query(Profession profession);

	Profession detail(Integer id);

	int count(Profession profession);
}
