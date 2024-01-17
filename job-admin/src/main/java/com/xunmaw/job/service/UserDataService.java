package com.xunmaw.job.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xunmaw.job.framework.exception.TokenException;
import com.xunmaw.job.framework.redis.RedisUtil;
import com.xunmaw.job.utils.Status;
import com.xunmaw.job.utils.UserThreadLocal;
import com.xunmaw.job.vo.UserData;

@Service
public class UserDataService {

    @Autowired
    private RedisUtil redisUtil;

    public UserData getUser() {
        String token = UserThreadLocal.get();
        UserData userData = (UserData) redisUtil.get(token);
        if(userData != null) {
            return userData;
        } else {
            throw new TokenException(Status.TOKEN_ERROR.getMsg());
        }
    }
}
