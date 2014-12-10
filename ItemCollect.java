import java.util.HashMap;
/**
 * 
 * That creates Items in the Haunted Mansion
 */
public class ItemCollect extends Items
{
    // instance variables - replace the example below with your own
    boolean pickUp;
    //private HashMap<String, TESTItems> items;
    
    /**
     * Constructor for objects of class Items
     */
    public ItemCollect(String itemName, String itemDescription, boolean pickUp)
    {
        super(itemName,itemDescription);
        this.pickUp = true;
    }
    
}
