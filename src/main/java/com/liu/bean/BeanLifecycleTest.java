package com.liu.bean;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by jam on 2017/1/14.
 */
public class BeanLifecycleTest {
    public static void main(String[] args) {
        BeanFactory context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        context.getBean("test");
    }
}
