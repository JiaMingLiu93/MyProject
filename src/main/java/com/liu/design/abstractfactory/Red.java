package com.liu.design.abstractfactory;

/**
 * Created by Jam on 2017/2/19.
 */
public class Red implements Color{
    @Override
    public void fill() {
        System.out.println("Inside Red::fill() method.");
    }
}
