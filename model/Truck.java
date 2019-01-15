/*
 * TCSS 305 - Spring 2017
 * Assignment 3 - easystreet
 */

package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class models the behavior of a Truck vehicle.
 * 
 * @author Lloyd Brooks
 * @version 4/21/17
 *
 */
public class Truck extends AbstractVehicle {

    /**
     * Constructor for a Truck object.
     * 
     * @param theX The starting x position for this Truck.
     * @param theY The starting y position for this Truck.
     * @param theDirection The starting Direction for this Truck.
     */
    public Truck(final int theX, final int theY, final Direction theDirection) {

        super(theX, theY, theDirection);
        
        setAbilities();
        
        myAliveImageFileName = "truck.gif";
        myDeadImageFileName = "truck_dead.gif";
        
        final int magicNumberFix = 0; //To fix magic number warning from check style.
        myDeadTime = magicNumberFix;
    }
    
    /**
     * Private helper method which sets up the abilities map for Truck objects.
     */
    private void setAbilities() {
        
        final ArrayList<Terrain> onGreen = new ArrayList<Terrain>(); 
        onGreen.add(Terrain.STREET);
        onGreen.add(Terrain.LIGHT);
        onGreen.add(Terrain.CROSSWALK);
        final ArrayList<Terrain> onYellow = new ArrayList<Terrain>();
        onYellow.add(Terrain.LIGHT);
        onYellow.add(Terrain.STREET);
        onYellow.add(Terrain.CROSSWALK);
        final ArrayList<Terrain> onRed = new ArrayList<Terrain>();
        onRed.add(Terrain.STREET);    
        onRed.add(Terrain.LIGHT);
        myAbilities = new HashMap<Light, ArrayList<Terrain>>();
        myAbilities.put(Light.GREEN, onGreen);
        myAbilities.put(Light.YELLOW, onYellow);
        myAbilities.put(Light.RED, onRed);
    }

    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        
        Direction result = myDirection.reverse(); //only if no other options are valid.
        
        if (canPass(theNeighbors.get(myDirection), Light.GREEN) 
                        || canPass(theNeighbors.get(myDirection.left()), Light.GREEN)
                        || canPass(theNeighbors.get(myDirection.right()), Light.GREEN)) {
            
            while (result == myDirection.reverse()
                            || !canPass(theNeighbors.get(result), Light.GREEN)) {
                result = Direction.random();
            }           
        }
        
        return result;
    }

    @Override
    public String toString() {
        return "Truck";
    }
}
