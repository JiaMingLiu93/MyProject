package com.liu.guava;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

import java.util.List;

/**
 * Created by Jam on 2017/4/7.
 */
public class GuavaTest {
    public static void main(String[] args) {
        Ordering<String> ordering = new Ordering<String>() {
            @Override
            public int compare(String s, String t1) {
                return Ints.compare(s.length(),t1.length());
            }
        };
        List<String> strings = ordering.greatestOf(Lists.newArrayList("liu", "jia", "ming", "is", "a"), 3);
        System.out.println(strings);
    }
}
