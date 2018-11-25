package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {

    /**
     *  @param coordinate
     *	@methodtype getter
     */
    public double getCartesianDistance(Coordinate coordinate){
        CartesianCoordinate cc1 = this.asCartesianCoordinate();
        CartesianCoordinate cc2 = coordinate.asCartesianCoordinate();

        double xDif = Math.pow((cc2.getX() - cc1.getX()), 2);
        double yDif = Math.pow((cc2.getY() - cc1.getY()), 2);
        double zDif = Math.pow((cc2.getZ() - cc1.getZ()), 2);

        return Math.sqrt(xDif + yDif + zDif);
    }

    /**
     *  @param coordinate
     *	@methodtype getter
     */
    public double getCentralAngle(Coordinate coordinate){
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

}
