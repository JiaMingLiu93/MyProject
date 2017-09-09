package com.liu.test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
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
        //testForIfContinue();
        //byte[][] bytes = longsToBytes(Lists.newArrayList(100069151L));
        //System.out.println(bytes);
        //testCollectorsBuild();
        testContainer();
    }

    public static void testContainer(){
        //Supplier<TestContainer> a = ()-> new TestContainer();
        //Supplier<TestContainer> objectSupplier= TestContainer::new;
        //TestContainer testContainer = objectSupplier.get();
        //testContainer.print();
        BiConsumer<Long,Long> handler = (Long c, Long b) -> System.out.println("c+b=" + (c + b));
        handler.accept(2L,3L);
    }

    public static void testCollectorsBuild(){
        HashBasedTable<Long, Long, Long> table = HashBasedTable.create();
        ArrayList<Long> list = Lists.newArrayList(1L,2L,3L);
        BiConsumer<HashBasedTable<Long, Long, Long>, ImmutableTable<Long, Long, Long>> putAll = HashBasedTable::putAll;
        Supplier<HashBasedTable<Long, Long, Long>> create = HashBasedTable::create;
        BiConsumer<HashBasedTable<Long, Long, Long>, HashBasedTable<Long, Long, Long>> putAll1 = HashBasedTable::putAll;
        HashBasedTable<Long, Long, Long> collect = list.stream().map(e -> ImmutableTable.of(e, e, e)).collect(create, putAll, putAll1);
        System.out.println(collect);
    }

    public static byte[][] longsToBytes(List<Long> ls) {
        byte[][] result = new byte[ls.size()][8];

        for(int i = 0; i < ls.size(); ++i) {
            long l = ((Long)ls.get(i)).longValue();
            result[i][0] = (byte)((int)(l >> 56));
            result[i][1] = (byte)((int)(l >> 48));
            result[i][2] = (byte)((int)(l >> 40));
            result[i][3] = (byte)((int)(l >> 32));
            result[i][4] = (byte)((int)(l >> 24));
            result[i][5] = (byte)((int)(l >> 16));
            result[i][6] = (byte)((int)(l >> 8));
            result[i][7] = (byte)((int)l);
        }

        return result;
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
