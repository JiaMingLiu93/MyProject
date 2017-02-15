package com.liu.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jam on 2017/1/22.
 */
@Component("test")
public class DemoBean2 {
    @Autowired
    DemoBean demoBean;

}
