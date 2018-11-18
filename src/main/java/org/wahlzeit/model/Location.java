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

public class Location {
    /**
     *
     */
    protected CartesianCoordinate cartesianCoordinate;

    /**
     * create a location directly with a cartesianCoordinate
     */
    public Location(CartesianCoordinate cartesianCoordinate){
        this.cartesianCoordinate = cartesianCoordinate;
    }

    /**
     * create a location and cartesianCoordinate by supplying coordinates.
     */
    public Location(double x, double y, double z) {
        this.cartesianCoordinate = new CartesianCoordinate(x, y, z);
    }

    /**
     * @methodtype get
     */
    public CartesianCoordinate getCoordinate() {
        return cartesianCoordinate;
    }

    /**
     * @methodtype set
     */
    public void setCoordinate(CartesianCoordinate cartesianCoordinate) {
        this.cartesianCoordinate = cartesianCoordinate;
    }
}
