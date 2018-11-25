package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {
    /**
     * @methodtype conversion
     */
    public CartesianCoordinate asCartesianCoordinate() {
        return doAsCartesianCoordinate();
    }

    /**
     * @methodtype conversion
     * @methodproterty primitive, hook
     */
    protected abstract CartesianCoordinate doAsCartesianCoordinate();

    /**
     * @methodtype conversion
     */
    public SphericCoordinate asSphericCoordinate() {
        return doAsSphericCoordinate();
    }

    /**
     * @methodtype conversion
     * @methodproterty primitive, hook
     */
    protected abstract SphericCoordinate doAsSphericCoordinate();

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
