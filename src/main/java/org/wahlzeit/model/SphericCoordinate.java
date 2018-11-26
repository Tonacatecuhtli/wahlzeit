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

public class SphericCoordinate extends AbstractCoordinate {
    /**
     * latitude = phi;
     */
    private double latitude;

    public double getLatitude() {
        return latitude;
    }

    /**
     * longitude = theta;
     */
    private double longitude;

    public double getLongitude() {
        return longitude;
    }

    /**
     *
     */
    private double radius;

    public double getRadius() {
        return radius;
    }

    public SphericCoordinate(double latitude, double longitude, double radius) {
        assertClassInvariants(latitude, longitude, radius);
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
    }

    /**
     * @methodtype conversion
     * @methodproterty primitive, hook
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return new CartesianCoordinate(this.latitude, this.longitude, this.radius);
    }

    /**
     * @methodtype conversion
     * @methodproterty primitive, hook
     */
    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }

    /**
     * @param coordinate Coordinate
     * @return the actual arc length d on a sphere of radius r
     */
    public double getActualArcLength(Coordinate coordinate) {
        double centralAngle = getCentralAngle(coordinate);
        SphericCoordinate sc = coordinate.asSphericCoordinate();
        return sc.radius * centralAngle;
    }

    /**
     * @param coordinate
     * @methodtype conversion
     * @methodproterty primitive, hook
     */
    @Override
    protected boolean doIsEqual(Coordinate coordinate) {
        SphericCoordinate sphericCoordinate = coordinate.asSphericCoordinate();
        return this.latitude == sphericCoordinate.latitude && this.longitude == sphericCoordinate.longitude && this.radius == sphericCoordinate.radius;
    }

    /**
     * make sure no coordinates are null
     * make sure no coordinates are negative
     *
     * @param arg1 Coordinate parameter 1
     * @param arg2 Coordinate parameter 2
     * @param arg3 Coordinate parameter 3
     * @methodType assertion
     */
    @Override
    protected void assertClassInvariants(double arg1, double arg2, double arg3) {
        // assertNotNull
        assertNotNull(arg1);
        assertNotNull(arg2);
        assertNotNull(arg3);
        // assertNotNegative
        assertNotNegative(arg1);
        assertNotNegative(arg2);
        assertNotNegative(arg3);
    }
}