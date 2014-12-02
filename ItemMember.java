/**
 * 
 * That creates Items that effect members in the Haunted Mansion
 */
public class ItemMember extends Items
{
    // instance variables - replace the example below with your own
    private String itemAltDescription;
    
    /**
     * Constructor for objects of class Items
     */
    public ItemMember(String itemName, String itemDescription, String itemAltDescription)
    {
        super(itemName,itemDescription);
        this.itemAltDescription = itemAltDescription;
    }
    
    /**
     * .getAltDescription.
     * Returns the alternative description when necessary
     */
    public String getAltDescription(){
        return itemAltDescription;
    }
}