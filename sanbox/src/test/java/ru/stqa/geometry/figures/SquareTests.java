package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTests {

    @Test
    void canCalculateArea() {
        var s = new Square(4.0);
        double result = s.area();
        Assertions.assertEquals(25.0, result);

    }

    @Test
    void canCalculateAreaPerimeter() {
        Assertions.assertEquals(20.0, new Square(5.0).perimeter());
    }

    @Test
    void cannotCreateSquareWithNegativeSide(){
        try {
            new Square(-5.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
        }
    }


    @Test
    void canCalculatePerimeterTriangle(){
        Assertions.assertEquals(16.0, Triangle.getPerimeterTriangle(5.,5.,6.));

    }
    @Test
    void canCalculateTriangle() {
        Assertions.assertEquals(14.696938456699069, Triangle.getTriangle(5.0, 6.0, 7.0));

    }

    @Test
    void testEquality()    {
        var s1 = new Square(5.0);
        var s2 = new Square(5.0);
        Assertions.assertEquals(s1, s2);
    }

    @Test
    void testNonEquality()    {
        var s1 = new Square(5.0);
        var s2 = new Square(4.0);
        Assertions.assertNotEquals(s1, s2);
    }

    @Test
    void testFail()    {
        var s1 = new Square(5.0);
        var s2 = new Square(5.0);
        Assertions.assertTrue(s1.equals(s2));
    }
}

