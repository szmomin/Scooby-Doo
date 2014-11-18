import java.util.HashMap;
/**
 * This is a test class.
 * That creates Items in the Haunted Mansion
 */
public class TESTItems
{
    // instance variables - replace the example below with your own
    private String itemName;
    private String itemDescription;
    private boolean pickUp;
    //private HashMap<String, TESTItems> items;
    
    /**
     * Constructor for objects of class TESTItems
     */
    public TESTItems(String itemName, String itemDescription)
    {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        pickUp = false;
    }

    /**
     * Returns Item Name 
     */
    public String getItemName(){
        return itemName;
    }
    
    /**
     * Returns Item Description 
     */
    public String getItemDescription(){
        return itemDescription;
    }
}
