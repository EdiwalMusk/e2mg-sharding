package com.e2mg.sharding;

import com.e2mg.sharding.entity.User;
import com.e2mg.sharding.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 描述
 *
 * @author EdiwalMusk
 * @date 2023/3/9 10:35
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingJdbcTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
        for (int i = 0; i < 10; i++) {
            userMapper.insert(new User());
        }
    }

    @Test
    public void testRead() {
        User user = userMapper.selectById(1633665850080784388L);
        System.out.println(user.getId());
    }
}
