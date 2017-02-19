package com.liu.design.factory;

/**
 * Created by Jam on 2017/2/19.
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
