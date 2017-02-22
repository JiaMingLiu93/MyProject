package com.liu.design.facade;

/**
 * Created by Jam on 2017/2/22.
 */
public class Rectangle implements Shape{
    @Override
    public void draw() {
        System.out.println("Rectangle::draw()");
    }
}
