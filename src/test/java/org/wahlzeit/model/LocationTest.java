package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class LocationTest {

    private Location location = new Location();

    @Before
    public void setUp () {
        Coordinate a = new Coordinate(3,3,3);
        location.setCoordinate(a);
    }

    @Test
    public void getCoordinatesTest () {
        Coordinate locationCoordinate = location.getCoordinate();
        assertTrue(locationCoordinate.getClass().getName() == Coordinate.class.getName());
    }
}
