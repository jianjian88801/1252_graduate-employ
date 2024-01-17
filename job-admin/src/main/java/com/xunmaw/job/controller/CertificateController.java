package com.xunmaw.job.controller;

import com.github.pagehelper.PageInfo;
import com.xunmaw.job.entity.Certificate;
import com.xunmaw.job.entity.Resume;
import com.xunmaw.job.service.CertificateService;
import com.xunmaw.job.service.ResumeService;
import com.xunmaw.job.service.UserDataService;
import com.xunmaw.job.utils.Result;
import com.xunmaw.job.vo.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/certificate")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;
    @Autowired
    private ResumeService resumeService;
    @Autowired
    private UserDataService userDataService;

    @PostMapping("/create")
    public Result create(@RequestBody Certificate certificate) {
        int flag = certificateService.create(certificate);
        if (flag > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @PostMapping("/delete")
    public Result delete(String ids) {
        int flag = certificateService.delete(ids);
        if (flag > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @PostMapping("/update")
    public Result update(@RequestBody Certificate certificate) {
        int flag = certificateService.update(certificate);
        if (flag > 0) {
            return Result.success();
        } else {
            return Result.error();
        }
    }

    @PostMapping("/detail")
    public Result detail(Integer id) {
        return Result.success(certificateService.detail(id));
    }

    @PostMapping("/query")
    public Map<String, Object> query(@RequestBody Certificate certificate) {
        UserData user = userDataService.getUser();
        Resume resumeParam = resumeService.detail(user.getId());
        if(resumeParam == null) {
            return Result.success(new PageInfo<>());
        }
        certificate.setResumeId(resumeParam.getId());
        PageInfo<Certificate> pageInfo = certificateService.query(certificate);
        pageInfo.getList().forEach(item -> {
            Resume resume = resumeService.detail(item.getResumeId());
            item.setResume(resume);
        });
        return Result.success(pageInfo);
    }

}
