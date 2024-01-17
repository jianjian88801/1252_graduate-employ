package com.xunmaw.job.utils;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Result {

    private Integer code;
    private String msg;
    private Object data;

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result success() {
        return new Result(Status.SUCCESS.getCode(), Status.SUCCESS.getMsg(), null);
    }

    public static Map<String, Object> success(PageInfo pageInfo) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", Status.SUCCESS.getCode());
        map.put("msg", Status.SUCCESS.getMsg());
        map.put("total", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }

    public static Result success(Object data) {
        return new Result(Status.SUCCESS.getCode(), Status.SUCCESS.getMsg(), data);
    }

    public static Result error() {
        return new Result(Status.ERROR.getCode(), Status.ERROR.getMsg(), null);
    }

    public static Result error(String msg) {
        return new Result(Status.ERROR.getCode(), msg, null);
    }

    public static Result error(Integer code, String msg) {
        return new Result(code, msg, null);
    }
}
