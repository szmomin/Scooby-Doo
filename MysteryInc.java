
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
    public MysteryInc()
    {
        // initialise instance variables
        this.memberName = memberName;
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
     * .print. will print the first part of the line, then will call the super class print method.
     * 
     * @param  void
     * @return  void  
     */
    public void print()
    {
        System.out.println(memberName + "    " + present);
        return;
    }
}
