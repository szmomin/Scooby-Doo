import java.util.ArrayList;
import java.util.HashMap;
import java.lang.*;

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
    private ArrayList<String> cluesList;
    private HashMap<String, Clue> clues;
    private Clue thisClue;
    private String clueDescription;
    private int cluesNeedToWin;
    
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
    Items bookshelves, sofa, gravestone, pantry, cabinets, cabinet, diningtable, vase, armoire, wineshelf, supplybox, closet, alcove, bathtub, 
        BathroomCloset, sink, box, nightstand, bed, FatherCloset, dresser, wardrobe, SonNightstand;
            
    //Declares items that can be collected
    ItemCollect rope, key;
    
    //Declares itesm that effect members
    ItemMember fireplace, cell, statue, BathroomCabinet, winebox, mosileum, counters, SonBed;
    
    //Declares items that are clues
    Clue desk, toolshed, table, barrels, tapestries, SonDesk;
    

    /**
     * Create the game and initialise its internal map.
     */
    public Haunted_Mansion() 
    {
        cluesList = new ArrayList<String>();
        items = new ArrayList<Items>();
        members = new ArrayList<MysteryInc>();
        clues = new HashMap<String, Clue>();
        cluesNeedToWin = 4;
        createRooms();
        createItems();
        createMembers();
        parser = new Parser();
        play();
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
        desk = new Clue ("desk", "Google search history is on how to kidnap someone! A clue! A clue! Good boy Scooby!", "Search history on how to kidnap someone");
        bookshelves = new Items ("bookshelves", "Could these books be any more boring? Nothing here. Keep looking!");
        sofa = new Items ("sofa.", "Nothing here, but this sofa does look comfy enough for rainy day naps.");
        fireplace = new ItemMember ("fireplace", "Nothing, but it looks like a place for glasses to be lost",
                                    "You found Velma's glasses! Velma is now back with the gang. Good boy Scooby!");
        study.setItem("desk", desk);
        study.setItem("bookshelves", bookshelves);
        study.setItem("sofa", sofa);
        study.setItem("fireplace", fireplace);
        clues.put("desk", desk);
        
        // Items for the Cemetary
        toolshed = new Clue ("toolshed", "Look! Tape and a white sheet! A clue! A clue!", "Tape and white sheet found in the Cemetery's toolshed");
        gravestone = new Items ("gravestone", "There are a few cobwebs, but other than that, nothing!");
        mosileum = new ItemMember ("mosileum", "Oh no! Velma lost her glasses! You have now lost Velma.", " ");
        statue = new ItemMember ("statue", "Look! A rope that can be collected to maybe used to rescue someone later",
                                "The rope can be used to rescue Fred from the trapdoor! Fred is now back with the gang Good boy Scooby!");
        rope = new ItemCollect ("rope", "You have now picked up the rope. It is in your inventory", true);
        cemetary.setItem("toolshed", toolshed);
        cemetary.setItem("gravestone", gravestone);
        cemetary.setItem("mosileum", mosileum);
        cemetary.setItem("statue", statue);
        cemetary.setItem("rope", rope);
        clues.put("toolshed", toolshed);
        
        // Items for the Kitchen
        pantry = new Items ("pantry", "Well... nothing here. Oooh!!! A box of Scooby Snacks! Yum! Yum! Yum!");
        counters = new ItemMember ("counters", "Oh no! Shaggy ate all the food on the counter and got food poisoning! You have now lost Shaggy.", " ");
        table = new Clue ("table", "Under the table is a cellphone has to be Dr. Alcotts'! A clue! A clue! Last text is to the gardener arguing about a pay raise. Hmmmmm...", 
                            "Gardener's text arguing with Dr. Alcott about a pay raise");
        cabinets = new Items ("cabinets", "Unless you really like expensive silverware and porcelain dishes, this looks like a dead end to me, Scoob.");
        kitchen.setItem("pantry", pantry);
        kitchen.setItem("counters", counters);
        kitchen.setItem("table", table);
        kitchen.setItem("cabinets", cabinets);
        clues.put("table", table);
        
        // Items for the Dinning Room
        cabinet = new Items ("cabinet", "So many dishes! So few clues! Try again Scooby Doo!");
        diningtable = new Items ("diningtable", "What an incredibly large table! The Alcott family must have been monstrously large at some point.");
        vase = new Items ("vase", "Here is a key! A clue! A clue! It must unlock the safe! You have found Dr. Alcott's will! Good boy Scooby!");
        armoire = new Items ("armoire", "Oops. Nothing here. Keep looking Scooby!");
        key = new ItemCollect ("key", "You have now picked up the key. It is in your inventory", true);
        diningroom.setItem("cabinet", cabinet);
        diningroom.setItem("diningtable", diningtable);
        diningroom.setItem("vase", vase);
        diningroom.setItem("armoire", armoire);
        diningroom.setItem("key", key);
        
        // Items for the Winecellar
        wineshelf = new Items ("wineshelf", "Hmmm. According to the dust on these old wine bottles here, they have not been moved in years. Keep looking, Scooby!");
        barrels = new Clue ("barrels", "Look! A watch! It looks like Dr. Alcott's watch from the painting over the fireplace! A clue! A clue! Good boy Scooby!",
                             "Dr. Alcotts watch found near the barrels in the winecellar");
        winebox = new ItemMember ("winebox.", "Oh no! The ghost leaps out and fights Fred and pushes him down a trap door! You have now lost Fred.", " ");
        supplybox = new Items ("supplybox", "Just a box full of corks. Nothing really to see here, Scoob.");
        winecellar.setItem("wineshelf", wineshelf);
        winecellar.setItem("barrels", barrels);
        winecellar.setItem("winebox", winebox);
        winecellar.setItem("supplybox", supplybox);
        clues.put("barrels", barrels);
        
        // Items for the Dungeon
        tapestries = new Clue ("tapestries", "Look! Footprints that lead to a secret passageway! A clue! A clue! Good boy Scooby!", 
                                "Footprints leading to secret passageway from the Dungeon");
        closet = new Items ("closet", "Eeeek! There are swords and axes, Scoob! But nothing that would help solve the mystery.");
        cell = new ItemMember ("cell", " Wow, this would be great place to hide someone, right Scoobs? But no clues here",
                                "You found Daphne locked in the cell! Daphne is now back with the gang. Good boy Scooby!");
        alcove = new Items ("alcove", "As creepy and dark as this is, looks like nothing is here, Scoob.");
        dungeon.setItem("tapestries", tapestries);
        dungeon.setItem("closet", closet);
        dungeon.setItem("cell", cell);
        dungeon.setItem("alcove", alcove);
        clues.put("tapestries", tapestries);
        
        // Items for the Bathroom
        bathtub = new Items ("bathtub", "Don't see anything, but sure looks like a creepy tub to take a bath in!");
        BathroomCabinet = new ItemMember ("BathroomCabinet", "Hmm... there's just stomach ache medicine here", 
                                            "You found stomach ache medicine for Shaggy! Shaggy is now back with the gang. Good boy Scooby!");
        sink = new Items ("sink.", "Cleaning supplies and toilet paper. Dr. Alcott sure kept his mansion spotless, Scoob!");
        box = new Items ("box", "A shaving kit. Not really a clue. Keep looking, Scooby Doo!");
        bathroom.setItem("bathtub", bathtub);
        bathroom.setItem("BathroomCabinet", BathroomCabinet);
        bathroom.setItem("sink", sink);
        bathroom.setItem("box", box);
        
        // Items for the Father's Bathroom
        nightstand = new Items ("nightstand", "A Bible, two notebooks, and some writing pens. Nothing out of the ordinary.");
        bed = new Items ("bed", "Nothing under the bed. Guess we have to try again, Scoob.");
        FatherCloset = new Items ("FatherCloset", "Look! A safe! A clue! A clue! I wonder how we can open it? Good boy Scooby!");
        dresser = new Items ("dresser", "Just a bunch of clothes and old famous doctor like Dr. Alcott would wear!");
        // Needs a clue about the will
        fatherbedroom.setItem("nightstand", nightstand);
        fatherbedroom.setItem("bed", bed);
        fatherbedroom.setItem("FatherCloset", FatherCloset);
        fatherbedroom.setItem("dresser", dresser);
        
        // Items for the son's bedroom
        SonDesk = new Clue ("SonDesk", "Look! An angry letter on debt that Wren owes! A clue! A clue! Good boy Scooby!", "Angry debtors letter addressed to Wren");
        wardrobe = new Items ("wardrobe", "Hmmmm. Wren has nice taste in clothes. Atta boy!");
        SonNightstand = new Items ("SonNightstand", "The drawers are emptry. Interesting...");
        SonBed = new ItemMember ("SonBed", "Oh no! The ghost appears and whisks Daphne away! You have now lost Daphne.", " ");
        sonbedroom.setItem("SonDesk", SonDesk);
        sonbedroom.setItem("wardrobe", wardrobe);
        sonbedroom.setItem("SonNightstand", SonNightstand);
        sonbedroom.setItem("SonBed", SonBed);
        clues.put("SonDesk", SonDesk);
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
        System.out.println("Type '" + CommandWord.INVENTORY + "' to view items, members, or clues you have.");
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
     * .printClues.
     * Prints a list of clues that the player currently has
     */
    private void printClues(){
        System.out.println("You have " + cluesList.size() + " clues");
        for (int i = 0; i < cluesList.size(); i++) {
            System.out.println(i + 1 + ". " + cluesList.get(i));
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
            
            case GUESS:
            goGuess(command);            
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
        Clue clueInRoom;
        String personLostItem = command.getSecondWord();
        if (itemInRoom != null){
            String Description = itemInRoom.getItemDescription();
            // Checks if item is losing or gaining a member
            if (itemInRoom instanceof ItemMember){ 
                present(personLostItem);
                // Checks if item is a clue that can be stored or not
            } else if (itemInRoom instanceof Clue){ 
                //insert into arraylist
                System.out.println(Description);
                clueInRoom = clues.get(itemName);
                cluesList.add(clueInRoom.getClueDescription());
            } else {
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
     * .goGuess.
     * This method deals with making a guess as to who the culprit is
     * You initially need four clues and 2 team members
     */
    private void goGuess(Command command) 
    {
        int cluesHave = cluesList.size();
        int cluesNeed = cluesNeedToWin - cluesHave;
        if (cluesHave >= cluesNeedToWin && currentRoom.equals(livingroom)){
            System.out.println("It seems you have solved this mystery. Who do you think is the culprit?");
            System.out.println("Type the command 'It's' and then the name of the person you suspect");
        } else if (cluesHave >= cluesNeedToWin && !currentRoom.equals(livingroom)){
            System.out.println("You must go to the caretaker in the livingroom in order to guess");
        } else if (cluesHave < cluesNeedToWin){
            System.out.println("You need " + cluesNeed + " more clues in order to guess");
        } else if (cluesHave == clues.size()){
            System.out.println("This is your last try to guess.");
            System.out.println("Type the command 'It's' and then the name of the person you suspect");
        }
        return;
    }  
        
    
    /**
     * .goSuspect.
     * This method deals with making a guess as to who the culprit is
     * You initially need four clues and 2 team members
     * but evertime you guess wrong, you must collect one additional clue
     * You lose once you have all ten clues and still guess incorrectly. 
     */
    private void goSuspect(Command command) 
    {
        int cluesHave = cluesList.size();
        int cluesNeed = cluesNeedToWin - cluesHave;
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know who you suspect is the culprit...
            System.out.println("Who do you suspect?");
            System.out.println("Type the command 'It's' and then the name of the person you suspect");
            return;
        } else if (cluesHave >= cluesNeedToWin && !currentRoom.equals(livingroom)){
            System.out.println("You must first go to the caretaker in the livingroom, tell him you are ready to guess.");
            return;
        } else if (cluesHave < cluesNeedToWin){
            System.out.println("You need " + cluesNeed + " more clues in order to guess");
            return;
        }
        
        String suspect = command.getSecondWord();
        //Items itemInRoom = currentRoom.items.get(itemName);
        if (suspect.equals("Wren")){
            System.out.println("Congratulations! You've solved the mystery!");
            return;
        } else {
            cluesNeedToWin =+ 1;
            System.out.println("That is incorrect. Please go search for more clues then try again.");
            System.out.println("You now need a total of " + cluesNeedToWin + " number of clues to guess again");
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
            System.out.println("To view your items type 'Inventory items', members type 'Inventory members', or clues 'Inventory clues'");
            //System.out.println("To view the gang members still with you please type 'Inventory members'");
            return;
        } else if(command.getSecondWord().equals("items")){
            printInventory();
        } else if(command.getSecondWord().equals("members")){
            printMembers();
        } else if (command.getSecondWord().equals("clues")){
            printClues();
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
