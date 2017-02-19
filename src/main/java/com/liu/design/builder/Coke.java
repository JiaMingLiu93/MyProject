package com.liu.design.builder;

/**
 * Created by Jam on 2017/2/19.
 */
public class Coke extends ColdDrink{
    @Override
    public String name() {
        return "Coke";
    }

    @Override
    public float price() {
        return 30.0f;
    }
}
