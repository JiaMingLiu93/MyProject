package com.liu.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by Jam on 2017/3/13.
 */
@Component
public class Test {
    @Value("${metric.config.name:loadOne}")
    public String loadOne = "";
    @PostConstruct
    public void print(){
        System.out.println("====="+loadOne);
    }
    public static void main(String[] args) {
        testEquals();
    }
    public static void testEquals(){
        Long a = 0l;
        if (a.equals(Integer.valueOf(0))){
            System.out.println("it is true.");
        }else {
            System.out.println("it is false.");
        }
    }
}
