package com.xunmaw.job.front;

import com.xunmaw.job.entity.Company;
import com.xunmaw.job.entity.Student;
import com.xunmaw.job.service.CompanyService;
import com.xunmaw.job.service.StudentService;
import com.xunmaw.job.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/f/register/")
public class RegisterFrontController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CompanyService companyService;

    @PostMapping("student_create")
    public Result student_create(@RequestBody Student student) {
        Student param = new Student();
        param.setAccount(student.getAccount());
        int count = studentService.count(param);
        if (count > 0) {
            return Result.error("注册失败，账号已存在！");
        } else {
            int flag = studentService.create(student);
            if (flag > 0) {
                return Result.success();
            } else {
                return Result.error();
            }
        }
    }

    @PostMapping("company_create")
    public Result student_create(@RequestBody Company company) {
        Company param = new Company();
        param.setAccount(company.getAccount());
        int count = companyService.count(param);
        if (count > 0) {
            return Result.error("注册失败，账号已存在！");
        } else {
            int flag = companyService.create(company);
            if (flag > 0) {
                return Result.success();
            } else {
                return Result.error();
            }
        }
    }


}
