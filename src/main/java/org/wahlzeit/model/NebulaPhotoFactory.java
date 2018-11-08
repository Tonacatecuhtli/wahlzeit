package org.wahlzeit.model;

import org.wahlzeit.services.LogBuilder;

import java.util.logging.Logger;

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
        getInstance(); // drops result due to getInstance() side-effects
    }

    /**
     * Public singleton access method.
     */
    public static synchronized NebulaPhotoFactory getInstance() {
        if (instance == null) {
            log.config(LogBuilder.createSystemMessage().addAction("setting generic NebulaPhotoFactory").toString());
            setInstance(new NebulaPhotoFactory());
        }

        return instance;
    }
}
