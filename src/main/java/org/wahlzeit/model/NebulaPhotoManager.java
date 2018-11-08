package org.wahlzeit.model;

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
    public static final NebulaPhotoManager getInstance() {
        return instance;
    }

    /**
     *
     */
    public Photo getPhotoFromId(PhotoId id) {
        if (id == null) {
            return null;
        }

        Photo result = doGetPhotoFromId(id);

        if (result == null) {
            result = NebulaPhotoFactory.getInstance().loadPhoto(id);
            if (result != null) {
                doAddPhoto(result);
            }
        }

        return result;
    }
}
