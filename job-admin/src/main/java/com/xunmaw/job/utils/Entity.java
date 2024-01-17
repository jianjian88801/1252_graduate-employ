package com.xunmaw.job.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class Entity implements Serializable {

    private Integer page = 1;
    private Integer limit = 10;
}
