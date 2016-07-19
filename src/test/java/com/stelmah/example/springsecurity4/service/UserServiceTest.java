package com.stelmah.example.springsecurity4.service;


import com.stelmah.example.springsecurity4.configuration.WebConfig;
import com.stelmah.example.springsecurity4.model.User;
import com.stelmah.example.springsecurity4.service.api.UserService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Ignore
public class UserServiceTest {

    private static final String TEST_LOGIN_1 = "testtesttest";
    private static final String TEST_LOGIN_2 = "testtest";

    private static Logger LOG = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    private UserService userService;


    @Test
//    @Ignore
    public void saveTest() {
        User user = new User();
        user.setLogin(TEST_LOGIN_1);
        user.setPassword(TEST_LOGIN_1);
        userService.save(user);
    }


    @Test
    public void getAllTest() {
        List<User> users = userService.getAll();

        for (User user : users) {
            if (user.getLogin().equals(TEST_LOGIN_1)) {
            }
        }
    }

    @Test
    public void updateTest() {
        User user = userService.getByLoginPassword(TEST_LOGIN_1, TEST_LOGIN_1);
        if (user == null) {
            Assert.fail("NOT FOUND User with ");
        }
        user.setLogin(TEST_LOGIN_2);
        user.setPassword(TEST_LOGIN_2);
        userService.update(user);
    }

    @Test
    public void deleteTest() {
        User user = userService.getByLoginPassword(TEST_LOGIN_2, TEST_LOGIN_2);
        if (user == null) {
            Assert.fail("NOT FOUND User with ");
        }
        userService.delete(user);
    }

}
