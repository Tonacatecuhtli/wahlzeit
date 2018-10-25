package org.wahlzeit.model;


public class Coordinate {
    private double x, y, z;

    public Coordinate (double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    protected double getDistance (Coordinate other) {

        return 1;
    }

    protected boolean isEqual (Coordinate other) {
        // check variables for nill
        return true;
    }
}
