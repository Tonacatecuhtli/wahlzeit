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
    public static NebulaPhotoManager getInstance() {
        log.info(new Object(){}.getClass().getName() + " " + new Object(){}.getClass().getEnclosingMethod().getName());
        return instance;
    }
}
