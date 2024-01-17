package com.xunmaw.job.controller;

import com.xunmaw.job.entity.Menu;
import com.xunmaw.job.service.MenuService;
import com.xunmaw.job.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.xunmaw.job.utils.Result;
import com.xunmaw.job.vo.UserData;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private UserDataService userDataService;

    @PostMapping("/create")
    public Result create(@RequestBody Menu menu) {
        int flag = menuService.create(menu);
        if (flag > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @PostMapping("/delete")
    public Result delete(String ids) {
        int flag = menuService.delete(ids);
        if (flag > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @PostMapping("/update")
    public Result update(@RequestBody Menu menu) {
        int flag = menuService.update(menu);
        if (flag > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @PostMapping("/detail")
    public Result detail(Integer id) {
        return Result.success(menuService.detail(id));
    }

    @PostMapping("/query")
    public Result query() {
        Menu menu = new Menu();
        //获取当前登录用户的用户信息（从redis中获取）
        UserData userData = userDataService.getUser();
        if(userData != null) {
            menu.setType(userData.getType());
            List<Menu> list = menuService.query(menu);
            return Result.success(list);
        } else {
            return Result.error();
        }
    }

}
