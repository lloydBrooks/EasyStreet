/*
 * TCSS 305 - Spring 2017
 * Assignment 3 - easystreet
 */

package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class models the behavior of a Human vehicle.
 * 
 * @author Lloyd Brooks
 * @version 4/21/17
 *
 */
public class Human extends AbstractVehicle {

    /**
     * Constructor for a Human object.
     * 
     * @param theX The starting x position for this ATV.
     * @param theY The starting y position for this ATV.
     * @param theDirection The starting Direction for this ATV.
     */
    public Human(final int theX, final int theY, final Direction theDirection) {
        super(theX, theY, theDirection);
        
        setAbilities();
        
        myAliveImageFileName = "human.gif";
        myDeadImageFileName = "human_dead.gif";
        
        final int magicNumberFix = 50; //To fix magic number warning from check style.
        myDeadTime = magicNumberFix;
    }
    
    /**
     * Private helper method which sets up the abilities map for Human objects.
     */
    private void setAbilities() {
        
        final ArrayList<Terrain> onGreen = new ArrayList<Terrain>(); 
        onGreen.add(Terrain.GRASS);
        final ArrayList<Terrain> onYellow = new ArrayList<Terrain>();
        onYellow.add(Terrain.CROSSWALK);
        onYellow.add(Terrain.GRASS);
   
        myAbilities = new HashMap<Light, ArrayList<Terrain>>();
        myAbilities.put(Light.GREEN, onGreen);
        myAbilities.put(Light.YELLOW, onYellow);
        myAbilities.put(Light.RED, onYellow);
        
    }

    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {

        Direction result = myDirection.reverse(); //will be changed if better option exists.
        
        if (theNeighbors.get(myDirection.reverse()) != Terrain.CROSSWALK 
                        && theNeighbors.containsValue(Terrain.CROSSWALK)) {
            
            result = findCrossWalk(theNeighbors);
                 
        } else if (canPass(theNeighbors.get(myDirection), Light.GREEN) 
                        || canPass(theNeighbors.get(myDirection.right()), Light.GREEN) 
                        || canPass(theNeighbors.get(myDirection.left()), Light.GREEN)) {
            do {
                result = Direction.random();
            } while (!canPass(theNeighbors.get(result), Light.GREEN) 
                        || result == myDirection.reverse());        
        }
           
        return result;
    }
    
    /**
     * Private helper method to find cross walk in the map of neighbors.
     * 
     * 
     * @param theNeighbors The map of neighboring terrains.
     * @return The direction which the cross walk is in.
     */
    private Direction findCrossWalk(final Map<Direction, Terrain> theNeighbors) {
  
        Direction result = myDirection; //returned if a cross walk is not to the right or left.
        if (theNeighbors.get(myDirection.right()) == Terrain.CROSSWALK) {
            result = myDirection.right();
        }
        if (theNeighbors.get(myDirection.left()) == Terrain.CROSSWALK) {
            result = myDirection.left();
        }   
        
        return result;
    }

    @Override
    public String toString() {
        return "Human Dead for: " + myDeathCounter + " turns";
    }
}
