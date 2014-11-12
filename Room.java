import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "Scooby Doo: Mystery of the Haunted Mansion" application. 
 * "Scooby Doo: Mystery of the Haunted Mansion" is a very simple, text based myster game.  
 *
 * A "Room" represents one location in the haunted mansion of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.08.10
 */

public class Room 
{
    private String roomdescription;
    private String actiondescription;
    private HashMap<String, Room> exits;
    private HashMap<String, String> actions;// stores exits of this room.

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String roomdescription) 
    {
        this.roomdescription = roomdescription;
        this.actiondescription = actiondescription;
        exits = new HashMap<String, Room>();
        actions = new HashMap<String, String>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }
    
    /**
     * Define a direction within this room.
     * @param direction The direction in the room.
     * @param action What happens when Scooby goes in that direction.
     */
    public void setAction(String action, String result) 
    {
        actions.put(action, result);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getRoomDescription()
    {
        return roomdescription;
    }
    
    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getActionDescription()
    {
        return actiondescription;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return roomdescription + ".\n" + getActionString() + ".\n"  + getExitString();
    }

    /**
     * Return a string describing the room's possible exploration avenues, for example
     * "Directions: inspect grave headstone".
     * @return Details of the room's actions.
     */
    private String getActionString()
    {
        String returnString = "Actions: ";
        Set<String> keys = actions.keySet();
        for(String action : keys) {
            returnString += action + ", ";
        }
        return returnString;
    }
    
    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits: ";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += exit + ", ";
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
        /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public String getAction(String action) 
    {
        return actions.get(action);
    }
        
}

