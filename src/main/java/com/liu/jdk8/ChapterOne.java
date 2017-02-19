package com.liu.jdk8;


import com.google.common.collect.Lists;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by Jam on 2017/2/19.
 */
public class ChapterOne {
    public static void main(String[] args) {
        //firstQuestion();
        //secondQuestion();
        //thirdQuestion();
        //fourthQuestion();
        //eighthQuestion();
        ninthQuestion();
    }
    public static void firstQuestion(){
        //is the thread of comparator's code in the Arrays.sort method the same as the thread that calls the sort method?
        ArrayList<String> lists = Lists.newArrayList();
        lists.add("i");
        lists.add("miss");
        lists.add("you");
        lists.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                Thread thread = Thread.currentThread();
                System.out.println("comparator thread id is: "+thread.getId());
                return Integer.valueOf(o1.length()).compareTo(o2.length());
            }
        });
        Collections.sort(lists);
        Thread thread = Thread.currentThread();
        System.out.println("calls the sort method ,the thread id is: "+thread.getId());
        System.out.println(lists);

        //the answer is :yes.
    }

    public static void secondQuestion(){
        //Use the listFiles (FileFilter) and isDirectory methods of the java.io.File class to write a method that
        // returns all subdirectories under the specified directory. Use the lambda expression instead of the
        // FileFilter object, and rewrite it as a method reference.
        File file = new File("e:\\install");
        System.out.println(file.getPath());
        File[] files = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
    });
        File[] files1 = file.listFiles((file1->file1.isDirectory()));
        File[] files2 = file.listFiles((File::isDirectory));
        System.out.println("fileFilter object:");
        Stream.of(files!= null ? files : new File[0]).forEach(System.out::println);
        System.out.println("lambda expression:");
        Stream.of(files1 != null ? files1 : new File[0]).forEach(System.out::println);
        System.out.println("lambda expression using a method reference:");
        Stream.of(files2 != null ? files2 : new File[0]).forEach(System.out::println);
    }

    public static void thirdQuestion(){
        //Use the java.io.File class's list (FilenameFilter) method to write a method that returns all the files in the
        // specified directory with the pointing extension. Use lambd expressions instead of FilenameFilter. Does it
        // catch the variables of the closed scope?
        File file = new File("e:\\install\\Git");

        file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return false;
            }
        });

        String postfix = ".exe";
        File[] files = file.listFiles((File file1,String fileName)->{
            //the file1 if file according to the source code of listFiles().
            //System.out.println("file1="+file1.getPath()+" and fileName="+fileName);
            return fileName.contains(postfix);
        });
        assert files != null;
        Stream.of(files).forEach(System.out::println);
    }

    public static void fourthQuestion(){
        //given an array of file objects,sort it so that the directories come before the files,and within each group,
        // elements are sorted by path name.Use a lambda expression ,not a Comparator.
        File file = new File("e:\\test");
        File[] files = file.listFiles();
        List<File> fileList = Arrays.asList(files);
        System.out.println("befor sort:");
        fileList.forEach(System.out::println);
        fileList.sort((File file1,File file2)->{
            if (file1.isDirectory()&&file2.isFile()){
                return -1;
            }
            if (file1.isFile()&&file2.isDirectory()){
                return 1;
            }
            return Integer.valueOf(file1.getPath().length()).compareTo(file2.getPath().length());
        });

        System.out.println("after sort:");
        fileList.forEach(System.out::println);

    }

    public static void sixthQuestion(){
        //Write a method uncheck that catches all checked exceptions and turns
        //them into unchecked exceptions.
        new Thread(new RunnableEx() {
            @Override
            public void run(){
                System.out.println("shm");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //i have no idea.
    }

    public static Runnable uncheck(RunnableEx runner){
        return new Thread(runner);
    }

    public static void eighthQuestion(){
        String[] names = {"Peter","Paul","May"};
        ArrayList<Runnable> runnables = new ArrayList<Runnable>();
        for (String name:names){
            runnables.add(()->System.out.println(name));
        }
        //for (int i=0;i<names.length;i++){
        //    runnables.add(()-> System.out.println(names[i]));
        //}
        runnables.forEach(runner->{
            new Thread(runner).start();
        });
    }

    public static void ninthQuestion(){
        //For a subclass Collection2 from Collection and add a default method void
        //forEachIf(Consumer<T> action,Predicate<T> filter) that applies action to
        //each element for which filter returns true.How could you use it?
        ArrayList2<String> list2 = new ArrayList2<String>();
        list2.add("liu");
        list2.add("jia");
        list2.add("ming");
        list2.forEachIf(System.out::println,s -> s.length()==3);
    }

}

interface RunnableEx extends Runnable{
    public abstract void run() throws RuntimeException;
}

interface Collection2<E> extends Collection<E>{
    void forEachIf(Consumer<E> action, Predicate<E> filter);
}
class ArrayList2<E> extends AbstractCollection<E> implements Collection2<E>{

    private ArrayList<E> list;
    public ArrayList2(){
        list = Lists.newArrayList();
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    public boolean add(E e){
        list.add(e);
        return true;
    }
    @Override
    public void forEachIf(Consumer<E> action, Predicate<E> filter) {
        int size = list.size();
        for (int i=0;i<size;i++){
            if (filter==null||filter.test(list.get(i))){
                action.accept(list.get(i));
            }
        }
    }

}
