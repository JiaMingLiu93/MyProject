package com.liu.test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        //testEquals();
        testMapPutNull();
    }
    public static void testEquals(){
        Long a = 0l;
        if (a.equals(Integer.valueOf(0))){
            System.out.println("it is true.");
        }else {
            System.out.println("it is false.");
        }
    }
    public static void testMapPutNull(){
        HashMap<String, List<String>> map = Maps.newHashMap();
        ArrayList<String> list = Lists.newArrayList();
        map.put("q",null);
        System.out.println(CollectionUtils.isEmpty(map));
        map.entrySet().forEach(entry->{
            entry.getValue().get(0);
        });
    }
}
