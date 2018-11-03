/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class LocationTest {

    private Location location;

    @Before
    public void setUp() {
        this.location = new Location(5,4,3);
    }

    @Test
    public void testSetCoordinate() {
        Coordinate coordinate = new Coordinate(3,3,3);

        location.setCoordinate(coordinate);
        assertEquals(coordinate, location.getCoordinate());
    }

    @Test
    public void testConstructorWithCoordinate() {
        Coordinate coordinate = new Coordinate(3,3,3);
        new Location(coordinate);
    }

    @Test
    public void testConstructorWithCoordinates() {
        new Location(1,2,3);
    }

    @Test
    public void getCoordinatesTest() {
        Coordinate locationCoordinate = location.getCoordinate();
        assertTrue(locationCoordinate.getClass().getName() == Coordinate.class.getName());
    }
}
