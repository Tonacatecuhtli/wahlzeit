package org.wahlzeit.model;

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
