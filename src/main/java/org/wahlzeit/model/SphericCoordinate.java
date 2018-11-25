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

public class SphericCoordinate implements Coordinate {
    /**
     * latitude = phi;
     */
    private double latitude;
    /**
     * longitude = theta;
     */
    private double longitude;
    /**
     *
     */
    private double radius;

    public SphericCoordinate(double latitude, double longitude, double radius){
        if(isValid(latitude) && isValid(longitude) && isValid(radius)){
            this.latitude = latitude;
            this.longitude = longitude;
            this.radius = radius;
        } else {
            throw new IllegalArgumentException("All parameters must be >= 0");
        }
    }

    /**
     * @methodType boolean query
     * @methodproperty primitive
     * @param input one of the constructor arguments
     * @return boolean
     */
    private boolean isValid (double input) {
        return input >= 0;
    }

    /**
     * @methodtype conversion
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return new CartesianCoordinate(this.latitude, this.longitude, this.radius);
    }

    /**
     * @param coordinate
     * @methodtype getter
     */
    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        return CartesianCoordinate.doGetCartesianDistance(this, coordinate);
    }

    /**
     * @methodtype conversion
     */
    @Override
    public SphericCoordinate asSphericCoordinate() {
       return this;
    }

    /**
     * @param coordinate
     * @methodtype getter
     */
    @Override
    public double getCentralAngle(Coordinate coordinate) {
        // todo split into assertion and primitive methods


        return doGetCentralAngle(coordinate);
    }

    public double doGetCentralAngle(Coordinate other){
        SphericCoordinate sc = other.asSphericCoordinate();
        double centralAngle = Math.acos(
            Math.sin(this.latitude) * Math.sin(sc.latitude)
            +
            Math.cos(this.latitude) * Math.cos(sc.latitude) * Math.cos(Math.abs(sc.longitude-this.longitude))
        );

        return centralAngle;
    }

    /**
     *
     * @param coordinate Coordinate
     * @return the actual arc length d on a sphere of radius r
     */
    public double getActualArcLength(Coordinate coordinate){
        double centralAngle = doGetCentralAngle(this, coordinate);
        SphericCoordinate sc = coordinate.asSphericCoordinate();
        return sc.radius * centralAngle;
    }
k
    /**
     * @param other the SphericalCoordinate to compare to
     * @methodtype comparision
     */
    protected boolean isEqual(SphericCoordinate other) {
        return this.latitude == other.latitude && this.longitude == other.longitude && this.radius == other.radius;
    }

    /**
     * @param coordinate
     * @methodtype comparision
     */
    @Override
    public boolean isEqual(Coordinate coordinate) {
        SphericCoordinate sphericCoordinate = coordinate.asSphericCoordinate();
        return isEqual(sphericCoordinate);
    }
}
