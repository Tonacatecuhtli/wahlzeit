package org.wahlzeit.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CoordinateTest {

    private Coordinate c1;
    private Coordinate c2;

    // delta of which assertEquals double, test variables can be different
    private static final double epsilon = 1e-10;

    @Before
    public void setUp () {
        c1 = new Coordinate(1,1,1);
        c2 = new Coordinate(2,2,2);
    }

    @Test
    public void testIsEqual() {
        assertTrue(c1.isEqual(c1));
        assertFalse(c1.isEqual(c2));
    }

    @Test
    public void testDistance() {
        // c2 with 2,2,2 - c1 with 1,1,1 = 1.7320508075688772;
        assertEquals(c1.getDistance(c2),1.7320508075688772, epsilon);
        assertEquals(c1.getDistance(c1), 0, epsilon);
    }

    @After
    public void tearDown () {
        // remove references so that garbage collection will delete the objects.
        c1 = null;
        c2 = null;
    }

}
