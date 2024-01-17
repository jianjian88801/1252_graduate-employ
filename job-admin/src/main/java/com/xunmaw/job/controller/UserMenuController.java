package com.xunmaw.job.controller;

import com.github.pagehelper.PageInfo;
import com.xunmaw.job.entity.UserMenu;
import com.xunmaw.job.service.UserMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.xunmaw.job.utils.Result;

import java.util.Map;

@RestController
@RequestMapping("/userMenu")
public class UserMenuController {

    @Autowired
    private UserMenuService userMenuService;

    @PostMapping("/create")
    public Result create(@RequestBody UserMenu userMenu) {
        int flag = userMenuService.create(userMenu);
        if (flag > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @PostMapping("/delete")
    public Result delete(String ids) {
        int flag = userMenuService.delete(ids);
        if (flag > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @PostMapping("/update")
    public Result update(@RequestBody UserMenu userMenu) {
        int flag = userMenuService.update(userMenu);
        if (flag > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @PostMapping("/detail")
    public Result detail(Integer id) {
        return Result.success(userMenuService.detail(id));
    }

    @PostMapping("/query")
    public Map<String, Object> query(@RequestBody UserMenu userMenu) {
        PageInfo<UserMenu> pageInfo = userMenuService.query(userMenu);
        return Result.success(pageInfo);
    }

}
