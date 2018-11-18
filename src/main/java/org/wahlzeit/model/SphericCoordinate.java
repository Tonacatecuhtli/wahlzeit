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

    /**
     * @methodtype conversion
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return null;
    }

    /**
     * @param coordinate
     * @methodtype getter
     */
    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        return 0;
    }

    /**
     * @methodtype conversion
     */
    @Override
    public SphericCoordinate asSphericCoordinate() {
       return null;
    }

    /**
     * @param coordinate
     * @methodtype getter
     */
    @Override
    public double getCentralAngle(Coordinate coordinate) {
        // todo split into assertion and primitive methods

        return 0;
    }

    protected static double doGetCentralAngle(Coordinate c1, Coordinate c2){
        SphericCoordinate c1s = c1.asSphericCoordinate();
        SphericCoordinate c2s = c1.asSphericCoordinate();

        double centralAngle = Math.acos(
            Math.sin(c1s.latitude) * Math.sin(c2s.latitude)
            +
            Math.cos(c1s.latitude) * Math.cos(c2s.latitude) * Math.cos(Math.abs(c2s.longitude-c1s.longitude))
        );

        return centralAngle;
    }

    /**
     *
     * @param c1 Coordinate 1
     * @param c2 Coordinate 2
     * @return the actual arc length d on a sphere of radius r
     */
    public double getActualArcLength(Coordinate c1, Coordinate c2){
        double centralAngle = doGetCentralAngle(c1, c2);
        SphericCoordinate c1s = c1.asSphericCoordinate();
        return c1s.radius * centralAngle;
    }

    /**
     * @param coordinate
     * @methodtype comparision
     */
    @Override
    public boolean isEqual(Coordinate coordinate) {
        return false;
    }
}
