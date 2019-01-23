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
import org.wahlzeit.services.LogBuilder;

import java.util.logging.Logger;

@PatternInstance(
        patternName = "Abstract Factory",
        participants = {
                "AbstractFactory: PhotoFactory",
                "ConcreteFactory: NebulaPhotoFactory"
        }
)
public class NebulaPhotoFactory extends PhotoFactory {

    private static final Logger log = Logger.getLogger(NebulaPhotoFactory.class.getName());
    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    private static NebulaPhotoFactory instance = null;

    public NebulaPhotoFactory() {
        // do nothing
    }

    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    public static void initialize() {
        log.info(new Object(){}.getClass().getName() + " " + new Object(){}.getClass().getEnclosingMethod().getName());

        getInstance(); // drops result due to getInstance() side-effects
    }

    /**
     * Public singleton access method.
     */
    public static synchronized NebulaPhotoFactory getInstance() {
        log.info(new Object(){}.getClass().getName() + " " + new Object(){}.getClass().getEnclosingMethod().getName());

        if (instance == null) {
            log.config(LogBuilder.createSystemMessage().addAction("setting generic NebulaPhotoFactory").toString());
            setInstance(new NebulaPhotoFactory());
        }

        return instance;
    }
    /**
     * Method to set the singleton instance of NebulaPhotoFactory.
     */
    protected static synchronized void setInstance(NebulaPhotoFactory nebulaPhotoFactory) {
        assertInstanceIsNull(instance);


        instance = nebulaPhotoFactory;
    }


    /**
     * @methodtype factory
     */
    public NebulaPhoto createPhoto() {
        log.info(new Object(){}.getClass().getName() + " " + new Object(){}.getClass().getEnclosingMethod().getName());

        return new NebulaPhoto();
    }

    /**
     * Creates a new photo with the specified id
     */
    public NebulaPhoto createPhoto(PhotoId id) {
        log.info(new Object(){}.getClass().getName() + " " + new Object(){}.getClass().getEnclosingMethod().getName());

        return new NebulaPhoto(id);
    }

    /**
     * @methodtype factory
     */
    public NebulaPhoto createPhoto(PhotoId myId, String typeName, Integer discoveryTimestamp, Location location){
        return new NebulaPhoto(myId, typeName, discoveryTimestamp, location);
    }
}
