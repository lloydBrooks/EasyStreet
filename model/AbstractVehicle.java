/*
 *  TCSS 305 - Spring 2017
 *  Assignment 3 - easystreet
 */
package model;

import java.util.ArrayList;
import java.util.Map;

/**
 * This program is an abstract framework for a basic vehicle.
 * 
 * @author Lloyd Brooks
 * @version 4/21/17
 */
public abstract class AbstractVehicle implements Vehicle {

    //instance fields:
    /**
     * The Direction this vehicle was initialized with, used to reset vehicle.
     */
    protected final Direction myDefaultDirection;
    
    /**
     * The direction the vehicle is currently facing.
     */
    protected Direction myDirection; 
    
    /**
     *A map detailing the terrain this vehicle can traverse. 
     *The Light is the key to what terrain the vehicle can traverse,
     *given the status of the light. 
     */
    protected Map<Light, ArrayList<Terrain>> myAbilities;
    
    /**
     * The file name which houses the live image of this vehicle.
     */
    protected String myAliveImageFileName;
    
    /**
     * The file name which houses the dead image of this vehicle.
     */
    protected String myDeadImageFileName;
    
    /**
     * The status of the vehicle, true means alive, false means dead.
     */
    protected boolean myAlive = true;
    
    /**
     * The time which the vehicle will be dead for when killed.
     */
    protected int myDeadTime;
    
    /**
     * The number of turns the vehicle has been dead for.
     */
    protected int myDeathCounter;
    
    /**
     * The x location this vehicle was initialized with, used to reset vehicle.
     */
    protected final int myDefaultX;
    
    /**
     * THe y location this vehicle was initialized with, used to reset vehicle.
     */
    protected final int myDefaultY;
    
    /**
     * The x coordinate the vehicle is located at.
     */
    protected int myX;
    
    /**
     * The y coordinate the vehicle is located at.
     */
    protected int myY;
    
    /**
     * A basic constructor for all vehicles.
     * 
     * @param theX The starting x location for this vehicle.
     * @param theY The starting y location for this vehicle.
     * @param theDirection The starting direction for this vehicle.
     */
    public AbstractVehicle(final int theX, final int theY, final Direction theDirection) {
       
        myDirection = theDirection;
        myDefaultDirection = theDirection;
        myX = theX;
        myDefaultX = theX;
        myY = theY;
        myDefaultY = theY;
    }
    
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        
        boolean result = false;
        if (myAbilities.get(theLight).contains(theTerrain)) {
            result = true;
        }
        return result;
    }

    @Override
    public abstract Direction chooseDirection(Map<Direction, Terrain> theNeighbors);

    @Override
    public void collide(final Vehicle theOther) {

        if (theOther.getDeathTime() < myDeadTime) {
            myAlive = false;
        }
    }

    @Override
    public int getDeathTime() {

        return myDeadTime;
    }

    @Override
    public String getImageFileName() {

        String result = myDeadImageFileName;
        if (myAlive) {
            result = myAliveImageFileName;
        }
        return result;
    }

    @Override
    public Direction getDirection() {

        return myDirection;
    }

    @Override
    public int getX() {

        return myX;
    }

    @Override
    public int getY() {

        return myY;
    }

    @Override
    public boolean isAlive() {

        return myAlive;
    }

    @Override
    public void poke() {

        if (++myDeathCounter == myDeadTime) {
            myAlive = true;
            myDeathCounter = 0;
            myDirection = Direction.random();
            
        }
    }

    @Override
    public void reset() {
        
        myX = myDefaultX;
        myY = myDefaultY;
        myDirection = myDefaultDirection;
        myAlive = true;
        myDeathCounter = 0;
        
    }

    @Override
    public void setDirection(final Direction theDir) {
        
        myDirection = theDir;
    }

    @Override
    public void setX(final int theX) {
        
        myX = theX;
    }

    @Override
    public void setY(final int theY) {

        myY = theY;
    }

}
