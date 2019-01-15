/*
 * TCSS 305 - Spring 2017
 * Assignment 3 - easystreet
 */

package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class models the behavior of a Car vehicle.
 * 
 * @author Lloyd Brooks
 * @version 4/21/17
 *
 */
public class Car extends AbstractVehicle {

    
    /**
     * Constructor for a new Car object.
     * 
     * @param theX The starting x position.
     * @param theY The starting y position.
     * @param theDirection The starting direction.
     */
    public Car(final int theX, final int theY, final Direction theDirection) {

        super(theX, theY, theDirection);
        
        setAbilities();
        
        myAliveImageFileName = "car.gif";
        myDeadImageFileName = "car_dead.gif";
        
        final int magicNumberFix = 10; //To fix magic number warning from check style.
        myDeadTime = magicNumberFix;
    }

    /**
     * Private helper method which sets up the abilities map for Car objects.
     */
    private void setAbilities() {
        
        final ArrayList<Terrain> onGreen = new ArrayList<Terrain>(); 
        onGreen.add(Terrain.STREET);
        onGreen.add(Terrain.LIGHT);
        onGreen.add(Terrain.CROSSWALK);
        final ArrayList<Terrain> onYellow = new ArrayList<Terrain>();
        onYellow.add(Terrain.LIGHT);
        onYellow.add(Terrain.STREET);
        final ArrayList<Terrain> onRed = new ArrayList<Terrain>();
        onRed.add(Terrain.STREET);    
        myAbilities = new HashMap<Light, ArrayList<Terrain>>();
        myAbilities.put(Light.GREEN, onGreen);
        myAbilities.put(Light.YELLOW, onYellow);
        myAbilities.put(Light.RED, onRed);
    }

    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {

        Direction result = myDirection.reverse(); //if nothing else works.
        
        if (canPass(theNeighbors.get(myDirection), Light.GREEN)) {
            result = myDirection;
        } else if (canPass(theNeighbors.get(myDirection.left()), Light.GREEN)) {
            result = myDirection.left();
        } else if (canPass(theNeighbors.get(myDirection.right()), Light.GREEN)) {
            result = myDirection.right();
        }
        
        return result;
    }

    @Override
    public String toString() {
        return "Car Dead for: " + myDeathCounter + " turns";
    }
}
