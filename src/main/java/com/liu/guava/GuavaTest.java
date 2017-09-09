package com.liu.guava;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.collect.TreeBasedTable;
import com.google.common.primitives.Ints;

import java.util.List;

/**
 * Created by Jam on 2017/4/7.
 */
public class GuavaTest {
    public static void main(String[] args) {
        //orderingTest();
        tableSortTest();
    }

    private static void orderingTest() {
        Ordering<String> ordering = new Ordering<String>() {
            @Override
            public int compare(String s, String t1) {
                return Ints.compare(s.length(),t1.length());
            }
        };
        List<String> strings = ordering.greatestOf(Lists.newArrayList("liu", "jia", "ming", "is", "a"), 3);
        System.out.println(strings);
    }

    public static void tableSortTest(){
        TreeBasedTable<String, Long, Long> table = TreeBasedTable.create();
        table.put("1",2L,2L);
        table.put("5",1L,2L);
        table.put("4",5L,5L);
        table.put("2",2L,2L);
        table.put("3",4L,4L);
        table.cellSet().forEach(System.out::println);
        System.out.println(table);
    }
}
