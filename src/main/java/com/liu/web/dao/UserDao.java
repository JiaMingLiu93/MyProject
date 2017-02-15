package com.liu.web.dao;

import com.liu.web.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 2017/2/14.
 */
@Repository
public class UserDao extends MyDao<User> {
    public int createUser(User user){
        return super.getSqlSession().insert(this.sqlId("createUser"), user);
    }
}
