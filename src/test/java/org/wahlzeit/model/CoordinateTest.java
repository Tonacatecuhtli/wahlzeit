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
import static org.junit.Assert.*;

public class CoordinateTest {

    private Coordinate c1;
    private Coordinate c2;
    private double c1GetDistanceC2;

    // delta of which assertEquals double, test variables can be different
    private static final double epsilon = 1e-10;

    @Before
    public void setUp () {
        c1 = new Coordinate(1,1,1);
        c2 = new Coordinate(2,2,2);
        // c2 with 2,2,2 - c1 with 1,1,1 = 1.7320508075688772;
        c1GetDistanceC2 = 1.7320508075688772;
    }

    @Test
    public void testIsEqual() {
        assertTrue(c1.isEqual(c1));
        assertFalse(c1.isEqual(c2));
    }

    @Test
    public void testDistance() {
        assertEquals(c1.getDistance(c2),c1GetDistanceC2, epsilon);
        assertEquals(c1.getDistance(c1), 0, epsilon);
    }

    @After
    public void tearDown () {
        // remove references so that garbage collection will delete the objects.
        c1 = null;
        c2 = null;
    }

}
