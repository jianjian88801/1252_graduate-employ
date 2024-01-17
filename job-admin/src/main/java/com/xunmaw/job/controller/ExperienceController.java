package com.xunmaw.job.controller;

import com.github.pagehelper.PageInfo;
import com.xunmaw.job.entity.Experience;
import com.xunmaw.job.entity.Resume;
import com.xunmaw.job.service.ExperienceService;
import com.xunmaw.job.service.ResumeService;
import com.xunmaw.job.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.xunmaw.job.utils.Result;
import com.xunmaw.job.vo.UserData;

import java.util.Map;

@RestController
@RequestMapping("/experience")
public class ExperienceController {

    @Autowired
    private ExperienceService experienceService;
    @Autowired
    private ResumeService resumeService;
    @Autowired
    private UserDataService userDataService;

    @PostMapping("/create")
    public Result create(@RequestBody Experience experience) {
        int flag = experienceService.create(experience);
        if (flag > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @PostMapping("/delete")
    public Result delete(String ids) {
        int flag = experienceService.delete(ids);
        if (flag > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @PostMapping("/update")
    public Result update(@RequestBody Experience experience) {
        int flag = experienceService.update(experience);
        if (flag > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @PostMapping("/detail")
    public Result detail(Integer id) {
        return Result.success(experienceService.detail(id));
    }

    @PostMapping("/query")
    public Map<String, Object> query(@RequestBody Experience experience) {
        UserData user = userDataService.getUser();
        Resume resumeParam = resumeService.detail(user.getId());
        if(resumeParam == null) {
            return Result.success(new PageInfo<>());
        }
        experience.setResumeId(resumeParam.getId());
        PageInfo<Experience> pageInfo = experienceService.query(experience);
        pageInfo.getList().forEach(item -> {
            Resume resume = resumeService.detail(item.getResumeId());
            item.setResume(resume);
        });
        return Result.success(pageInfo);
    }

}
