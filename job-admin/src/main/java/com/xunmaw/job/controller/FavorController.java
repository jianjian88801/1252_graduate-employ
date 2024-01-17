package com.xunmaw.job.controller;

import com.github.pagehelper.PageInfo;
import com.xunmaw.job.entity.Favor;
import com.xunmaw.job.service.*;
import com.xunmaw.job.utils.Result;
import com.xunmaw.job.vo.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/favor")
public class FavorController {

    @Autowired
    private FavorService favorService;
    @Autowired
    private UserDataService userDataService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private PostService postService;


    @PostMapping("/create")
    public Result create(@RequestBody Map<String,Integer> map) {
        UserData userData = userDataService.getUser();
        //1、判断用户是否登录
        if(userData == null || userData.getType() != 2) {
            Result.success("非法登录不能操作");
        }
        Integer postId = map.get("postId");
        Integer companyId = map.get("companyId");
        //2、判断是否重复收藏
        Favor favorParam = new Favor();
        favorParam.setStudentId(userData.getId());
        favorParam.setPostId(postId);
        favorParam.setCompanyId(companyId);
        PageInfo<Favor> favorPageInfo = favorService.query(favorParam);
        if(favorPageInfo.getList() != null && favorPageInfo.getList().size() > 0) {
            return Result.error("请勿重复收藏职位");
        }
        //构建send对象
        Favor favor = new Favor();
        favor.setPostId(postId);
        favor.setCompanyId(companyId);
        favor.setFavorDate(new Date());
        favor.setStudentId(userData.getId());
        //3、进行保存操作
        int row = favorService.create(favor);
        if(row > 0) {
            return Result.success();
        } else {
            return Result.error("操作失败");
        }
    }

    @PostMapping("/delete")
    public Result delete(String ids) {
        int flag = favorService.delete(ids);
        if (flag > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @PostMapping("/update")
    public Result update(@RequestBody Favor favor) {
        int flag = favorService.update(favor);
        if (flag > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @PostMapping("/detail")
    public Result detail(Integer id) {
        return Result.success(favorService.detail(id));
    }

    @PostMapping("/query")
    public Map<String, Object> query(@RequestBody Favor favor) {
        PageInfo<Favor> pageInfo = favorService.query(favor);
        pageInfo.getList().forEach(item -> {
            item.setCompany(companyService.detail(item.getCompanyId()));
            item.setStudent(studentService.detail(item.getStudentId()));
            item.setPost(postService.detail(item.getPostId()));
        });
        return Result.success(pageInfo);
    }

}
