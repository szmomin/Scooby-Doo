
/**
 * Class Item - an item in an adventure game.
 *
 * This class is part of the "Scooby Doo: Mystery of the Haunted Mansion" application. 
 * "Scooby Doo: Mystery of the Haunted Mansion" is a very simple, text based myster game.  
 *
 * An "Item" represents one object in the haunted mansion of the game.  
 * Items can be inspected, picked up, and used. 
 * 
 */

public class Item
{
    private String itemName;

    /**
     * Create an item described "description". "description" is something like "a watch" or
     * "a computer".
     * @param description The item's description.
     */
    public Item(String itemName) 
    {
        this.itemName = itemName;
    }
    
    /**
     * @return The short description of the item
     * (the one that was defined in the constructor).
     */
    public String getItemName()
    {
        return itemName;
    } 
}


