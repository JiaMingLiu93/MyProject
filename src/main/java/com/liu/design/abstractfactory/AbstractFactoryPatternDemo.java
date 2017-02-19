package com.liu.design.abstractfactory;

/**
 * Created by Jam on 2017/2/19.
 * Abstract Factory patterns work around a super-factory which creates other factories. This factory is also called as
 * factory of factories. This type of design pattern comes under creational pattern as this pattern provides one of the
 * best ways to create an object.
 * In Abstract Factory pattern an interface is responsible for creating a factory of related objects without explicitly
 * specifying their classes. Each generated factory can give the objects as per the Factory pattern.
 */
public class AbstractFactoryPatternDemo {
    public static void main(String[] args) {

        //get shape factory
        AbstractFactory shapeFactory = FactoryProducer.getFactory("SHAPE");

        //get an object of Shape Circle
        Shape shape1 = shapeFactory.getShape("CIRCLE");

        //call draw method of Shape Circle
        shape1.draw();

        //get an object of Shape Rectangle
        Shape shape2 = shapeFactory.getShape("RECTANGLE");

        //call draw method of Shape Rectangle
        shape2.draw();

        //get an object of Shape Square
        Shape shape3 = shapeFactory.getShape("SQUARE");

        //call draw method of Shape Square
        shape3.draw();

        //get color factory
        AbstractFactory colorFactory = FactoryProducer.getFactory("COLOR");

        //get an object of Color Red
        Color color1 = colorFactory.getColor("RED");

        //call fill method of Red
        color1.fill();

        //get an object of Color Green
        Color color2 = colorFactory.getColor("Green");

        //call fill method of Green
        color2.fill();

        //get an object of Color Blue
        Color color3 = colorFactory.getColor("BLUE");

        //call fill method of Color Blue
        color3.fill();
    }
}
