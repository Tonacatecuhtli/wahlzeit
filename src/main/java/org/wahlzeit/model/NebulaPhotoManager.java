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

    /**
     * @methodtype init Loads all Photos from the Datastore and holds them in the cache
     */
    public void init() {
        loadPhotos();
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
                        addParameter("Load Photo with ID", photo.getIdAsString()).toString());
                loadScaledImages(photo);
                doAddPhoto(photo);
            } else {
                log.config(LogBuilder.createSystemMessage().
                        addParameter("Already loaded Photo", photo.getIdAsString()).toString());
            }
        }

        log.info(LogBuilder.createSystemMessage().addMessage("All photos loaded.").toString());
    }
}
