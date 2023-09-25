package ru.stqa.geometry.figures;

public record Triangle() {
     public static void SquareTriangle(double sideA, double sideB, double sideC) {
        if (sideA < 0 || sideB < 0 || sideC < 0) {
            throw new ArithmeticException("Сторона треугольника меньше 0");
         }
        if ((sideA+sideB) < sideC || (sideA + sideC) < sideB || (sideB + sideC) < sideA ){
            throw new ArithmeticException("Сумма двух любых сторон должна быть не меньше третьей стороны");
        }
         var perimeterTriangle = getPerimeterTriangle(sideA, sideB, sideC);
         var semiPerimeterTriangle = getSemiPerimeterTriangle(sideA, sideB);
         var areaTriangle = getTriangle(sideA, sideB, sideC, semiPerimeterTriangle);
        System.out.println(String.format("Периметр треугольника со сторонами %f  %f  %f  = %f ", sideA, sideB, sideC, perimeterTriangle));
        System.out.println(String.format("Площадь треугольника по формуле Герона = %f ", areaTriangle));

            }

    private static double getSemiPerimeterTriangle(double sideA, double sideB) {
        return getPerimeterTriangle(sideA, sideB, sideB) / 2;
    }

    public static double getPerimeterTriangle(double sideA, double sideB, double sideC) {
        return (sideA + sideB + sideC);
    }

    public static double getTriangle(double sideA, double sideB, double sideC, double getSemiPerimeterTriangle) {

        return Math.sqrt(getSemiPerimeterTriangle * (getSemiPerimeterTriangle - sideA) * (getSemiPerimeterTriangle - sideB) * (getSemiPerimeterTriangle - sideC));
    }

}