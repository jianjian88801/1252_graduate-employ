package com.xunmaw.job.mapper;

import com.xunmaw.job.entity.Resume;

import java.util.List;

public interface ResumeMapper {

	int create(Resume resume);

	int delete(Integer id);

	int update(Resume resume);

	List<Resume> query(Resume resume);

	Resume detail(Integer id);

	int count(Resume resume);
}
