/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
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
    private Parser parser;
    private Room currentRoom;

    /**
     * Create the game and initialise its internal map.
     */
    public Haunted_Mansion() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room cemetary, study, kitchen, diningroom, livingroom, hallway, winecellar, dungeon, bathroom, fatherbedroom, sonbedroom;

        // create the rooms
        cemetary = new Room("You are now outside in the back garden, which turns out to be a creepy and spooky graveyard!!!");
        study = new Room("You are in Dr. Alcott's study, which is also a library. There's a huge desk in the middle of the room.");
        kitchen = new Room("You are now in the mansion's massive kitchen, where there is sure to be a delicious feast waiting. Ain't that right, Scoob?");
        diningroom = new Room("You are now in the dining room. Watch out for the expensive china and ancient paintings Scooby!");
        hallway = new Room("You are now in the upstairs hallway. Which room would you like to enter from here?");
        winecellar = new Room("You are now in the wine cellar with wonderful vintage wine and stacks of huge wooden barrels. Tread carefully, Scooby . . . ");
        dungeon = new Room("You are now in the dungeon of the mansion. It used to hold prisoners and slaves a long time ago. Now it's mainly used for storage.");
        bathroom = new Room("You are now in the bathroom. Don't forget to flush!");
        fatherbedroom = new Room("You are now in the father's bedroom. Could there possibly be a clue here too, Scooby Doo?");
        sonbedroom = new Room("You are now in the son's bedroom. Let's check it out, Scoobs!");
        livingroom = new Room("You are now in the living room. Ask the caretaker a question or go to another room.");

        // initialise room exits
        cemetary.setAction("Inspect the grave stone", "There are a few cobwebs, but other than that, nothing!");
        cemetary.setAction("Inspect the Mosileum", "Oh no! Velma lost her glasses! You have now lost Velma.");
        cemetary.setAction("Inspect the tool shed.", "Uhhhh... yep. There are only gardening tools here!");
        cemetary.setAction("Inspect the broken angel statue", "Look! A watch! It looks like Dr. Alcott's watch from the painting over the fireplace! A clue! A clue! Good boy Scooby!");
        cemetary.setExit("Go left to the kitchen", kitchen);

        study.setAction("Inspect Mr. Alcott's desk", "Google search history is on how to kidnap someone! A clue! A clue! Good boy Scooby!");
        study.setAction("Inspect the bookshelves", "Could these books be any more boring? Nothing here. Keep looking!");
        study.setAction("Inspect the reading sofa.", "Nothing here, but this sofa does look comfy enough for rainy day naps.");
        study.setAction("Inspect the fireplace", "You found Velma's glasses! Velma is now back with the gang. Good boy Scooby!");
        study.setExit("Go right to the living room", livingroom);

        kitchen.setAction("Inspect the pantry", "Well... nothing here. Oooh!!! A box of Scooby Snacks! Yum! Yum! Yum!");
        kitchen.setAction("Inspect the counters", "Oh no! Shaggy ate all the food on the counter and got food poisoning! You have now lost Shaggy.");
        kitchen.setAction("Inspect the kitchen table.", "Under the table is a cellphone. It has to be Dr. Alcotts'! A clue! A clue! Last text is to the gardener arguing about a pay raise. Hmmmmm...");
        kitchen.setAction("Inspect the cabinets", "Unless you really like expensive silverware and porcelain dishes, this looks like a dead end to me, Scoob.");
        kitchen.setExit("Go south to the dining room", diningroom);
        kitchen.setExit("Go right to the cemetary", cemetary);

        diningroom.setAction("Inspect the china cabinet", "So many dishes! So few clues! Try again Scooby Doo!");
        diningroom.setAction("Inspect the dining room table", "What an incredibly large table! The Alcott family must have been monstrously large at some point.");
        diningroom.setAction("Inspect the jade vase next to the armoire.", "Here is a key! A clue! A clue! It must unlock the safe! You have found Dr. Alcott's will! Good boy Scooby!");
        diningroom.setAction("Inspect the wooden armoire", "Oops. Nothing here. Keep looking Scooby!");
        diningroom.setExit("Go left to the living room", livingroom);
        diningroom.setExit("Go north to the kitchen", kitchen);

        hallway.setExit("Go left to Dr. Alcott's bedroom", fatherbedroom);
        hallway.setExit("Go right to Wren's bedroom", sonbedroom);
        hallway.setExit("Go north to the bathroom", bathroom);
        hallway.setExit("Go down to the living room", livingroom);

        winecellar.setAction("Inspect the wine shelf", "Hmmm. According to the dust on these old wine bottles here, they have not been moved in years. Keep looking, Scooby!");
        winecellar.setAction("Inspect the wine barrels", "Look! A rope and a white sheet! A clue! A clue! The rope can rescue Fred from the trapdoor! Fred is now back with the gang Good boy Scooby!");
        winecellar.setAction("Inspect the bottle supply box.", "Oh no! The ghost leaps out and fights Fred and pushes him down a trap door! You have now lost Fred.");
        winecellar.setAction("Inspect the cork supply box", "Just a box full of corks. Nothing really to see here, Scoob.");
        winecellar.setExit("Go up to the living room", livingroom);
        winecellar.setExit("Go right to the dungeon", dungeon);

        dungeon.setAction("Inspect the tapestries on the wall.", "Look! Footprints that lead to a secret passageway! A clue! A clue! Good boy Scooby!");
        dungeon.setAction("Inspect the weapons closet", "Eeeek! There are swords and axes, Scoob! But nothing that would help solve the mystery.");
        dungeon.setAction("Inspect the dungeon cell", "You found Daphne locked in the cell! Daphne is now back with the gang. Good boy Scooby!");
        dungeon.setAction("Inspect the alcove in the corner", "As creepy and dark as this is, looks like nothing is here, Scoob.");
        dungeon.setExit("Go left to the wine cellar", winecellar);
        dungeon.setExit("Go up the secret passageway", cemetary);

        bathroom.setAction("Inspect the bathtub", "Don't see anything, but sure looks like a creepy tub to take a bath in!");
        bathroom.setAction("Inspect the medicine cabinet", "You found stomachache medicine for Shaggy! Shaggy is now back with the gang. Good boy Scooby!");
        bathroom.setAction("Inspect under the sink.", "Cleaning supplies and toilet paper. Dr. Alcott sure kept his mansion spotless, Scoob!");
        bathroom.setAction("Inspect the small box on the counter", "A shaving kit. Not really a clue. Keep looking, Scooby Doo!");
        bathroom.setExit("Go south to the hallway", hallway);

        fatherbedroom.setAction("Inspect the nightstand", "A Bible, two notebooks, and some writing pens. Nothing out of the ordinary.");
        fatherbedroom.setAction("Inspect under the bed", "Nothing under the bed. Guess we have to try again, Scoob.");
        fatherbedroom.setAction("Inspect the closet", "Look! A safe! A clue! A clue! I wonder how we can open it? Good boy Scooby!");
        fatherbedroom.setAction("Inspect the dresser", "Just a bunch of clothes and old famous doctor like Dr. Alcott would wear!");
        fatherbedroom.setExit("Go right to the hallway", hallway);

        sonbedroom.setAction("Inspect inside the wooden desk", "Look! An angry letter on debt that Wren owes! A clue! A clue! Good boy Scooby!");
        sonbedroom.setAction("Inspect the wardrobe", "Hmmmm. Wren has nice taste in clothes. Atta boy!");
        sonbedroom.setAction("Inspect the nightstand", "The drawers are emptry. Interesting...");
        sonbedroom.setAction("Inspect under the bed", "Oh no! The ghost appears and whisks Daphne away! You have now lost Daphne.");
        sonbedroom.setExit("Go left to the hallway", hallway);

        livingroom.setExit("Go left", study);
        livingroom.setExit("Go right to the dining room", diningroom);
        livingroom.setExit("Go up to the hallway", hallway);
        livingroom.setExit("Go down to the wine cellar", winecellar);

        currentRoom = livingroom;  // start game outside
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
        System.out.println("There are eleven rooms on three floors in this mansion that the Myster Gang will be able to explore. On their journey, the gang must find clues strewn about the");
        System.out.println("mansion, but WATCH OUT! There are accidents that can happen that will cause Scooby to lose a gang member along the way. Each accident can be counteracted by something");
        System.out.println("located somewhere else in the Mansion that will return the member to the gang. You will start off in the living room.");
        System.out.println();
        System.out.println("The living room is the center of the house and is the only official way to go up or down to the other floors. The caretaker will be in the living room to answer");
        System.out.println("answer appropriate questions. You will also go to him if you wish to guess who the ghost is. Scooby Doo must have at least 5 clues found and 2 gang members STILL with");
        System.out.println("him in order to guess who the Ghost of the Haunted Mansion is. A correct guess will win the game, while a wrong guess will increase the clue or member requirement +1.");
        System.out.println("If Scooby has all clues and gang members with him when he guesses wrong, he will lose the game. Happy Hunting Scooby Doo!");
        System.out.println();
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
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
            goRoom(command);
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
        String nextAction = currentRoom.getAction(action);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
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
