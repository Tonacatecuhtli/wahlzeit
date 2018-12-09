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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.*;

public class CoordinateTest {

    private static final Logger log = Logger.getLogger(CoordinateTest.class.getName());


    private CartesianCoordinate cc1;
    private CartesianCoordinate cc2;
    private SphericCoordinate sc1;
    private SphericCoordinate sc2;
    private SphericCoordinate sc3;

    private double cartesianDistanceCc1Cc2;
    private double centralAngleSc1Sc2;
    private double actualArcLengthSc1Sc3;

    private double twoPi = 2 * Math.PI;
    private double bigLatitude = 7;
    private double bigLongitude = 7;

    // delta of which assertEquals double, test variables can be different
    private static final double epsilon = 1e-10;

    @Before
    public void setUp() throws CoordinateException {
        cc1 = new CartesianCoordinate(1, 1, 1);
        cc2 = new CartesianCoordinate(2, 2, 2);
        // cc2 with 2,2,2 - cc1 with 1,1,1 = sqrt(3)
        cartesianDistanceCc1Cc2 = Math.sqrt(3);

        sc1 = new SphericCoordinate(1, 1, 1);
        sc2 = new SphericCoordinate(2, 2, 2);
        sc3 = new SphericCoordinate(3, 4, 5);

        centralAngleSc1Sc2 = 0.8715212348676055;
        actualArcLengthSc1Sc3 = 4.327295325724265;

    }

    @Test(expected = CoordinateException.class)
    public void invalidConstructionTest() throws CoordinateException {
        new SphericCoordinate(-1, -1, -1);
        new CartesianCoordinate(-1, -1, -1);
    }

    @Test
    public void testIsEqual() {
        assertTrue(cc1.isEqual(cc1));
        assertFalse(cc1.isEqual(cc2));
        assertTrue(cc1.isEqual(sc1));
        assertFalse(sc1.isEqual(sc2));
    }

    @Test
    public void testDistance() throws CoordinateException {
        assertEquals(cc1.getCartesianDistance(cc2), cartesianDistanceCc1Cc2, epsilon);
        assertEquals(cc1.getCartesianDistance(cc1), 0, epsilon);
    }

    @Test
    public void testCentralAngle() throws CoordinateException {
        assertEquals(sc1.getCentralAngle(sc2), centralAngleSc1Sc2, epsilon);
    }

    @Test
    public void testActualArcLength() throws CoordinateException {
        assertEquals(sc1.getActualArcLength(sc3), actualArcLengthSc1Sc3, epsilon);
    }

    @Test
    public void ensureInterchangeability() throws CoordinateException {
        assertEquals(cc1.getCentralAngle(sc2), centralAngleSc1Sc2, epsilon);
        assertEquals(sc1.getCentralAngle(cc2), centralAngleSc1Sc2, epsilon);
    }

    @Test
    public void testNormalize() throws CoordinateException {
        SphericCoordinate ss = new SphericCoordinate(this.bigLatitude, this.bigLongitude, 5);
        assertEquals(ss.getLatitude(), this.bigLatitude % twoPi, epsilon);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertValidRad() throws CoordinateException {
        SphericCoordinate ss = new SphericCoordinate(
                this.bigLatitude,
                this.bigLongitude,
                5,
                false
        );
    }

    @After
    public void tearDown() {
        // remove references so that garbage collection will delete the objects.
        cc1 = null;
        cc2 = null;
    }

}
