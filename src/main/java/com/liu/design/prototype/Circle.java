package com.liu.design.prototype;

/**
 * Created by Jam on 2017/2/20.
 */
public class Circle extends Shape{
    @Override
    void draw() {
        System.out.println("Inside Circle::draw() method.");
    }

    public Circle(){
        type = "Circle";
    }

}
