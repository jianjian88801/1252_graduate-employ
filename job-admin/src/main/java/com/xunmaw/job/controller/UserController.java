package com.xunmaw.job.controller;

import com.github.pagehelper.PageInfo;
import com.xunmaw.job.framework.role.RequiresRoles;
import com.xunmaw.job.framework.role.Role;
import com.xunmaw.job.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.xunmaw.job.entity.User;
import com.xunmaw.job.utils.Result;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public Result create(@RequestBody User user) {
        int row = userService.create(user);
        if (row > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @PostMapping("/delete")
    public Result delete(String ids) {
        int row = userService.delete(ids);
        if (row > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        int row = userService.update(user);
        if (row > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @PostMapping("/query")
    @RequiresRoles(type = Role.ADMIN)
    public Map<String, Object> query(@RequestBody User user) {
        PageInfo<User> pageInfo = userService.query(user);
        return Result.success(pageInfo);
    }

    @PostMapping("/detail")
    public Result detail(Integer id) {
        User user = userService.detail(id);
        return Result.success(user);
    }
}
