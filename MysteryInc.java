
/**
 * Write a description of class MysteryInc here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MysteryInc
{
    // instance variables - replace the example below with your own
    
    private MysteryInc thisMember;
    private String memberName;
    private boolean present;
    
    /**
     * Constructor for objects of class MysteryInc
     */
    public MysteryInc(String memberName)
    {
        // initialise instance variables
        this.memberName = memberName;
        present = true; // at the beginning all members are with Scooby
    }
    
        /**
     * Returns Item Name 
     */
    
    public void testPickUp(){
        if (present = false){
            System.out.println("On no! " + thisMember + "is not with you! Find an item to rescue them!");
        }
    }
    
    /**
     * .loseMember.
     * Sets the present boolean to false so that a member is not with the player
     */
    public void loseMember(){
        present = false;
    }
    
     /**
     * .saveMember.
     * Sets the present boolean to true so that the player is saved by the player
     */
    public void saveMember(){
        present = true;
    }
    
    /**
     * .getPresent.
     * returns whether a member is present or not 
     */
    public boolean getPresent(){
        return present;
    }
    
    
    /**
     * .print. will print the first part of the line, then will call the super class print method.
     * 
     * @param  void
     * @return  void  
     */
    public void print()
    {
        //System.out.println(memberName + "    " + present);
        System.out.println(memberName);
        return;
    }
}
