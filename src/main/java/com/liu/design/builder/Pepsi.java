package com.liu.design.builder;

/**
 * Created by Jam on 2017/2/19.
 */
public class Pepsi extends ColdDrink{
    @Override
    public String name() {
        return "Pepsi";
    }

    @Override
    public float price() {
        return 35.0f;
    }
}
