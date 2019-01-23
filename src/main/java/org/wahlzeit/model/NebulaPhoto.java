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
public class NebulaPhoto extends Photo {

    /**
     * @methodType getter
     * @return
     */
    public Nebula getNebula() {
        return nebula;
    }

    private Nebula nebula;

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

    /**
     * @methodtype constructor
     */
    public NebulaPhoto(String typeName, int discoveryTimestamp, Location location){
        nebula = NebulaManager.getInstance().createNebula(typeName, discoveryTimestamp, location);
    }
}
