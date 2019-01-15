package sample;

import org.junit.Assert;
import org.junit.Test;

public class PointTest {

    @Test
    public void compareTo() {
        Point point1 = new Point (-100, -100);
        Point point2 = new Point(100, -99);
        Point point3 = new Point(101, -101);
        Point point4 = new Point(101, -101);
        Assert.assertTrue(point1.compareTo(point2) < 0);
        Assert.assertTrue(point2.compareTo(point3) < 0);
        Assert.assertEquals(point4, point3);
        //compare the other way around
        Assert.assertTrue(point2.compareTo(point1) > 0);
        Assert.assertTrue(point3.compareTo(point2) > 0);
        Assert.assertEquals(point3, point4);
    }
}