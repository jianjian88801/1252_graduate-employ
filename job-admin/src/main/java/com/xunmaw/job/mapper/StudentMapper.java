package com.xunmaw.job.mapper;

import com.xunmaw.job.entity.Student;

import java.util.List;

public interface StudentMapper {

	int create(Student student);

	int delete(Integer id);

	int update(Student student);

	List<Student> query(Student student);

	Student detail(Integer id);

	int count(Student student);
}
