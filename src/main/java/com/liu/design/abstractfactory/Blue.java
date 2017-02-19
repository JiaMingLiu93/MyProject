package com.liu.design.abstractfactory;

/**
 * Created by Jam on 2017/2/19.
 */
public class Blue implements Color{
    @Override
    public void fill() {
        System.out.println("Inside Blue::fill() method.");
    }
}
