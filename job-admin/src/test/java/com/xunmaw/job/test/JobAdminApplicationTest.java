package com.xunmaw.job.test;

import com.xunmaw.job.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class JobAdminApplicationTest {

    //如何使用redis
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("name", "xiaoming");
        System.out.println(redisTemplate.opsForValue().get("name"));

        User user = new User();
        user.setId(1);
        user.setUserName("admin");
        redisTemplate.opsForValue().set("user", user);
        System.out.println(redisTemplate.opsForValue().get("user"));
    }
}
