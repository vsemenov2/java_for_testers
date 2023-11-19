import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.geometry.figures.Triangle;

public class TriangleTest {
    @Test
    void canCalculatePerimeterTriangle() {
            var triangle = new Triangle(4,5,6);
            double result = triangle.areaTriangle();
            Assertions.assertEquals(9.921567416492215, result);
        }


    @Test
    void canCalculateTriangle() {
        Assertions.assertEquals(18, new  Triangle(5.0, 6.0, 7.0).PerimeterTriangle());

    }

    @Test
    void TriangleTest () {
        try {
            new Triangle(-5.0, 6.0, 7.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception){
        }
    }
    @Test
    void triangleSidesTest(){
        try {
            new Triangle(4.0, 5.0, 15.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception){

        }
    }

    @Test
    void testEquality(){
        var a = 3;
        var b = 4;
        var c = 5;
        var t1= new Triangle(a, b, c);
        var t2 = new Triangle(a, c, b);
        var t3 = new Triangle(b, c, a);
        var t4 = new Triangle(b, a, c);
        var t5 = new Triangle(c, b, a);
        var t6 = new Triangle(c, a, b);
        Assertions.assertTrue(t1.equals(t2)&t1.equals(t3)&t1.equals(t3)&t1.equals(t4)&t1.equals(t5)&t1.equals(t6));
    }
}
