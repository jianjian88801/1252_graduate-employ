package com.xunmaw.job.mapper;

import com.xunmaw.job.entity.Company;

import java.util.List;

public interface CompanyMapper {

	int create(Company company);

	int delete(Integer id);

	int update(Company company);

	List<Company> query(Company company);

	Company detail(Integer id);

	int count(Company company);
}
