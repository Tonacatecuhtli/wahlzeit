package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {

    /**
     * calculates the cartesian distance
     * @param coordinate
     * @return cartesianDistance
     */
    public double getCartesianDistance(Coordinate coordinate) {
        CartesianCoordinate cc1 = this.asCartesianCoordinate();
        CartesianCoordinate cc2 = coordinate.asCartesianCoordinate();

        double xDif = Math.pow((cc2.getX() - cc1.getX()), 2);
        double yDif = Math.pow((cc2.getY() - cc1.getY()), 2);
        double zDif = Math.pow((cc2.getZ() - cc1.getZ()), 2);

        return Math.sqrt(xDif + yDif + zDif);
    }

    /**
     * calculates the central angle
     * @param coordinate
     * @return centralAngle
     */
    public double getCentralAngle(Coordinate coordinate) {
        SphericCoordinate cs1 = this.asSphericCoordinate();
        SphericCoordinate cs2 = coordinate.asSphericCoordinate();

        double centralAngle = Math.acos(
                Math.sin(cs1.getLatitude()) * Math.sin(cs2.getLatitude())
                        +
                        Math.cos(cs1.getLatitude()) * Math.cos(cs2.getLatitude()) * Math.cos(Math.abs(cs2.getLongitude() - cs1.getLongitude()))
        );

        return centralAngle;
    }

    /**
     * @param coordinate
     * @methodtype boolean query
     */
    public boolean isEqual(Coordinate coordinate) {
        return doIsEqual(coordinate);
    }

    /**
     * @methodtype conversion
     * @methodproterty primitive, hook
     */
    protected abstract boolean doIsEqual(Coordinate coordinate);

    /**
     * @param value a constructor input value
     * @methodtype boolean query
     * @methodProperty hook
     */
    protected boolean isValid(double value) {
        return value >= 0;
    }

    /**
     * make sure no coordinates are null
     * make sure no coordinates are negative
     *
     * @param arg1 Coordinate parameter 1
     * @param arg2 Coordinate parameter 2
     * @param arg3 Coordinate parameter 3
     * @methodType assertion
     * @methodProperty hook
     */
    protected void assertClassInvariants(double arg1, double arg2, double arg3){
        // assertNotNull
        assertNotNull(arg1);
        assertNotNull(arg2);
        assertNotNull(arg3);
        // assertNotNegative
        assertNotNegative(arg1);
        assertNotNegative(arg2);
        assertNotNegative(arg3);
    }

    /**
     * @methodType assertion
     * @param arg a Double argument
     * @throws IllegalArgumentException
     */
    protected void assertNotNull(Double arg) throws IllegalArgumentException {
        if (arg == null)
            throw new IllegalArgumentException("argument can not be null");
    }

    /**
     * @methodType assertion
     * @param arg
     * @throws IllegalArgumentException
     */
    protected void assertNotNegative(double arg) throws IllegalArgumentException {
        if (arg < 0)
            throw new IllegalArgumentException("argument can not be negative");
    }
}
