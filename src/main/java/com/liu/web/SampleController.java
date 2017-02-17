package com.liu.web;

import com.liu.web.dao.UserDao;
import com.liu.web.entity.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jam on 2017/1/22.
 */
@RestController
@Data
public class SampleController {
    @Autowired
    UserDao userDao;

    @Value("${user.age}")
    private String age;
    @RequestMapping("/")
    String home() {
        User user = new User();
        user.setName("liu");
        user.setAge(19);
        int userCount = userDao.createUser(user);

        return "the number of created user is:"+userCount;
    }
}
