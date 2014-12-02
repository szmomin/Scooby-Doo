import java.util.ArrayList;
import java.util.HashMap;

/**
 *  This class is the main class of the "Scooby Doo: Mystery of the Haunted Mansion" application. 
 *  "Scooby Doo: Mystery of the Haunted Mansion" is a very simple, text based mystery game.
 *  Users can walk around a haunted mansion and inspect things.
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.08.10
 */

public class Haunted_Mansion 
{
    private ArrayList<Items> items;
    private Items thisItem;
    private String itemName;
    private String itemDescription;
    private boolean collect;
    
    private ArrayList<MysteryInc> members;
    private MysteryInc thisMember;
    private String memberName;
    private boolean present;
    
    private Parser parser;
    private Room currentRoom;
    Room cemetary, study, kitchen, diningroom, livingroom, hallway, winecellar, dungeon, bathroom, fatherbedroom, sonbedroom;
    MysteryInc Fred, Velma, Daphne, Shaggy; // declares the MysterInc members
    
    //Declares items that can be inspected
    Items desk, bookshelves, sofa, gravestone, toolshed, pantry, table, cabinets, cabinet, diningtable, vase, armoire, wineshelf, 
            barrels, supplybox, tapestries, closet, alcove, bathtub, BathroomCloset, sink, box, nightstand, bed, FatherCloset, dresser, SonDesk, wardrobe, 
            SonNightstand;
            
    //Declares items that can be collected
    ItemCollect rope, key;
    
    //Declares itesm that effect members
    ItemMember fireplace, cell, statue, BathroomCabinet, winebox, mosileum, counters, SonBed;
    

    /**
     * Create the game and initialise its internal map.
     */
    public Haunted_Mansion() 
    {
        items = new ArrayList<Items>();
        members = new ArrayList<MysteryInc>();
        createRooms();
        createItems();
        createMembers();
        //inventory = new ArrayList<Item>();
        parser = new Parser();
        play();
    }
    
    /*
     * .ListAllItems.
     * List all items with the player.
     */
    public void listAllItems()
    {
        int index = 0;
        System.out.println();
        System.out.println("List Of All Items");
        System.out.println("Name             Description");
        while(index < items.size())
        {
            thisItem = (Items) items.get(index);
            thisItem.print();
            index ++;
                    }
    }
    
    /*
     * .ListAllMembers.
     * List all members with the player.
     */
    public void listAllMembers()
    {
        int index = 0;
        System.out.println();
        System.out.println("List Of All Members");
        System.out.println("Name            Present?");
        while(index < members.size()){
            thisMember = (MysteryInc) members.get(index);
            thisMember.print();
            index ++;
        }
    }

