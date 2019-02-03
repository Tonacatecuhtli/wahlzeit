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

import org.wahlzeit.utils.PatternInstance;

import java.util.logging.Logger;

@PatternInstance(
        patternName = "Abstract Factory",
        participants = {
                "AbstractProduct: Photo",
                "ConcreteProduct: NebulaPhoto"
        }
)

/**
 * Creation of a NebulaPhoto
 *
 * Sequence of calls leading to a new object:
 *
 * the creation of NebulaPhotos is handled by the NebulaPhotoFactory,
 * which supplies a createPhoto factory method. This is called in PhotoUtils createPhoto method.
 * which in turn is called by NebulaPhotoManager because PhotoManager actually instantiates a NebulaPhotoManger.
 * In short:
 * NebulaPhotoManager.createPhoto(...) -> PhotoUtils.createPhoto(...) -> NebulaPhotoFactory.createPhoto(...)
 *
 * Design Space solution
 *
 * 1. Delegation: separate-object
 * 2. Selection: By-subclassing
 * 3. Configuration: In-code
 * 4: Instantiation: By-class-object
 * 5: Initialization: In-second-step
 * 6: Building: Default
 */
public class NebulaPhoto extends Photo {

    // NebulaPhoto-Nebula-Collaboration
    /**
     * @methodType getter
     * @return
     */
    public Nebula getNebula() {
        return nebula;
    }
    private Nebula nebula;

    // Client-NebulaPhoto-Collaboration
    /**
     * @methodtype constructor
     */
    public NebulaPhoto() {
        // log.info("Default NebulaPhoto constructor");
        id = PhotoId.getNextId();
        incWriteCount();
    }
    /**
     * @methodtype constructor
     */
    public NebulaPhoto(PhotoId myId) {
        // log.info("Id NebulaPhoto constructor");
        id = myId;
        incWriteCount();
    }

    // Client-NebulaPhoto-NebulaManager-Collaboration
    /**
     * @methodtype constructor
     */
    public NebulaPhoto(PhotoId myId, String typeName, Integer discoveryTimestamp, Location location){
        id = myId;
        nebula = NebulaManager.getInstance().createNebula(typeName, discoveryTimestamp, location);
    }
}
