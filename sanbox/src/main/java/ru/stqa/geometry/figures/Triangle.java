package ru.stqa.geometry.figures;

public record Triangle() {
     public static void SquareTriangle(double sideA, double sideB, double sideC) {
         var perimeterTriangle = getPerimeterTriangle(sideA, sideB, sideC);
         var areaTriangle = getTriangle(sideA, sideB, sideC, perimeterTriangle);
        System.out.println(String.format("Периметр треугольника со сторонами %f  %f  %f  = %f ", sideA, sideB, sideC, perimeterTriangle));
        System.out.println(String.format("Площадь треугольника по формуле Герона = %f ", areaTriangle));


         /*var perimeterTriangle = (sideA + sideB + sideC) / 2;
         System.out.println("Периметр треугольника = " + perimeterTriangle);
         System.out.println("Площадь треугольника по формуле Герона =  " + Math.sqrt(perimeterTriangle * (perimeterTriangle - sideA) * (perimeterTriangle - sideB) * (perimeterTriangle - sideC)));*/




            }

    public static double getPerimeterTriangle(double sideA, double sideB, double sideC) {
        return (sideA + sideB + sideC) / 2;
    }

    public static double getTriangle(double sideA, double sideB, double sideC, double perimeterTriangle) {
        return Math.sqrt(perimeterTriangle * (perimeterTriangle - sideA) * (perimeterTriangle - sideB) * (perimeterTriangle - sideC));
    }

}