package com.liu.design.builder;

/**
 * Created by Jam on 2017/2/19.
 */
public abstract class Burger implements Item{
    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();
}
