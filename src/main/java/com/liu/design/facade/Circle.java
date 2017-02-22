package com.liu.design.facade;

/**
 * Created by Jam on 2017/2/22.
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Circle::draw()");
    }
}
