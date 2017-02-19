package com.liu.design.abstractfactory;

/**
 * Created by Jam on 2017/2/19.
 */
public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
