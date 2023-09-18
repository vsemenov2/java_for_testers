package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;

public class Geometry {
    public static void main(String[] args) {
        Square.PrintSquareArea(7.0 );
        Square.PrintSquareArea(5.0);
        Square.PrintSquareArea(3.0);

        Rectangle.printRectangleArea(3.0, 5.0);
        Rectangle.printRectangleArea(7.0, 9.0);

    }

}
