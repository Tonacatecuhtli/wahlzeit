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

public class CartesianCoordinate extends AbstractCoordinate{

    /**
     *
     */
    private double x;
    public double getX() {
        return x;
    }

    /**
     *
     */
    private double y;
    public double getY() {
        return y;
    }

    /**
     *
     */
    private double z;
    public double getZ() {
        return z;
    }

    /**
     *
     */
    public CartesianCoordinate(double x, double y, double z) {
        if(isValid(x) && isValid(y) && isValid(z)){
            this.x = x;
            this.y = y;
            this.z = z;
        } else {
            throw new IllegalArgumentException("All parameters must be >= 0");
        }
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
    public SphericCoordinate asSphericCoordinate() {
        return new SphericCoordinate(this.x, this.y, this.z);
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
     * @param coordinate
     * @methodtype conversion
     * @methodproterty primitive, hook
     */
    @Override
    protected boolean doIsEqual(Coordinate coordinate) {
        CartesianCoordinate cartesianCoordinate = coordinate.asCartesianCoordinate();
        return  this.x == cartesianCoordinate.x && this.y == cartesianCoordinate.y && this.z == cartesianCoordinate.z;
    }
}