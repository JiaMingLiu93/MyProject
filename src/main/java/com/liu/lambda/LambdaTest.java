package com.liu.lambda;

import com.google.common.collect.Lists;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Created by jam on 2016/12/30.
 */
public class LambdaTest<T> {
    public static void main(String[] args) throws Exception {
        //testIflazyExecute();
        //testSupplier();
        testNullStream();
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
