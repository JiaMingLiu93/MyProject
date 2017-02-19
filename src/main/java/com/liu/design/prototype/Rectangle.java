package com.liu.design.prototype;

/**
 * Created by Jam on 2017/2/20.
 */
public class Rectangle extends Shape{
    @Override
    void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
    public Rectangle(){
        type = "Rectangle";
    }
}
