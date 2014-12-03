/**
 * 
 * That creates Items as clues so that a player can list them
 */
public class Clue extends Items
{
    // instance variables - replace the example below with your own
    private String clueDescription;
    
    /**
     * Constructor for objects of class Items
     */
    public Clue(String itemName, String itemDescription, String clueDescription)
    {
        super(itemName,itemDescription);
        this.clueDescription = clueDescription;
    }
    
    /**
     * .getClueDescription.
     * Returns the clue description that will be displayed when necessary
     */
    public String getClueDescription(){
        return clueDescription;
    }
}