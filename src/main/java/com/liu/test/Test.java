package com.liu.test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

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
        //testMapPutNull();
        //testReturnInForeach();
        //testPutNullIntoList();
        //testReturnFromEmpty();
        //testHashMapToJson();
        //testSortHashMap();
        //testListAddNUllS();
        //testMultiSet();
        testForIfContinue();
    }

    public static void testForIfContinue(){
        int i = 1;
        while (i<10){
            if (i>5){
                System.out.println(i);
                if (i>8){
                    return;
                }
            }
            System.out.println("while:"+i);
            i++;
        }
    }

    public static void testMultiSet(){
        HashMultiset<Long> longs = HashMultiset.create();
        longs.add(1L);
        longs.add(2L);
        longs.add(1L);
        longs.add(4L);
        System.out.println(longs.elementSet());
        System.out.println(longs.entrySet());

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
    public static void testReturnInForeach(){
        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 4, 5);
        integers.forEach(integer -> {
            if (integer<3){
                return;
            }
            System.out.println(integer);
        });
    }
    public static void testPutNullIntoList(){
        ArrayList<Object> list = Lists.newArrayList();
        list.add(null);
        list.add(null);
        System.out.println(list.size());
    }

    /**
     * conclusion:the return is not null,maybe empty.
     */
    public static void testReturnFromEmpty(){
        Map<Integer,Integer> collect = Lists.newArrayList().stream().map(o -> {
            return Maps.immutableEntry(1, 3);
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, HashMap::new));
        System.out.println(CollectionUtils.isEmpty(collect));
        System.out.println(collect.size());
    }

    public static void testHashMapToJson(){
        HashMap<String, List<Long>> map = Maps.newHashMap();
        //HashMap<String, List<Long>> map1 = Maps.newHashMap();
        map.put("2017/01/04",Lists.newArrayList(12L,13L));
        map.put("2017/01/05",Lists.newArrayList(12L,13L));
        //Lists.newArrayList(map);
        Object json = JSON.toJSON(map);
        String jsonString = JSON.toJSONString(map.entrySet());
        System.out.println(json);
        System.out.println(jsonString);
    }

    public static void testSortHashMap(){
        HashMap<String, String> map = Maps.newHashMap();
        map.put("2017-04-28","1");
        map.put("2017-04-26","2");
        map.put("2017-04-27","3");
        map.put("2017-04-25","4");
        ArrayList<Map.Entry<String, String>> entries = new ArrayList<>(map.entrySet());
        entries.sort(Comparator.comparing(Map.Entry::getKey));
        System.out.println(entries);
        System.out.println(map);
        map.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey)).forEach(System.out::println);

    }

    public static void testListAddNUllS(){
        ArrayList<Object> list = Lists.newArrayList();
        list.add(null);
        list.add(null);
        list.add(null);
        System.out.println(list);
    }


}
