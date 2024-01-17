package com.xunmaw.job.mapper;

import com.xunmaw.job.entity.UserMenu;

import java.util.List;

public interface UserMenuMapper {

	int create(UserMenu userMenu);

	int delete(Integer id);

	int update(UserMenu userMenu);

	List<UserMenu> query(UserMenu userMenu);

	UserMenu detail(Integer id);

	int count(UserMenu userMenu);
}
