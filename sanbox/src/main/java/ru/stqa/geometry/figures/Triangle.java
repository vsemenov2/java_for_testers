package ru.stqa.geometry.figures;

import java.util.Objects;

public record Triangle(double sideA, double sideB, double sideC) {

    public Triangle{
        if ((sideA + sideB) < sideC || (sideA + sideC) < sideB || (sideB + sideC) < sideA) {
            throw new IllegalArgumentException("Сторона треугольника меньше 0");
        }
        if (sideA < 0 || sideB < 0 || sideC < 0){
            throw new IllegalArgumentException("Сумма двух любых сторон должна быть не меньше третьей стороны");
        }
    }

     public void SquareTriangle(Triangle triangle) {
        System.out.println(String.format("Площадь треугольника по формуле Герона = %f ", triangle.area()));
            }
     public void printTrianglePerimeter(Triangle triangle) {
        System.out.println(String.format("Периметр треугольника со сторонами %f  %f  %f  = %f ", triangle.sideA, triangle.sideB, triangle.sideC, triangle.PerimeterTriangle()));

    }

    public  double PerimeterTriangle() {
        return (this.sideA + this.sideB + this.sideC);
    }

    public  double area() {
          return Math.sqrt(PerimeterTriangle()/2*(PerimeterTriangle()/2-this.sideA)*(PerimeterTriangle()/2-this.sideB)*(PerimeterTriangle()/2-this.sideC));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return (Double.compare(triangle.sideA, this.sideA) == 0 && Double.compare(triangle.sideB, this.sideB) == 0 && Double.compare(triangle.sideC, this.sideC) == 0)
                || (Double.compare(triangle.sideA, this.sideC) == 0 && Double.compare(triangle.sideB, this.sideA) == 0 && Double.compare(triangle.sideC, this.sideB) == 0)
                || (Double.compare(triangle.sideA, this.sideB) == 0 && Double.compare(triangle.sideB, this.sideC) == 0 && Double.compare(triangle.sideC, this.sideA) == 0)
                || (Double.compare(triangle.sideA, this.sideC) == 0 && Double.compare(triangle.sideB, this.sideB) == 0 && Double.compare(triangle.sideC, this.sideA) == 0)
                || (Double.compare(triangle.sideA, this.sideA) == 0 && Double.compare(triangle.sideB, this.sideC) == 0 && Double.compare(triangle.sideC, this.sideB) == 0)
                || (Double.compare(triangle.sideA, this.sideB) == 0 && Double.compare(triangle.sideB, this.sideA) == 0 && Double.compare(triangle.sideC, this.sideC) == 0);


    }

    @Override
    public int hashCode() {
        return 1;
    }

}