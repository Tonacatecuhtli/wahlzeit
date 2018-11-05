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

public class Coordinate {
    /**
     *
     */
    private double x, y, z;

    /**
     *
     */
    public Coordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * calculate the cartesian distance between this and another Coordinate
     * @methodproperty primitive
     */
    protected double getDistance(Coordinate other) {
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
        if (!Coordinate.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        final Coordinate other = (Coordinate) obj;
        return this.isEqual(other);
    }

    /**
     * @methodtype comparision
     */
    protected boolean isEqual(Coordinate other) {
        return this.x == other.x && this.y == other.y && this.z == other.z;
    }
}
