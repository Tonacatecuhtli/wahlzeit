package org.wahlzeit.model;


public class Coordinate {
    private double x, y, z;

    public Coordinate (double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    protected double getDistance (Coordinate other) {
        double xDif = Math.pow((other.x - this.x), 2);
        double yDif = Math.pow((other.y - this.y), 2);
        double zDif = Math.pow((other.z - this.z), 2);

        return Math.sqrt(xDif + yDif + zDif);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        // check if it's a assignable
        if (!Coordinate.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        final Coordinate other = (Coordinate) obj;
        return this.isEqual(other);
    }


    protected boolean isEqual (Coordinate other) {
        return this.x == other.x && this.y == other.y && this.z == other.z;
    }
}
