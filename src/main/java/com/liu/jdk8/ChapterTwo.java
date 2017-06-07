package com.liu.jdk8;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by admin on 2017/5/18.
 */
public class ChapterTwo {
    public static void main(String[] args) {
        //fourth();
        //five();
        //six();
        //eight();
        //functionTest();
        combineFunctionTest();
    }
    public static void fourth(){
        int []a = new int[]{1,2,3,4};
        Stream<int[]> a1 = Stream.of(a);
        System.out.println(a1);
        IntStream stream = Arrays.stream(a);
        stream.forEach(System.out::println);
    }

    public static void five(){
        Stream<Long> iterate = Stream.iterate(0L, longs -> (25214903517L * longs + 11) % (long) Math.pow(2, 48));
        iterate.forEach(System.out::println);
    }

    public static void six(){
        //String s = "wonderful";
        //char c = s.charAt(0);
        //IntStream range = IntStream.rangeClosed(0, s.length() - 1);
        //range.map(i->(Character)s.charAt(i)).collect(Collectors.toList());
        //intStream.forEach(System.out::println);
    }

    //public static <T> boolean isFinite(Stream<T> stream){
    //    stream.count();
    //}

    public static void eight(){
        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 4);
        ArrayList<Integer> integers1 = Lists.newArrayList(5, 6, 7, 8);
        Stream<Integer> stream = integers.stream();
        Stream<Integer> stream1 = integers1.stream();
        Stream.of(integers,integers1).flatMap(Collection::stream).forEach(System.out::println);
    }

    public static void functionTest(){
        int x = 1;
        info(()->x);
    }
    public static void info(Supplier<Integer> supplier){
        System.out.println(supplier.get());
    }

    public static void combineFunctionTest(){
        Integer a = 0;
        Predicate<Object> equal = Predicate.isEqual(a);
        boolean test = equal.test(0);
        System.out.println(test);
    }

}
