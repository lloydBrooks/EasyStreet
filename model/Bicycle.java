/*
 * TCSS 305 - Spring 2017
 * Assignment 3 - easystreet
 */

package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class models the behavior of a Bicycle vehicle.
 * 
 * @author Lloyd Brooks
 * @version 4/21/17
 *
 */
public class Bicycle extends AbstractVehicle implements Vehicle {

    /**
     * Constructor for a Bicycle object.
     * 
     * @param theX The starting x position for this Bicycle.
     * @param theY The starting y position for this Bicycle.
     * @param theDirection The starting Direction for this Bicycle.
     */
    public Bicycle(final int theX, final int theY, final Direction theDirection) {
        super(theX, theY, theDirection);
        
        setAbilities();
        
        myAliveImageFileName = "bicycle.gif";
        myDeadImageFileName = "bicycle_dead.gif";
        
        final int magicNumberFix = 30; //To fix magic number warning from check style.
        myDeadTime = magicNumberFix;
    }
    
    /**
     * Private helper method which sets up the abilities map for Bicycle objects.
     */
    private void setAbilities() {
        
        final ArrayList<Terrain> onGreen = new ArrayList<Terrain>(); 
        onGreen.add(Terrain.TRAIL);
        onGreen.add(Terrain.STREET);
        onGreen.add(Terrain.LIGHT);
        onGreen.add(Terrain.CROSSWALK);
        final ArrayList<Terrain> onYellow = new ArrayList<Terrain>();
        onYellow.add(Terrain.TRAIL);
        onYellow.add(Terrain.STREET);
        final ArrayList<Terrain> onRed = new ArrayList<Terrain>();
        onRed.add(Terrain.TRAIL);
        onRed.add(Terrain.STREET);    
        myAbilities = new HashMap<Light, ArrayList<Terrain>>();
        myAbilities.put(Light.GREEN, onGreen);
        myAbilities.put(Light.YELLOW, onYellow);
        myAbilities.put(Light.RED, onRed);
    }


    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        
        Direction result = myDirection.reverse(); //will only return if no other options.
        
        if (theNeighbors.containsValue(Terrain.TRAIL)
                        && theNeighbors.get(result) != Terrain.TRAIL) {
            result = findTrail(theNeighbors);
        } else if (canPass(theNeighbors.get(myDirection), Light.GREEN)) {
            result = myDirection;
        } else if (canPass(theNeighbors.get(myDirection.left()), Light.GREEN)) {
            result = myDirection.left();
        } else if (canPass(theNeighbors.get(myDirection.right()), Light.GREEN)) {
            result = myDirection.right();
        }
        return result;
    }
    
    /**
     * Private helper method to find trail, only call if a neighboring terrain is a trail.
     * 
     * @param theNeighbors A map of neighboring terrains.
     * @return the direction of the trail
     */
    private Direction findTrail(final Map<Direction, Terrain> theNeighbors) {

        Direction result = myDirection;
        if (theNeighbors.get(myDirection.right()) == Terrain.TRAIL) {
            result = myDirection.right();
        }
        if (theNeighbors.get(myDirection.left()) == Terrain.TRAIL) {
            result = myDirection.left();
        }   
        
        return result;
    }
    
    @Override
    public String toString() {
        return "Bike Dead for: " + myDeathCounter + " turns";
    }
}
