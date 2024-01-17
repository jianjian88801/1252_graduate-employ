package com.xunmaw.job.vo;

import lombok.Data;

@Data
public class UserData {

    private Integer id;
    private String account;
    private String name;
    private Integer type;

    private String token;
}