    /**
     * Create all the rooms and link their exits together.
     * And has the room descriptions
     */
    private void createRooms()
    {
        //Room cemetary, study, kitchen, diningroom, livingroom, hallway, winecellar, dungeon, bathroom, fatherbedroom, sonbedroom;
        
        // create the rooms
        cemetary = new Room("You are now outside in the back garden, which turns out to be a creepy and spooky graveyard!" +
                            "\n You can inspect the gravestone, mosileum, toolshed, and statue." + 
                            "\n The kitchen is to your left.");
        
        study = new Room("You are in Dr. Alcott's study, which is also a library. There's a huge desk in the middle of the room." +
                            "\n You can inspect the desk, bookshelves, fireplace, and sofa." + 
                            "\n The living room is to your right.");
                            
        kitchen = new Room("You are now in the mansion's massive kitchen, where there is sure to be a delicious feast waiting. Ain't that right, Scoob?" + 
                            "\n You can inspect the pantry, counters, table, and cabinets." + 
                            "\n The dining room is to the south, and the cemetary to the right.");
                            
        diningroom = new Room("You are now in the dining room. Watch out for the expensive china and ancient paintings Scooby!" + 
                            "\n You can inspect the cabinet, diningtable, vase, and armoire." +
                            "\n The kitchen is to your north and the living room is to your left.");
                            
        hallway = new Room("You are now in the upstairs hallway. Which room would you like to enter from here?" +
                            "\n The bathroom is to the north, Mr. Alcott's room to the left, Wren's room to the right, the living room to the south");
                            
        winecellar = new Room("You are now in the wine cellar with wonderful vintage wine and stacks of huge wooden barrels. Tread carefully, Scooby . . ." + 
                            "\n You can inspect the wineshelf, barrels, supplybox, and winebox."+
                            "\n The dungeon is to the right, the living room is up.");
                            
        dungeon = new Room("You are now in the dungeon of the mansion. It used to hold prisoners and slaves a long time ago. Now it's mainly used for storage." + 
                            "\n You can inspect the tapestries, closet, cell, and alcove."+
                            "\n The winecellar is to the left, and you feel a strange breeze coming from above.");
                            
        bathroom = new Room("You are now in the bathroom. Don't forget to flush!" + 
                            "\n You can inspect the bathtub, BathroomCabinet, sink, and box." +
                            "\n The hallway is to the south.");
                            
        fatherbedroom = new Room("You are now in the father's bedroom. Could there possibly be a clue here too, Scooby Doo?" + 
                            "\n You can inspect the nightstand, bed, FatherCloset, and dresser." + 
                            "\n The hallway is to the right.");
                            
        sonbedroom = new Room("You are now in the son's bedroom. Let's check it out, Scoobs!" + 
                            "\n You can inspect the SonBed, SonNightstand, wardrobe, and SonDesk. " +
                            "\n The hallway is to the left.");
                            
        livingroom = new Room("You are now in the living room. Ask the caretaker a question or go to another room." + 
                            "\n The study is to the left, the dining room to the right, the wineceller down, and the hallway up.");
        
        // initialise room exits and actions
        cemetary.setExit("left", kitchen);
        cemetary.setExit("down", dungeon);
        
        study.setExit("right", livingroom);

        kitchen.setExit("south", diningroom);
        kitchen.setExit("right", cemetary);

        diningroom.setExit("left", livingroom);
        diningroom.setExit("north", kitchen);

        hallway.setExit("left", fatherbedroom);
        hallway.setExit("right", sonbedroom);
        hallway.setExit("north", bathroom);
        hallway.setExit("down", livingroom);
       
        winecellar.setExit("up", livingroom);
        winecellar.setExit("right", dungeon);
       
        dungeon.setExit("left", winecellar);
        dungeon.setExit("up", cemetary);
       
        bathroom.setExit("south", hallway);

        fatherbedroom.setExit("right", hallway);
        sonbedroom.setExit("left", hallway);

        livingroom.setExit("left", study);
        livingroom.setExit("right", diningroom);
        livingroom.setExit("up", hallway);
        livingroom.setExit("down", winecellar);
        
        currentRoom = livingroom;  // start game in the living room
    }
    
