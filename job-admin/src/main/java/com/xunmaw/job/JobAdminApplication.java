package com.xunmaw.job;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 就业管理系统启动类
 *
 * @author xunmaw 2023/6/22 19:15
 */
@SpringBootApplication
@MapperScan("com.xunmaw.job.mapper")
public class JobAdminApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(JobAdminApplication.class, args);
    }
    
}
