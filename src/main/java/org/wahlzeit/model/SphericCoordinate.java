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
     * latitude = phi in rad
     */
    private double latitude;

    /**
     *
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * longitude = theta in rad
     */
    private double longitude;

    /**
     *
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     *
     */
    private double radius;

    /**
     *
     */
    public double getRadius() {
        return radius;
    }

    /**
     *
     */
    public SphericCoordinate(double latitude, double longitude, double radius) {
        // normalize default is set to true
        constructSphericCoordinate(latitude, longitude, radius, true);
    }

    /**
     *
     */
    public SphericCoordinate(double latitude, double longitude, double radius, boolean normalize) {
        constructSphericCoordinate(latitude, longitude, radius, normalize);
    }

    /**
     * @methodType helper
     * @param latitude in rad
     * @param longitude in rad
     * @param radius
     * @param normalize boolean default = true
     */
    private void constructSphericCoordinate(double latitude, double longitude, double radius, boolean normalize) {
        assertClassInvariants(latitude, longitude, radius);

        if (normalize) {
            latitude = normalizeRadAngle(latitude);
            longitude = normalizeRadAngle(longitude);
        } else {
            assertValidRad(latitude, longitude);
        }

        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
    }

    /**
     * @param angle a radial angle
     * @return
     */
    private double normalizeRadAngle(double angle) {
        double twoPi = 2 * Math.PI;
        return angle % twoPi;
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

    /**
     * checks if an angle is <= 2 pi
     *
     * @param latitude  in rad
     * @param longitude in rad
     * @methodType assertion
     */
    protected void assertValidRad(double latitude, double longitude) throws IllegalArgumentException {
        double twoPi = 2 * Math.PI;
        if (latitude > twoPi || longitude > twoPi)
            throw new IllegalArgumentException("If you aren't normalizing radiants shouldnt be bigger than two pi");

    }
}