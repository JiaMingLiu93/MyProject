package com.liu.design.abstractfactory;

/**
 * Created by Jam on 2017/2/19.
 */
public class Green implements Color{
    @Override
    public void fill() {
        System.out.println("Inside Green::fill() method.");
    }
}
