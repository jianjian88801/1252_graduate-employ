package com.xunmaw.job.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xunmaw.job.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.xunmaw.job.entity.User;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    
    public int create(User user) {
        return userMapper.create(user);
    }

    public int delete(String ids) {
        int row = 0;
        String[] arr = ids.split(",");
        for (String id : arr) {
            if (!StringUtils.isEmpty(id)) {
                userMapper.delete(Integer.parseInt(id));
                row++;
            }
        }
        return row;
    }

    public int delete(Integer id) {
        return userMapper.delete(id);
    }

    public int update(User user) {
        return userMapper.update(user);
    }

    public PageInfo<User> query(User user) {
        if (user != null && user.getPage() != null) {
            PageHelper.startPage(user.getPage(), user.getLimit());
        }
        List<User> list = userMapper.query(user);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public User detail(Integer id) {
        return userMapper.detail(id);
    }

    public int count(User user) {
        return userMapper.count(user);
    }

    public User login(String userName, String password) {
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        List<User> list = userMapper.query(user);
        if(list != null && list.size() >= 1) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
