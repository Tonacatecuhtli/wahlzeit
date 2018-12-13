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

import java.util.HashMap;
import java.util.logging.Logger;

public class CartesianCoordinate extends AbstractCoordinate {

    private static final Logger log = Logger.getLogger(CartesianCoordinate.class.getName());


    /**
     *
     */
    private double x;

    /**
     * @methodType getter
     */
    public double getX() {
        return x;
    }

    /**
     *
     */
    private double y;

    /**
     * @methodType getter
     */
    public double getY() {
        return y;
    }

    /**
     *
     */
    private double z;

    /**
     * @methodType getter
     */
    public double getZ() {
        return z;
    }


    private static final HashMap<String, CartesianCoordinate> cartesianCoordinateHashMap = new HashMap<>();

    /**
     * @param arg1 argument one
     * @param arg2 argument two
     * @param arg3 argument three
     * @methodtype helper constructor
     */
    public static synchronized CartesianCoordinate createCoordinate(double arg1, double arg2, double arg3) throws CoordinateException {
        String id = getId(arg1, arg2, arg3);
        CartesianCoordinate coordinate = cartesianCoordinateHashMap.get(id);
        if (coordinate != null) {
            // log.info("CartesianCoordinate exists");
            return coordinate;
        } else {
            // log.info("CartesianCoordinate new");
            coordinate = new CartesianCoordinate(arg1, arg2, arg3);
            id = coordinate.getId(arg1, arg2, arg3);
            cartesianCoordinateHashMap.put(id, coordinate);
            return coordinate;
        }
    }

    /**
     *
     */
    private CartesianCoordinate(double x, double y, double z) throws CoordinateException {
        assertClassInvariants(x, y, z);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * @methodtype conversion
     * @methodproterty primitive, hook
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return this;
    }

    /**
     * @methodtype conversion
     * @methodproterty primitive, hook
     */
    @Override
    public SphericCoordinate asSphericCoordinate() throws CoordinateException {
        return SphericCoordinate.createCoordinate(this.x, this.y, this.z);
    }

    /**
     * @methodtype comparision
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        // check if it's a assignable
        if (!CartesianCoordinate.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        final CartesianCoordinate other = (CartesianCoordinate) obj;
        return this.isEqual(other);
    }

    /**
     * @param coordinate
     * @methodtype conversion
     * @methodproterty primitive, hook
     */
    @Override
    protected boolean doIsEqual(Coordinate coordinate) {
        try {
            CartesianCoordinate cartesianCoordinate = coordinate.asCartesianCoordinate();
            return this.x == cartesianCoordinate.x && this.y == cartesianCoordinate.y && this.z == cartesianCoordinate.z;
        } catch (CoordinateException e) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        // create String of properties because the order matters
        String properties = Integer.toString((int) Math.round(this.x)) + Integer.toString((int) Math.round(this.y)) + Integer.toString((int) Math.round(this.z));
        return Integer.valueOf(properties);
    }

    @Override
    public String toString() {
        return "CartesianCoordinate{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", cartesianCoordinateHashMap=" + cartesianCoordinateHashMap.toString() +
                '}';
    }
}