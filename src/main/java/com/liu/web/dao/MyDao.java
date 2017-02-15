package com.liu.web.dao;

import com.dtdream.commons.mybatis.BaseDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by jam on 2017/2/14.
 */
public abstract class MyDao<T> extends BaseDao<T>{
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.init(sqlSessionFactory);
    }
}
