import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "Scooby Doo: Mystery of the Haunted Mansion" application. 
 * "Scooby Doo: Mystery of the Haunted Mansion" is a very simple, text based mystery game.  
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
    private HashMap<String, Room> exits;// stores exits of this room.
    //private HashMap<String, Item> items;
    public HashMap<String, Items> items;
    private String name;
    //private ArrayList<Item> itemLis;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String roomdescription) 
    {
        this.roomdescription = roomdescription;
        exits = new HashMap<String, Room>();
        //items = new HashMap<String, Item>();
        items = new HashMap<String, Items>();
        //items = new ArrayList<Item>();
    }

        /**
     * Define an item for this room.
     * @param itemName the text the user types in order to view/ pick up an item.
     * @param item  actual item that is picked up or viewed.
     */
    public void setItem (String itemName, Items item) 
    {
        items.put(itemName, item);
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
    public void setItem(String itemName, Item item) 
    {
        items.put(itemName, item);
    }
    */


    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getRoomDescription()
    {
        return roomdescription;
    }
    

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return roomdescription + ".\n" + /*getActionString() + ".\n"  + */getExitString();
    }

    /**
     * Return a string describing the room's possible exploration avenues, for example
     * "Directions: inspect grave headstone".
     * @return Details of the room's actions.
     */
    /*
    private String getActionString()
    {
        String returnString = "Actions: ";
        Set<String> keys = actions.keySet();
        for(String action : keys) {
            returnString += action + ", ";
        }
        return returnString;
    }
    */
    
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
    /*
    public String getAction(String action) 
    {
        return actions.get(action);
    }
    */
    /**
     * Add an item to the room
     */
    //public void addItem(Item i)
    {
       // items.add(i);
    }
    
    
    /**
     * Get item from room
     */
    /*
    public String getItem(int i)
    {
        if(i<items.size() && i>=0){
            return items.get(i).toString();
        } else {
            return "Item does not exist";
        }
    }
    */
}

