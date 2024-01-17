package com.xunmaw.job.utils;

public enum Status {

    SUCCESS(200, "操作成功"),
    ERROR(500, "操作失败"),
    NO_AUTH(401, "没有权限访问"),
    TOKEN_ERROR(402, "超时或不合法的token");

    private Integer code;
    private String msg;

    Status(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
