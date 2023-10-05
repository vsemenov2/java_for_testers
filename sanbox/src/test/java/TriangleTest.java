import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.geometry.figures.Triangle;

public class TriangleTest {
    @Test
    void canCalculatePerimeterTriangle() {
        Assertions.assertEquals(16.0, Triangle.getPerimeterTriangle(5., 5., 6.));

    }

    @Test
    void canCalculateTriangle() {
        Assertions.assertEquals(14.696938456699069, Triangle.getTriangle(5.0, 6.0, 7.0));

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
        var t1= new Triangle(3.0, 4.0, 5.0);
        var t2 = new Triangle(5.0, 3.0, 4.0);
        Assertions.assertTrue(t1.equals(t2));
    }
}
