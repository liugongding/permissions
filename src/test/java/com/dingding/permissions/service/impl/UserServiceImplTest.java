package com.dingding.permissions.service.impl;

import com.dingding.permissions.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @Autowired
    UserServiceImpl userService;
    @Test
    public void queryUserByName() {
       User user = userService.queryUserByName("root");
        log.info("用户:{}",user);
    }
}