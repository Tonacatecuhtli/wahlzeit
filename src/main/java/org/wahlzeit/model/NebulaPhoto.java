package org.wahlzeit.model;

import java.util.logging.Logger;

public class NebulaPhoto extends Photo {

    private static final Logger log = Logger.getLogger(NebulaPhoto.class.getName());

    private double magnitude;

    /**
     *
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
    public NebulaPhoto(PhotoId myId, double magnitude) {
        // log.info("Id/ magnitude NebulaPhoto constructor");
        id = myId;
        magnitude = magnitude;
        incWriteCount();
    }

    @Override
    public String toString() {
        return "NebulaPhoto{" +
                "magnitude=" + magnitude +
                ", id=" + id +
                ", ownerId='" + ownerId + '\'' +
                ", images=" + images +
                ", ownerNotifyAboutPraise=" + ownerNotifyAboutPraise +
                ", ownerEmailAddress=" + ownerEmailAddress +
                ", ownerLanguage=" + ownerLanguage +
                ", width=" + width +
                ", height=" + height +
                ", maxPhotoSize=" + maxPhotoSize +
                ", tags=" + tags +
                ", status=" + status +
                ", praiseSum=" + praiseSum +
                ", noVotes=" + noVotes +
                ", noVotesAtLastNotification=" + noVotesAtLastNotification +
                ", creationTime=" + creationTime +
                ", ending='" + ending + '\'' +
                ", location=" + location +
                ", idLong=" + idLong +
                ", parent=" + parent +
                ", writeCount=" + writeCount +
                '}';
    }
}
