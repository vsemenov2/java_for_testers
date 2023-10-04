package ru.stqa.geometry.figures;

import java.util.Objects;

public record Triangle(double sideA, double sideB, double sideC) {
     public static void SquareTriangle(double sideA, double sideB, double sideC) {
        if (sideA < 0 || sideB < 0 || sideC < 0) {
            throw new ArithmeticException("Сторона треугольника меньше 0");
         }
        if ((sideA+sideB) < sideC || (sideA + sideC) < sideB || (sideB + sideC) < sideA ){
            throw new ArithmeticException("Сумма двух любых сторон должна быть не меньше третьей стороны");
        }
         var perimeterTriangle = getPerimeterTriangle(sideA, sideB, sideC);
         var areaTriangle = getTriangle(sideA, sideB, sideC);
        System.out.println(String.format("Периметр треугольника со сторонами %f  %f  %f  = %f ", sideA, sideB, sideC, perimeterTriangle));
        System.out.println(String.format("Площадь треугольника по формуле Герона = %f ", areaTriangle));

            }


    public static double getPerimeterTriangle(double sideA, double sideB, double sideC) {
        return (sideA + sideB + sideC);
    }

    public static double getTriangle(double sideA, double sideB, double sideC) {
         var  p = getPerimeterTriangle(sideA, sideB, sideC) / 2;
         return Math.sqrt(p * (p - sideA) * (p - sideB) * (p - sideC));
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return (Double.compare(triangle.sideA, this.sideA) == 0 && Double.compare(triangle.sideB, this.sideB) == 0 && Double.compare(triangle.sideC, this.sideC) == 0)
                || (Double.compare(triangle.sideA, this.sideC) == 0 && Double.compare(triangle.sideB, this.sideA) == 0 && Double.compare(triangle.sideC, this.sideB) == 0)
                || (Double.compare(triangle.sideA, this.sideB) == 0 && Double.compare(triangle.sideB, this.sideC) == 0 && Double.compare(triangle.sideC, this.sideA) == 0)
                || (Double.compare(triangle.sideA, this.sideC) == 0 && Double.compare(triangle.sideB, this.sideB) == 0 && Double.compare(triangle.sideC, this.sideA) == 0);


    }

    @Override
    public int hashCode() {
        return 1;
    }

}