package com.xunmaw.job.controller;

import com.github.pagehelper.PageInfo;
import com.xunmaw.job.entity.Intention;
import com.xunmaw.job.entity.Resume;
import com.xunmaw.job.service.IntentionService;
import com.xunmaw.job.service.ResumeService;
import com.xunmaw.job.service.UserDataService;
import com.xunmaw.job.utils.Result;
import com.xunmaw.job.vo.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/intention")
public class IntentionController {

    @Autowired
    private IntentionService intentionService;
    @Autowired
    private ResumeService resumeService;
    @Autowired
    private UserDataService userDataService;

    @PostMapping("/create")
    public Result create(@RequestBody Intention intention) {
        int flag = intentionService.create(intention);
        if (flag > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @PostMapping("/delete")
    public Result delete(String ids) {
        int flag = intentionService.delete(ids);
        if (flag > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @PostMapping("/update")
    public Result update(@RequestBody Intention intention) {
        int flag = intentionService.update(intention);
        if (flag > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @PostMapping("/detail")
    public Result detail(Integer id) {
        return Result.success(intentionService.detail(id));
    }

    @PostMapping("/query")
    public Map<String, Object> query(@RequestBody Intention intention) {
        UserData user = userDataService.getUser();
        Resume resumeParam = resumeService.detail(user.getId());
        if(resumeParam == null) {
            return Result.success(new PageInfo<>());
        }
        intention.setResumeId(resumeParam.getId());
        PageInfo<Intention> pageInfo = intentionService.query(intention);
        pageInfo.getList().forEach(item -> {
            Resume resume = resumeService.detail(item.getResumeId());
            item.setResume(resume);
        });
        return Result.success(pageInfo);
    }

}
