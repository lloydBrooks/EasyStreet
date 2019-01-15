/*
 * TCSS 305 - Spring 2017
 * Assignment 3 - easystreet
 */

package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class models the behavior of an ATV vehicle.
 * 
 * @author Lloyd Brooks
 * @version 4/21/17
 *
 */
public class Atv extends AbstractVehicle implements Vehicle {

    /**
     * Constructor for an ATV object.
     * 
     * @param theX The starting x position for this ATV.
     * @param theY The starting y position for this ATV.
     * @param theDirection The starting Direction for this ATV.
     */
    public Atv(final int theX, final int theY, final Direction theDirection) {
        super(theX, theY, theDirection);
        
        setAbilities();
        
        myAliveImageFileName = "atv.gif";
        myDeadImageFileName = "atv_dead.gif";
        
        final int magicNumberFix = 20; //To fix magic number warning from check style.
        myDeadTime = magicNumberFix;
    }

    /**
     * Private helper method which sets up the abilities map for Bicycle objects.
     */
    private void setAbilities() {

        final ArrayList<Terrain> onGreen = new ArrayList<Terrain>();
        onGreen.add(Terrain.CROSSWALK);
        onGreen.add(Terrain.GRASS);
        onGreen.add(Terrain.LIGHT);
        onGreen.add(Terrain.STREET);
        onGreen.add(Terrain.TRAIL);
        
        myAbilities = new HashMap<Light, ArrayList<Terrain>>();
        myAbilities.put(Light.GREEN, onGreen);
        myAbilities.put(Light.YELLOW, onGreen);
        myAbilities.put(Light.RED, onGreen);
    }

    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        
        Direction result;
        do {
            result = Direction.random();
        } while (result == myDirection.reverse() 
                        || !canPass(theNeighbors.get(result), Light.GREEN));
        return result;
    }
    
    @Override
    public String toString() {
        return "AVT Dead for:" + myDeathCounter + " turns";
    }
}
