/*
 * TCSS 305 - Spring 2017
 * Assignment 3 - easystreet
 */

package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class models the behavior of a Taxi vehicle.
 * 
 * @author Lloyd Brooks
 * @version 4/21/17
 *
 */
public class Taxi extends Car {
    
    /**
     * @param TOLERANCE The tolerance of all taxi drivers to wait at cross walks. 
     */
    private static final int TOLERANCE = 3;
    
    /**
     * @param myImpatience The anger level of the taxi driver waiting at a 
     * cross walk. If this gets too high he will blow the light!
     */
    private int myImpatience;

    
    /**
     * Constructor for a new Taxi object.
     * 
     * @param theX The starting x position.
     * @param theY The starting y position.
     * @param theDirection The starting direction.
     */
    public Taxi(final int theX, final int theY, final Direction theDirection) {

        super(theX, theY, theDirection);
        
        setAbilities();
        
        myAliveImageFileName = "taxi.gif";
        myDeadImageFileName = "taxi_dead.gif";
        
        final int magicNumberFix = 10; //To fix magic number warning from check style.
        myDeadTime = magicNumberFix;
        myImpatience = 0;
    }

    /**
     * Private helper method which sets up the abilities map for Taxi objects.
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
        myAbilities = new HashMap<Light, ArrayList<Terrain>>();
        myAbilities.put(Light.GREEN, onGreen);
        myAbilities.put(Light.YELLOW, onYellow);
        myAbilities.put(Light.RED, onRed);
    }
    
    /**
     * Overridden canPass to implement taxi impatience at cross walks.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        
        boolean result = false;
        if (myAbilities.get(theLight).contains(theTerrain)) {
            result = true;
        }
        if (!result && theTerrain == Terrain.CROSSWALK
                        && theLight == Light.RED
                        && ++myImpatience == TOLERANCE) {
            result = true;
            myImpatience = 0;
        }
        return result;
    }
    
    @Override
    public String toString() {
        return "Taxi Dead for: " + myDeathCounter + " turns. Waited for: " + myImpatience;
    }
}
