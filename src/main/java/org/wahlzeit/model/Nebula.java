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

/**
 * Creation of a Nebula
 *
 * the creation of a Nebula is handled by the NebulaManager,
 * which supplies a createNebula factory method. Which also creates a NebulaType if it
 * doesn't exists jet.
 * It also supplies a createNebulaType factory method with which new NebulaTypes can be created.
 * A NebulaPhotos constructor uses the NebulaManager to create a Nebula.
 *
 * NebulaPhotoManager(...) -> NebulaPhotoManager.createNebula(...) -> NebulaType(...) -> Nebula(...)
 *
 * Design Space solution
 *
 * 1. Delegation: separate-object
 * 2. Selection: By-mapping
 * 3. Configuration: In-code
 * 4: Instantiation: In-code
 * 5: Initialization: Default
 * 6: Building: Default
 */
public class Nebula {
    final private int discoverTimestamp;
    final private Location location;
    final private NebulaType type;
    /**
     * @methodType constructor
     */
    public Nebula (NebulaType type, Integer discoverTimestamp, Location location){
        this.discoverTimestamp = discoverTimestamp;
        this.type = type;
        this.location = location;
    }
    /**
     * @methodType getter
     */
    public NebulaType getType() {
        return type;
    }
    /**
     * @methodType getter
     */
    public int getDiscoverTimestamp() {
        return discoverTimestamp;
    }
    /**
     * @methodType getter
     */
    public Location getLocation() {
        return location;
    }
}
