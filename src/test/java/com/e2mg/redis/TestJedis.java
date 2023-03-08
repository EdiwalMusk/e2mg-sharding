package com.e2mg.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 描述
 *
 * @author EdiwalMusk
 * @date 2023/3/4 8:54
 */
@Slf4j
@SpringBootTest
public class TestJedis {

    private Jedis jedis;

    @BeforeEach
    public void setUp() {
        jedis = new Jedis("192.168.94.194", 6379);
        jedis.select(0);
    }

    /**
     * 测试锁
     */
    @Test
    public void testGet()  {
        jedis.set("a", String.valueOf(5));
        System.out.println(jedis.get("a"));
    }

    /**
     * 测试可重入锁
     */
    @AfterEach
    public void tearDown() {
        if (jedis != null) {
            jedis.close();
        }
    }
}
