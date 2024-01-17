package com.xunmaw.job.controller;

import com.github.pagehelper.PageInfo;
import com.xunmaw.job.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.xunmaw.job.entity.Type;
import com.xunmaw.job.utils.Result;

import java.util.Map;

@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @PostMapping("/create")
    public Result create(@RequestBody Type type) {
        int flag = typeService.create(type);
        if (flag > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @PostMapping("/delete")
    public Result delete(String ids) {
        int flag = typeService.delete(ids);
        if (flag > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @PostMapping("/update")
    public Result update(@RequestBody Type type) {
        int flag = typeService.update(type);
        if (flag > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @PostMapping("/detail")
    public Result detail(Integer id) {
        return Result.success(typeService.detail(id));
    }

    @PostMapping("/query")
    public Map<String, Object> query(@RequestBody Type type) {
        PageInfo<Type> pageInfo = typeService.query(type);
        return Result.success(pageInfo);
    }

}
