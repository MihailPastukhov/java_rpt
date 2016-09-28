package ru.stqa.pft.sandbox;


import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Michael on 28.09.2016.
 */

public class PointTests {
    @Test
    public void testDistance(){
        Point a = new Point(1,1);
        Point b = new Point(5, 1);
        Assert.assertEquals(a.distance(b), 4.0);
    }


}