    private void createItems(){
        // Items for the Study 
        desk = new Items ("desk", "Google search history is on how to kidnap someone! A clue! A clue! Good boy Scooby!");
        study.setItem("desk", desk);
        bookshelves = new Items ("bookshelves", "Could these books be any more boring? Nothing here. Keep looking!");
        study.setItem("bookshelves", bookshelves);
        sofa = new Items ("sofa.", "Nothing here, but this sofa does look comfy enough for rainy day naps.");
        study.setItem("sofa", sofa);
        fireplace = new ItemMember ("fireplace", "Nothing, but it looks like a place for glasses to be lost",
                                    "You found Velma's glasses! Velma is now back with the gang. Good boy Scooby!");
        study.setItem("fireplace", fireplace);
        
        // Items for the Cemetary
        gravestone = new Items ("gravestone", "There are a few cobwebs, but other than that, nothing!");
        cemetary.setItem("gravestone", gravestone);
        mosileum = new ItemMember ("mosileum", "Oh no! Velma lost her glasses! You have now lost Velma.", " ");
        cemetary.setItem("mosileum", mosileum);
        toolshed = new Items ("toolshed.", "Uhhhh... yep. There are only gardening tools here!");
        cemetary.setItem("toolshed", toolshed);
        statue = new ItemMember ("statue", "Look! A rope and a white sheet! A clue! A clue! The rope can be collected to maybe rescue someone later",
                                "The rope can be used to rescue Fred from the trapdoor! Fred is now back with the gang Good boy Scooby!");
        cemetary.setItem("statue", statue);
        rope = new ItemCollect ("rope", "You have now picked up the rope. It is in your inventory", true);
        cemetary.setItem("rope", rope);
        
        // Items for the Kitchen
        pantry = new Items ("pantry", "Well... nothing here. Oooh!!! A box of Scooby Snacks! Yum! Yum! Yum!");
        kitchen.setItem("pantry", pantry);
        counters = new ItemMember ("counters", "Oh no! Shaggy ate all the food on the counter and got food poisoning! You have now lost Shaggy.", " ");
        kitchen.setItem("counters", counters);
        table = new Items ("table.", "Under the table is a cellphone has to be Dr. Alcotts'! A clue! A clue! Last text is to the gardener arguing about a pay raise. Hmmmmm...");
        kitchen.setItem("table", table);
        cabinets = new Items ("cabinets", "Unless you really like expensive silverware and porcelain dishes, this looks like a dead end to me, Scoob.");
        kitchen.setItem("cabinets", cabinets);
        
        // Items for the Dinning Room
        cabinet = new Items ("cabinet", "So many dishes! So few clues! Try again Scooby Doo!");
        diningroom.setItem("cabinet", cabinet);
        diningtable = new Items ("diningtable", "What an incredibly large table! The Alcott family must have been monstrously large at some point.");
        diningroom.setItem("diningtable", diningtable);
        vase = new Items ("vase", "Here is a key! A clue! A clue! It must unlock the safe! You have found Dr. Alcott's will! Good boy Scooby!");
        diningroom.setItem("vase", vase);
        armoire = new Items ("armoire", "Oops. Nothing here. Keep looking Scooby!");
        diningroom.setItem("armoire", armoire);
        key = new ItemCollect ("key", "You have now picked up the key. It is in your inventory", true);
        diningroom.setItem("key", key);
        
        // Items for the Winecellar
        wineshelf = new Items ("wineshelf", "Hmmm. According to the dust on these old wine bottles here, they have not been moved in years. Keep looking, Scooby!");
        winecellar.setItem("wineshelf", wineshelf);
        barrels = new Items ("barrels", "Look! A watch! It looks like Dr. Alcott's watch from the painting over the fireplace! A clue! A clue! Good boy Scooby!");
        winecellar.setItem("barrels", barrels);
        winebox = new ItemMember ("winebox.", "Oh no! The ghost leaps out and fights Fred and pushes him down a trap door! You have now lost Fred.", " ");
        winecellar.setItem("winebox", winebox);
        supplybox = new Items ("supplybox", "Just a box full of corks. Nothing really to see here, Scoob.");
        winecellar.setItem("supplybox", supplybox);
        
        // Items for the Dungeon
        tapestries = new Items ("tapestries", "Look! Footprints that lead to a secret passageway! A clue! A clue! Good boy Scooby!");
        dungeon.setItem("tapestries", tapestries);
        closet = new Items ("closet", "Eeeek! There are swords and axes, Scoob! But nothing that would help solve the mystery.");
        dungeon.setItem("closet", closet);
        cell = new ItemMember ("cell", " Wow, this would be great place to hide someone, right Scoobs? But no clues here",
                                "You found Daphne locked in the cell! Daphne is now back with the gang. Good boy Scooby!");
        dungeon.setItem("cell", cell);
        alcove = new Items ("alcove", "As creepy and dark as this is, looks like nothing is here, Scoob.");
        dungeon.setItem("alcove", alcove);
        
        // Items for the Bathroom
        bathtub = new Items ("bathtub", "Don't see anything, but sure looks like a creepy tub to take a bath in!");
        bathroom.setItem("bathtub", bathtub);
        BathroomCabinet = new ItemMember ("BathroomCabinet", "Hmm... there's just stomach ache medicine here", 
                                            "You found stomach ache medicine for Shaggy! Shaggy is now back with the gang. Good boy Scooby!");
        bathroom.setItem("BathroomCabinet", BathroomCabinet);
        sink = new Items ("sink.", "Cleaning supplies and toilet paper. Dr. Alcott sure kept his mansion spotless, Scoob!");
        bathroom.setItem("sink", sink);
        box = new Items ("box", "A shaving kit. Not really a clue. Keep looking, Scooby Doo!");
        bathroom.setItem("box", box);
        
        // Items for the Father's Bathroom
        nightstand = new Items ("nightstand", "A Bible, two notebooks, and some writing pens. Nothing out of the ordinary.");
        fatherbedroom.setItem("nightstand", nightstand);
        bed = new Items ("bed", "Nothing under the bed. Guess we have to try again, Scoob.");
        fatherbedroom.setItem("bed", bed);
        FatherCloset = new Items ("FatherCloset", "Look! A safe! A clue! A clue! I wonder how we can open it? Good boy Scooby!");
        fatherbedroom.setItem("FatherCloset", FatherCloset);
        dresser = new Items ("dresser", "Just a bunch of clothes and old famous doctor like Dr. Alcott would wear!");
        fatherbedroom.setItem("dresser", dresser);
        
        // Items for the son's bedroom
        SonDesk = new Items ("SonDesk", "Look! An angry letter on debt that Wren owes! A clue! A clue! Good boy Scooby!");
        sonbedroom.setItem("SonDesk", SonDesk);
        wardrobe = new Items ("wardrobe", "Hmmmm. Wren has nice taste in clothes. Atta boy!");
        sonbedroom.setItem("wardrobe", wardrobe);
        SonNightstand = new Items ("SonNightstand", "The drawers are emptry. Interesting...");
        sonbedroom.setItem("SonNightstand", SonNightstand);
        SonBed = new ItemMember ("SonBed", "Oh no! The ghost appears and whisks Daphne away! You have now lost Daphne.", " ");
        sonbedroom.setItem("SonBed", SonBed);
    }
    
