package com.xunmaw.job.mapper;

import com.xunmaw.job.entity.Menu;

import java.util.List;

public interface MenuMapper {

	int create(Menu menu);

	int delete(Integer id);

	int update(Menu menu);

	List<Menu> query(Menu menu);

	Menu detail(Integer id);

	int count(Menu menu);
}
