package com.xunmaw.job.entity;

import com.xunmaw.job.utils.Entity;
import lombok.Data;

import java.util.Date;

@Data
public class User extends Entity {

    private Integer id;
    private String userName;
    private String name;
    private String password;
    private Date loginTime;
}
