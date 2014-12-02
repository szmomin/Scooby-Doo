import java.util.HashMap;
/**
 * 
 * That creates Items in the Haunted Mansion
 */
public class Items
{
    private String itemName;
    private String itemDescription;
    boolean pickUp;
    
    /**
     * Constructor for objects of class Items
     */
    public Items(String itemName, String itemDescription)
    {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
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
    
        /**
     * Returns Item Name 
     */
    
    public void testPickUp(){
        if (pickUp = false){
            System.out.println("Item cannot be picked up");
        }
    }
    
    public boolean getPickUp(){
        return true;
    }
    
    /**
     * .print. will print the first part of the line, then will call the super class print method.
     * 
     * @param  void
     * @return  void  
     */
    public void print()
    {
        System.out.println(itemName + "    " + itemDescription);
        return;
    }
}