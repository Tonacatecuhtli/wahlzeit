package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate{
    /**
     *	@methodtype conversion
     */
    public CartesianCoordinate asCartesianCoordinate(){
        return doAsCartesianCoordinate();
    }

    /**
     *  @methodtype conversion
     *	@methodproterty primitive, hook
     */
    protected abstract CartesianCoordinate doAsCartesianCoordinate();

    /**
     *	@methodtype conversion
     */
    public SphericCoordinate asSphericCoordinate(){
        return doAsSphericCoordinate();
    }

    /**
     *  @methodtype conversion
     *	@methodproterty primitive, hook
     */
    protected abstract SphericCoordinate doAsSphericCoordinate();

    /**
     *  @param coordinate
     */
    public double getCartesianDistance(Coordinate coordinate){
        CartesianCoordinate cc1 = this.asCartesianCoordinate();
        CartesianCoordinate cc2 = coordinate.asCartesianCoordinate();
        return cc1.getDistance(cc2);
    }

    /**
     *  @param coordinate
     *	@methodtype getter
     */
    public double getCentralAngle(Coordinate coordinate){
        SphericCoordinate c1s = this.asSphericCoordinate();
        return c1s.doGetCentralAngle(coordinate);
    }

    protected abstract double doGetCentralAngle(Coordinate coordinate);

    /**
     *  @param coordinate
     *	@methodtype boolean query
     */
    public boolean isEqual(Coordinate coordinate){

    }

    /**
     *  @param value a constructor input value
     *	@methodtype boolean query
     */
    protected boolean isValid(double value){
        return doIsValid(value);
    }

    /**
     *	@methodtype boolean query
     *	@methodproterty primitive, hook
     */
    protected abstract boolean doIsValid(double value);


}
