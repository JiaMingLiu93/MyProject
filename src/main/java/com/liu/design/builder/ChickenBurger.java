package com.liu.design.builder;

/**
 * Created by Jam on 2017/2/19.
 */
public class ChickenBurger extends Burger{
    @Override
    public String name() {
        return "Chicken Burger";
    }

    @Override
    public float price() {
        return 50.5f;
    }
}
