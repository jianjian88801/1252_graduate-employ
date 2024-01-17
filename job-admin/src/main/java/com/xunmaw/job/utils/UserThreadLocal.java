package com.xunmaw.job.utils;

public class UserThreadLocal {

    //存放本地线程变量
    private static ThreadLocal<String> userThreadLocal = new ThreadLocal<>();

    public static void set(String token) {
        userThreadLocal.set(token);
    }

    public static String get() {
        String token = userThreadLocal.get();
        return token;
    }

    public static void remove() {
        userThreadLocal.remove();
    }
}
