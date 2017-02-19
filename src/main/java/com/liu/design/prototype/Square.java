package com.liu.design.prototype;

/**
 * Created by Jam on 2017/2/20.
 */
public class Square extends Shape{
    @Override
    void draw() {
        System.out.println("Inside Square::draw() method.");
    }
    public Square(){
        type = "Square";
    }
}
