package com.xunmaw.job.mapper;

import com.xunmaw.job.entity.User;

import java.util.List;

public interface UserMapper {

    int create(User user);

    int delete(Integer id);

    int update(User user);

    List<User> query(User user);

    int count(User user);

    User detail(Integer id);

}