    /**
     * .createMembers.
     * Creates the members that are with the player at the beginning of the game
     */
    private void createMembers(){
        Fred = new MysteryInc("Fred");
        Velma = new MysteryInc("Velma");
        Daphne = new MysteryInc("Daphne");
        Shaggy = new MysteryInc("Shaggy");
        
        // Adds members to the arraylist
        members.add(Fred);
        members.add(Velma);
        members.add(Daphne);
        members.add(Shaggy);
        
    }
    
    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the Scooby Doo Adventure: Haunted Mansion!");
        System.out.println("Dr. William Alcott, an incredibly famous and wealthy doctor, has vanished without a trace! Mystery Inc. has been called in by Dr. Alcott's son, Wren, to check");
        System.out.println("out reports that his father's ghost has been seen haunting the mansion where he was living. Mr. Alcott resided in this mansion with Wren, a caretaker, a chef,");
        System.out.println("and a gardener. You will be playing as Scooby Doo and will lead the Mystery Gang around the mansion to discover if the ghost is real and if not who kinapped Dr. Alcott.");
        System.out.println();
        System.out.println("There are eleven rooms on three floors in this mansion that the Mystery Gang will be able to explore. On their journey, the gang must find clues strewn about the");
        System.out.println("mansion, but WATCH OUT! There are accidents that can happen that will cause Scooby to lose a gang member along the way. Each accident can be counteracted by something");
        System.out.println("located somewhere else in the Mansion that will return the member to the gang. You will start off in the living room.");
        System.out.println();
        System.out.println("The living room is the center of the house and is the only official way to go up or down to the other floors. The caretaker will be in the living room to answer");
        System.out.println("appropriate questions. You will also go to him if you wish to guess who the ghost is. Scooby Doo must have at least 5 clues found and 2 gang members STILL with");
        System.out.println("him in order to guess who the Ghost of the Haunted Mansion is. A correct guess will win the game."); 
            //while a wrong guess will  cause you to lose the game increase the clue or member requirement +1.");
            // Got rid of the increasing requirement bc no time to include it
        System.out.println("If Scooby has all clues and gang members with him when he guesses wrong, he will lose the game. Happy Hunting Scooby Doo!");
        System.out.println();
        System.out.println("Type '" + CommandWord.GO + "' to go to a room.");
        System.out.println("Type '" + CommandWord.INSPECT + "' to inspect objects in a room.");
        System.out.println("Type '" + CommandWord.COLLECT + "' to collect items.");
        System.out.println("Type '" + CommandWord.INVENTORY + "' to view items or members you have.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println("Type '" + CommandWord.QUIT + "' to quit the game.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }
    
    /**
     * .printInventory.
     * Prints a list of items that the player currently has on them
     */
    private void printInventory(){
        Items itemCollected;
        String item;
        System.out.println("You currently have the following items:");
        for (int i = 0; i < items.size(); i++) {
            itemCollected = items.get(i);
            item = itemCollected.getItemName();            
            System.out.print(i + 1 + ". " + item);
        }
    }
    
     /**
     * .printMembers.
     * Prints a list of members that the player currently has with them
     */
    
