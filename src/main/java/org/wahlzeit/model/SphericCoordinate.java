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

public class SphericCoordinate extends AbstractCoordinate {

    private static final Logger log = Logger.getLogger(SphericCoordinate.class.getName());

    /**
     * latitude = phi in rad
     */
    private final double latitude;

    /**
     * @methodType getter
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * longitude = theta in rad
     */
    private final double longitude;

    /**
     * @methodType getter
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     *
     */
    private final double radius;

    /**
     * @methodType getter
     */
    public double getRadius() {
        return radius;
    }

    /**
     *
     */
    private boolean normalize = true;

    private static final HashMap<String, SphericCoordinate> sphericCoordinateHashMap = new HashMap<>();

    /**
     * @param arg1 argument one
     * @param arg2 argument two
     * @param arg3 argument three
     * @methodtype helper constructor
     */

    public static synchronized SphericCoordinate createCoordinate(double arg1, double arg2, double arg3) throws CoordinateException {
        String id = getId(arg1,arg2,arg3);
        SphericCoordinate coordinate = sphericCoordinateHashMap.get(id);
        if(coordinate != null){
            // log.info("SphericCoordinate exists");
            return coordinate;
        } else {
            // log.info("SphericCoordinate new");
            coordinate = new SphericCoordinate(arg1, arg2, arg3);
            id = coordinate.getId(arg1, arg2, arg3);
            sphericCoordinateHashMap.put(id, coordinate);
            return coordinate;
        }
    }

    /**
     * @param arg1 argument one
     * @param arg2 argument two
     * @param arg3 argument three
     * @methodtype helper constructor
     */

    public static synchronized SphericCoordinate createCoordinate(double arg1, double arg2, double arg3, boolean normilaze) throws CoordinateException {
        String id = getId(arg1,arg2,arg3);
        SphericCoordinate coordinate = sphericCoordinateHashMap.get(id);
        if(coordinate != null){
            log.info("SphericCoordinate exists");
            return coordinate;
        } else {
            log.info("SphericCoordinate new");
            coordinate = new SphericCoordinate(arg1, arg2, arg3, normilaze);
            id = coordinate.getId(arg1, arg2, arg3);
            sphericCoordinateHashMap.put(id, coordinate);
            return coordinate;
        }
    }

    /**
     * @param latitude  in rad
     * @param longitude in rad
     * @param radius    the radius
     * @methodType constructor
     */
    private SphericCoordinate(double latitude, double longitude, double radius) throws CoordinateException {
        // normalize default is set to true
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
     * @param latitude  in rad
     * @param longitude in rad
     * @param radius    the radius
     * @param normalize boolean default = true
     * @methodType helper
     */
    private SphericCoordinate(double latitude, double longitude, double radius, boolean normalize) throws CoordinateException {
        this.normalize = normalize;
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
     * @return radial angle on a circle without multiple rounds
     * @methodType helper
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
    public CartesianCoordinate asCartesianCoordinate() throws CoordinateException {

        return CartesianCoordinate.createCoordinate(this.latitude, this.longitude, this.radius);
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
    public double getActualArcLength(Coordinate coordinate) throws CoordinateException {
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
        try {
            SphericCoordinate sphericCoordinate = coordinate.asSphericCoordinate();
            return this.latitude == sphericCoordinate.latitude && this.longitude == sphericCoordinate.longitude && this.radius == sphericCoordinate.radius;
        } catch (CoordinateException e) {
            return false;
        }
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
            throw new IllegalArgumentException("If you aren't normalizing, radiants shouldn't be bigger than two pi");

    }
}