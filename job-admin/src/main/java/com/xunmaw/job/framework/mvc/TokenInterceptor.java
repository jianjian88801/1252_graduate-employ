package com.xunmaw.job.framework.mvc;

import com.xunmaw.job.framework.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.xunmaw.job.framework.exception.TokenException;
import com.xunmaw.job.utils.Status;
import com.xunmaw.job.utils.UserThreadLocal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        boolean flag = redisUtil.hasKey(token);
        if (flag) {
            UserThreadLocal.set(token);
            redisUtil.expire(token, RedisUtil.EXPR);
        } else {
            throw new TokenException(Status.TOKEN_ERROR.getMsg());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清除ThreadLocal
        UserThreadLocal.remove();
    }
}
