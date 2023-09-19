package ru.stqa.geometry.figures;

public record Triangle() {
     public static void SquareTriangle(double sideA, double sideB, double sideC) {
        var perimeterTriangle = (sideA + sideB + sideC) / 2;
        var areaTriangle = Math.sqrt(perimeterTriangle * (perimeterTriangle - sideA) * (perimeterTriangle - sideB) * (perimeterTriangle - sideC));
        System.out.println(String.format("Периметр треугольника со сторонами %f  %f  %f  = %f ", sideA, sideB, sideC, perimeterTriangle));
        System.out.println(String.format("Площадь треугольника по формуле Герона = %f ", areaTriangle));

            }

}