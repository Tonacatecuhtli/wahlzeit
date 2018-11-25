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

        return 0;
    }

    /**
     *  @param coordinate
     *	@methodtype getter
     */
    public double getCentralAngle(Coordinate coordinate){

        return 0;
    }

    protected abstract double doGetCentralAngle(Coordinate coordinate);

    /**
     *  @param coordinate
     *	@methodtype boolean query
     */
    public boolean isEqual(Coordinate coordinate){

        return false;
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
