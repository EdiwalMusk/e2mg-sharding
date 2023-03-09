package com.e2mg.sharding;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.e2mg.sharding.entity.Dict;
import com.e2mg.sharding.entity.User;
import com.e2mg.sharding.mapper.DictMapper;
import com.e2mg.sharding.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
    @Autowired
    private DictMapper dictMapper;

    @Test
    public void contextLoads() {
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setAge(1);
            userMapper.insert(user);
        }
    }

    @Test
    public void testRead() {
        User user = userMapper.selectById(1633673687615959042L);
        System.out.println(user.getId());
        user = userMapper.selectById(1633673575103729667L);
        System.out.println(user.getId());
    }

    @Test
    public void testReadUserByAge() {
        List<User> users = userMapper.selectList(new QueryWrapper<User>().eq("age", 1));
        System.out.println(users.size());
    }

    /**
     * 公共表数据添加测试
     */
    @Test
    public void testAddCommonTable() {
        Dict dict = new Dict();
        dict.setTitle("公共表");
        dictMapper.insert(dict);
    }

    @Test
    public void deleteCommonTable() {
        dictMapper.delete(new QueryWrapper<>());
    }
}
