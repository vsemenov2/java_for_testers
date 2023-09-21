package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTests {

    @Test
    void canCalculateArea() {
        var s = new Square(5.0);
        double result = s.area();
        Assertions.assertEquals(25.0, result);
    }

    @Test
    void canCalculateAreaPerimeter() {
        Assertions.assertEquals(20.0, new Square(5.0).perimeter());
    }
    @Test
    void canCalculatePerimeterTriangle(){
        Assertions.assertEquals(8.0, Triangle.getPerimeterTriangle(5.,5.,6.));

    }
    @Test
    void canCalculateTriangle() {
        Assertions.assertEquals(24.49489742783178, Triangle.getTriangle(5.0, 6.0, 7.0, 10));
    }
}
