package com.e2mg.redis.lock;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 描述
 *
 * @author EdiwalMusk
 * @date 2023/3/3 19:48
 */
public class RedisLock {

    private StringRedisTemplate stringRedisTemplate;

    private static final DefaultRedisScript<Long> UNLOCK_SCRIPT  = new DefaultRedisScript<>();
    private static final String ID_PREFIX = UUID.randomUUID().toString();
    static {
        UNLOCK_SCRIPT.setLocation(new ClassPathResource("unlock.lua"));
        UNLOCK_SCRIPT.setResultType(Long.class);
    }

    public RedisLock(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public boolean lock(String key) {
        Boolean b = stringRedisTemplate.opsForValue().setIfAbsent(key, ID_PREFIX,
                60, TimeUnit.SECONDS);
        return b == null ? false : b;
    }

    public boolean unlock(String key) {
        Long value = stringRedisTemplate.execute(UNLOCK_SCRIPT, Collections.singletonList(key), ID_PREFIX);
        return value != null && value > 0;
    }
}
