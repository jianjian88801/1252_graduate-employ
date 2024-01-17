package com.xunmaw.job.controller;

import com.github.pagehelper.PageInfo;
import com.xunmaw.job.entity.Resume;
import com.xunmaw.job.entity.Send;
import com.xunmaw.job.framework.redis.RedisUtil;
import com.xunmaw.job.framework.role.RequiresRoles;
import com.xunmaw.job.framework.role.Role;
import com.xunmaw.job.service.*;
import com.xunmaw.job.utils.Result;
import com.xunmaw.job.utils.UserThreadLocal;
import com.xunmaw.job.vo.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/send")
public class SendController {

    @Autowired
    private SendService sendService;
    @Autowired
    private UserDataService userDataService;
    @Autowired
    private ResumeService resumeService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private PostService postService;
    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/create")
    public Result create(@RequestBody Map<String, Integer> map) {
        UserData userData = userDataService.getUser();
        //1、判断用户是否登录
        if (userData == null || userData.getType() != 2) {
            Result.success("非法登录不能操作");
        }
        Integer postId = map.get("postId");
        Integer companyId = map.get("companyId");
        //2、判断是否重复申请职位
        Send sendParam = new Send();
        sendParam.setStudentId(userData.getId());
        sendParam.setPostId(postId);
        sendParam.setCompanyId(companyId);
        PageInfo<Send> sendPageInfo = sendService.query(sendParam);
        if (sendPageInfo.getList() != null && sendPageInfo.getList().size() > 0) {
            return Result.error("请勿重复申请职位");
        }
        //构建send对象
        Send send = new Send();
        send.setPostId(postId);
        send.setCompanyId(companyId);
        send.setSendDate(new Date());
        send.setStatus(0);
        send.setStudentId(userData.getId());
        //3、查询学生简历，判断简历是否开放
        Resume param = new Resume();
        param.setStudentId(userData.getId());
        param.setStatus(1);
        PageInfo<Resume> pageInfo = resumeService.query(param);
        if (pageInfo.getList() != null && pageInfo.getList().size() > 0) {
            Resume resume = pageInfo.getList().get(0);
            send.setResumeId(resume.getId());
        } else {
            Result.error("请先完善简历");
        }
        //4、进行保存操作
        int row = sendService.create(send);
        if (row > 0) {
            return Result.success("操作成功");
        } else {
            return Result.error("操作失败");
        }
    }

    @PostMapping("/delete")
    public Result delete(String ids) {
        int flag = sendService.delete(ids);
        if (flag > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @PostMapping("/update")
    public Result update(@RequestBody Send send) {
        int flag = sendService.update(send);
        if (flag > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @PostMapping("/detail")
    public Result detail(Integer id) {
        return Result.success(sendService.detail(id));
    }

    @RequiresRoles(type = Role.COMPANY)
    @PostMapping("query")
    public Map<String, Object> query(@RequestBody Send send) {
        String token = UserThreadLocal.get();
        UserData userData = (UserData) redisUtil.get(token);
        send.setCompanyId(userData.getId());
        PageInfo<Send> pageInfo = sendService.query(send);
        pageInfo.getList().forEach(item -> {
            item.setCompany(companyService.detail(item.getCompanyId()));
            item.setResume(resumeService.detail(item.getResumeId()));
            item.setStudent(studentService.detail(item.getStudentId()));
            item.setPost(postService.detail(item.getPostId()));
        });
        return Result.success(pageInfo);
    }

    @RequiresRoles(type = Role.STUDENT)
    @PostMapping("student_send")
    public Map<String, Object> student_send(@RequestBody Send send) {
        String token = UserThreadLocal.get();
        UserData userData = (UserData) redisUtil.get(token);
        send.setStudentId(userData.getId());
        PageInfo<Send> pageInfo = sendService.query(send);
        pageInfo.getList().forEach(item -> {
            item.setCompany(companyService.detail(item.getCompanyId()));
            item.setResume(resumeService.detail(item.getResumeId()));
            item.setPost(postService.detail(item.getPostId()));
        });
        return Result.success(pageInfo);
    }
}