    private void printMembers(){
        System.out.println("These gang members are still with you:");
        for (int i = 0; i < members.size(); i++) {
            thisMember = (MysteryInc) members.get(i);
            if (thisMember.getPresent()){
                thisMember.print();
            }
        }
    }
    
    
    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
            System.out.println("I don't know what you mean...");
            break;

            case HELP:
            printHelp();
            break;

            case GO:
            goRoom(command);
            break;

            case INSPECT:
            goItem(command);
            break;
            
            case INVENTORY:
            goInventory(command);
            break;
            
            case COLLECT:
            goItemCollect(command);            
            break;

            case QUIT:
            wantToQuit = quit(command);
            break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around the Haunted Mansion.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            
            return;

        }

        String direction = command.getSecondWord();
        String action = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        //String nextAction = currentRoom.getAction(action);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }
    
    /**
     * .goItem.
     * This method deals with inspecting items
     */
    private void goItem(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to inspect...
            System.out.println("Inspect what?");
            return;
        }  
        
        String itemName = command.getSecondWord();
        Items itemInRoom = currentRoom.items.get(itemName);
        String personLostItem = command.getSecondWord();
        if (itemInRoom != null){
            
            if (itemInRoom instanceof ItemMember){
                present(personLostItem);
            } else {
                String Description = itemInRoom.getItemDescription();
                System.out.println(Description);
            }
            
        } else {
            System.out.println("Sorry, this item is not in this room or it does not exist");
            return;
        }

    }
    
    /**
     * .present.
     * Determines if a person needs to be lost and set the present boolean to false
     */
    private void present(String personLostItem){
        boolean present;
        switch (personLostItem){
            // loses a member
            case "winebox":
                System.out.println(winebox.getItemDescription());
                Fred.loseMember();
                break;
            
            case "mosileum":
                System.out.println(mosileum.getItemDescription());
                Velma.loseMember();
                break;
            
            case "counters":
                System.out.println(counters.getItemDescription());
                Shaggy.loseMember();
                break;
            
            case "SonBed":
                System.out.println(SonBed.getItemDescription());
                Daphne.loseMember();
                break;
            
            // rescue a member back
            case "statue":
                if(!Fred.getPresent()){
                    Fred.saveMember();
                    System.out.println(statue.getAltDescription());
                } else {
                    System.out.println(statue.getItemDescription());
                }
                break;
            
            case "fireplace":
                thisMember = Velma;
                if(thisMember.getPresent()){
                    System.out.println(fireplace.getItemDescription());
                } else {
                    
                    Velma.saveMember();
                    System.out.println(fireplace.getAltDescription());
                }
                break;
            
            case "BathroomCabinet":
                if(!Shaggy.getPresent()){
                    Shaggy.saveMember();
                    System.out.println(BathroomCabinet.getAltDescription());
                } else {
                    System.out.println(BathroomCabinet.getItemDescription());
                }
                break;
            
            case "cell":
                if(!Daphne.getPresent()){
                    Daphne.saveMember();
                    System.out.println(cell.getAltDescription());
                } else {
                    System.out.println(cell.getItemDescription());
                }
                break;
        }
    }
    
    /**
     * .goItemCollect.
     * This method deals with picking up items
     * When the item is pick up here, it needs to stored in the player's arraylist (inventory)
     */
    private void goItemCollect(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to collect...
            System.out.println("Collect what?");
         
            return;

        }  
        String itemName = command.getSecondWord();
        Items itemInRoom = currentRoom.items.get(itemName);
        
        if (itemInRoom != null){
            // checks to see if the item is able to be collected or not
           boolean collectItem = itemInRoom.getPickUp(); 
            if (collectItem){
                String Description = itemInRoom.getItemDescription();
                System.out.println(Description);
                items.add(itemInRoom); //add the item in the array list items
            } else {
                System.out.println("Sorry, this item cannot be picked up");
                return;
           }
        } else {
            System.out.println("Sorry, this item is not in this room or it does not exist");
            return;
        }

    }
    
    
    /**
     * .goInventory. 
     * prints a list of mystery gang members currently with scooby or the item Scooby has
     */
    private void goInventory(Command command){
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know which inventory to print...
            System.out.println("To view your items inventory please type 'Inventory items'");
            System.out.println("To view the gang members still with you please type 'Inventory members'");
            return;
        } else if(command.getSecondWord().equals("items")){
            printInventory();
        } else if(command.getSecondWord().equals("members")){
            printMembers();
        }
        
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
