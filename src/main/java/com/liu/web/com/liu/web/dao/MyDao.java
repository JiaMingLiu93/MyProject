package com.liu.web.com.liu.web.dao;

import com.dtdream.commons.mybatis.BaseDao;
import com.liu.web.com.liu.web.entity.User;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * Created by jam on 2017/2/14.
 */
public class MyDao extends BaseDao<User>{
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.init(sqlSessionFactory);
    }
}
