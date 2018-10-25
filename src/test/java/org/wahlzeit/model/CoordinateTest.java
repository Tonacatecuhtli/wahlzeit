package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class CoordinateTest {

    private Coordinate c1 = new Coordinate(1,1,1);
    private Coordinate c2 = new Coordinate(2,2,2);

    @Test
    public void testIsEqual() {
        assertTrue(c1.isEqual(c1));
        assertFalse(c1.isEqual(c2));
    }


}
