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

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Work;
import org.wahlzeit.services.LogBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

public class NebulaPhotoManager extends PhotoManager {
    /**
     *
     */
    protected static final NebulaPhotoManager instance = new NebulaPhotoManager();

    private static final Logger log = Logger.getLogger(PhotoManager.class.getName());

    /**
     *
     */
    public static NebulaPhotoManager getInstance() {
        log.info(new Object(){}.getClass().getName() + " " + new Object(){}.getClass().getEnclosingMethod().getName());
        return instance;
    }

    /**
     * @methodtype command
     *
     * Load all persisted photos. Executed when Wahlzeit is restarted.
     */
    public void loadPhotos() {
        Collection<NebulaPhoto> existingPhotos = ObjectifyService.run(new Work<Collection<NebulaPhoto>>() {
            @Override
            public Collection<NebulaPhoto> run() {
                Collection<NebulaPhoto> existingPhotos = new ArrayList<NebulaPhoto>();
                readObjects(existingPhotos, NebulaPhoto.class);
                return existingPhotos;
            }
        });

        for (NebulaPhoto photo : existingPhotos) {
            if (!doHasPhoto(photo.getId())) {
                log.config(LogBuilder.createSystemMessage().
                        addParameter("Load NebulaPhoto with ID", photo.getIdAsString()).toString());
                loadScaledImages(photo);
                doAddPhoto(photo);
            } else {
                log.config(LogBuilder.createSystemMessage().
                        addParameter("Already loaded NebulaPhoto", photo.getIdAsString()).toString());
            }
        }

        log.info(LogBuilder.createSystemMessage().addMessage("All NebulaPhotos loaded.").toString());
    }
}
