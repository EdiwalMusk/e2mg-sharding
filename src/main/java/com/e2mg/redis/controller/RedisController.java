package com.e2mg.redis.controller;

import com.e2mg.redis.lock.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述
 *
 * @author EdiwalMusk
 * @date 2023/3/3 19:46
 */
@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/get")
    public void get() {
        RedisLock redisLock = new RedisLock(stringRedisTemplate);
        boolean b = redisLock.lock("abc");
        System.out.println(b);
        b = redisLock.unlock("abc");
        System.out.println(b);
    }
}
