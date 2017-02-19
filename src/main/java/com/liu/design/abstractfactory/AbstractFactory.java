package com.liu.design.abstractfactory;

/**
 * Created by Jam on 2017/2/19.
 */
public abstract class AbstractFactory {
    abstract Color getColor(String color);
    abstract Shape getShape(String shape) ;
}
