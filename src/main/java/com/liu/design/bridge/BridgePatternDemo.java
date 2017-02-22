package com.liu.design.bridge;

/**
 * Created by Jam on 2017/2/21.
 *
 * This pattern involves an interface which acts as a bridge which makes the functionality of concrete classes
 * independent from interface implementer classes. Both types of classes can be altered structurally without affecting
 * each other.
 * We are demonstrating use of Bridge pattern via following example in which a circle can be drawn in different colors
 * using same abstract class method but different bridge implementer classes.
 */
public class BridgePatternDemo {
    public static void main(String[] args) {
        Shape redCircle = new Circle(100,100, 10, new RedCircle());
        Shape greenCircle = new Circle(100,100, 10, new GreenCircle());

        redCircle.draw();
        greenCircle.draw();
    }

}
