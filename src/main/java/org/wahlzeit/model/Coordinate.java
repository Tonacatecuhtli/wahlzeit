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

public interface Coordinate {

    /**
     * @methodtype helper constructor
     */
    public Coordinate createCoordinate(double arg1, double arg2, double arg3) throws CoordinateException;

    /**
     *	@methodtype conversion
     */
    public CartesianCoordinate asCartesianCoordinate() throws CoordinateException;

    /**
     *  @param coordinate
     *	@methodtype getter
     */
    public double getCartesianDistance(Coordinate coordinate) throws CoordinateException;

    /**
     *	@methodtype conversion
     */
    public SphericCoordinate asSphericCoordinate() throws CoordinateException;

    /**
     *  @param coordinate
     *	@methodtype getter
     */
    public double getCentralAngle(Coordinate coordinate) throws CoordinateException;

    /**
     *  @param coordinate
     *	@methodtype comparision
     */
    public boolean isEqual(Coordinate coordinate);
}
