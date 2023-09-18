public class Geometry {
    public static void main(String[] args) {
        PrintSquareArea(7.0 );
        PrintSquareArea(5.0);
        PrintSquareArea(3.0);

        printRectangleArea(3.0, 5.0);
        printRectangleArea(7.0, 9.0);

    }

    private static void printRectangleArea(double a, double b) {
        System.out.println("Площадь прямоуголника со сторонами " + a + " и " + b + " = " + rectangleArea(a, b));
    }

    private static double rectangleArea(double a, double b) {
        return a * b;
    }

    static void PrintSquareArea(double side) {
        System.out.println("Площадь квадрата со стороной " + side + " = " + getaDouble(side));
    }

    private static double getaDouble(double a) {
        return a * a;
    }
}
