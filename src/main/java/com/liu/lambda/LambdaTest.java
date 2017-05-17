package com.liu.lambda;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import javafx.scene.control.Button;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by jam on 2016/12/30.
 */
public class LambdaTest<T> {
    public static void main(String[] args) throws Exception {
        //testIflazyExecute();
        //testSupplier();
        //testNullStream();
        //testFilterMap();
        //testCollectMapFromMap();
        //testPartitionBy();
        //testForkJoin();
        testCollectList();
    }

    public static void testCollectList(){
        ArrayList<Long> longs = Lists.newArrayList(1L, 2L, 3L);
        ArrayList<Long> longs1 = Lists.newArrayList(3L, 4L, 5L);
        HashMap<Integer, List<Long>> map = Maps.newHashMap();
        map.put(1,longs);
        map.put(2,longs1);
        ArrayList<Long> collect = map.entrySet().stream().map(Map.Entry::getValue).collect(ArrayList::new, ArrayList::addAll, ArrayList::addAll);
        System.out.println(collect);

    }

    public static void testForkJoin() throws ExecutionException, InterruptedException {

        Stream<Double> firstRange = Stream.generate(Math::random).limit(100000000);
        Stream<Double> secondRange = Stream.generate(Math::random).limit(10000);

        // 统计并行执行list的线程
        Set<Thread> threadSet = new CopyOnWriteArraySet<>();

        ForkJoinPool forkJoinPool = new ForkJoinPool(1);



        //firstRange.parallel().forEach((number) -> {
        //            Thread thread = Thread.currentThread();
        //            // System.out.println(thread);
        //            // 统计并行执行list的线程
        //            threadSet.add(thread);
        //        });
        forkJoinPool.submit(() -> firstRange.parallel().forEach((number) -> {
                Thread thread = Thread.currentThread();
                // System.out.println(thread);
                // 统计并行执行list的线程
                threadSet.add(thread);
        })).get();

        System.out.println("threadSet一共有" + threadSet.size() + "个线程");
        System.out.println("系统一个有"+Runtime.getRuntime().availableProcessors()+"个cpu");

        //ForkJoinPool forkJoinPool2 = new ForkJoinPool(3);
        //
        //forkJoinPool2.submit(() -> {
        //
        //    secondRange.parallel().forEach((number) -> {
        //
        //        try {
        //
        //            Thread.sleep(5);
        //
        //        } catch (InterruptedException e) {
        //
        //        }
        //
        //    });
        //
        //});


    }

    public static void testPartitionBy(){
        ArrayList<Integer> integers1 = Lists.newArrayList(1, 2, 3, 4, 5, 6);
        ArrayList<Integer> integers2 = Lists.newArrayList(1, 2, 3);
        ArrayList<Integer> integers3 = Lists.newArrayList(1, 2, 3, 4);
        ArrayList<Integer> integers4 = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7);
        HashMap<Object, List<Integer>> map = Maps.newHashMap();
        map.put("1",integers1);
        map.put("2",integers2);
        map.put("3",integers3);
        map.put("4",integers4);
        Map<Boolean, List<Map.Entry<Object, List<Integer>>>> collect = map.entrySet().stream().collect(Collectors.partitioningBy(entry -> entry.getValue().size() > 4));
        System.out.println(collect.get(false));
        System.out.println(collect.get(true));

    }
    public static void testCollectMapFromMap(){
        HashMap<Long, Long> map1 = Maps.newHashMap();
        map1.put(1L, 1L);
        map1.put(2L, 2L);
        map1.put(3L, 3L);
        map1 = (HashMap<Long,Long>)map1.entrySet().stream().filter(entry -> !(entry.getKey() == 1L)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(map1);
    }


    public static void testFilterMap(){
        ArrayList<Map<String,String>> listMap = Lists.newArrayList();
        Map<String, String> map1 = Maps.newHashMap();
        map1.put("1","1");
        map1.put("2","2");
        map1.put("3","3");

        listMap.add(map1);
        List<HashMap<String, String>> collect = listMap.stream().map(map -> map.entrySet().stream().filter(entry -> {
            String key = entry.getKey();
            if (key.contains("1")) {
                return false;
            }
            return true;
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, HashMap::new))).collect(Collectors.toList());

        collect.forEach(System.out::println);

        System.out.println(Lists.newArrayList().stream().anyMatch(o -> false));
    }
    public static void testNullStream(){
        List<String> strings = null;
        try {
            strings.stream().forEach(System.out::println);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void testFunctionConvert(){
        //()->{} convert to runnable.
        //every lambda statement can be converted to a function interface.
        Runnable runnable = () -> {
            System.out.println("i am running!");
        };
        BiFunction<String, String, Object> stringComparator = (String first, String second) ->
                Integer.compare(first.length(), second.length());

    }

    /**
     * build generic type array.
     * "new T[n]" is not valid.
     */
    public <T> void testBuildGenericArray(T t){
        //new T[5];
        ArrayList<String> labels = Lists.newArrayList();
        labels.add("button1");
        labels.add("button2");
        Stream<Button> buttonStream = labels.stream().map(Button::new);

        Button[] buttons = labels.stream().toArray(Button[]::new);
    }


    public  static void testSupplier(){
        Supplier<Stream<String>> streamSupplier =
                () -> Stream.of("d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));

        streamSupplier.get().anyMatch(s -> true);   // ok
        streamSupplier.get().noneMatch(s -> true);  // ok
    }

    public  void testIflazyExecute() throws MyException {
        List list = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");

        for(Object s:list){
            if(s.equals("2")){
                throw new MyException("can not execute.");
            }

        }
        try {

            list.forEach((Object string) ->{
                System.out.println(string);
                if(string.equals("2")) {
                    try {
                        throw new MyException("can not execute.");
                    } catch (MyException e) {
                        e.printStackTrace();
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

        //System.out.println("in the end");
    }
}


class MyException extends Exception{
    private String msg;
    public MyException(String msg){
        this.msg = msg;
    }
}
