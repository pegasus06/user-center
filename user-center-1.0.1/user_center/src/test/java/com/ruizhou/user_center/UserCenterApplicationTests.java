package com.ruizhou.user_center;

import com.ruizhou.user_center.mapper.UserMapper;
import com.ruizhou.user_center.model.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class UserCenterApplicationTests {
    @Resource
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        List<User> collect = users.stream()
                .map(user -> userMapper.selectById(user.getId()))
                .collect(Collectors.toList());
        System.out.println(collect);
    }


    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assertions.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

}

