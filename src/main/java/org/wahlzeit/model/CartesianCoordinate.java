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

public class CartesianCoordinate implements Coordinate {
    /**
     *
     */
    private double x, y, z;

    /**
     *
     */
    public CartesianCoordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * calculate the cartesian distance between this and another CartesianCoordinate
     * @methodproperty primitive
     */
    protected double getDistance(CartesianCoordinate other) {
        double xDif = Math.pow((other.x - this.x), 2);
        double yDif = Math.pow((other.y - this.y), 2);
        double zDif = Math.pow((other.z - this.z), 2);

        return Math.sqrt(xDif + yDif + zDif);
    }

    /**
     *	@methodtype comparision
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
     * @methodtype comparision
     */
    protected boolean isEqual(CartesianCoordinate other) {
        return this.x == other.x && this.y == other.y && this.z == other.z;
    }

    /**
     * @methodtype conversion
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return this;
    }

    /**
     * @param coordinate
     * @methodtype getter
     */
    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        return doGetCartesianDistance(this, coordinate);
    }

    /**
     *
     * @param c1 Coordinate 1
     * @param c2 Coordinate 2
     * @return the cartesian distance between to coordinates
     */
    protected static double doGetCartesianDistance(Coordinate c1, Coordinate c2){
        CartesianCoordinate cc1 = c1.asCartesianCoordinate();
        CartesianCoordinate cc2 = c2.asCartesianCoordinate();
        return cc1.getDistance(cc2);
    }

    /**
     * @methodtype conversion
     */
    @Override
    public SphericCoordinate asSphericCoordinate() {
        return new SphericCoordinate(this.x, this.y, this.z);
    }

    /**
     * @param coordinate
     * @methodtype getter
     */
    @Override
    public double getCentralAngle(Coordinate coordinate) {
        return SphericCoordinate.doGetCentralAngle(this, coordinate);
    }

    /**
     * @param coordinate
     * @methodtype comparision
     */
    @Override
    public boolean isEqual(Coordinate coordinate) {
        CartesianCoordinate cartesianCoordinate = coordinate.asCartesianCoordinate();
        return isEqual(cartesianCoordinate);
    }
}
