package com.liu.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by admin on 2017/1/14.
 */
@Component
public class DemoBean implements BeanFactoryAware,BeanNameAware,InitializingBean,DisposableBean{

    @PostConstruct
    public void init(){
        System.out.println("i am in init().");
    }
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("i am in setBeanFactory(),beanFactory = "+beanFactory.toString());
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("i am in setBeanName(),beanName = "+s);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("i am in destroy()");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("i am in afterPropertiesSet");
    }
}
