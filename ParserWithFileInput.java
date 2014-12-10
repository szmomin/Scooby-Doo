import java.util.Scanner;


/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two word command. It returns the command
 * as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class ParserWithFileInput 
{
    private CommandWords commands;  // holds all valid command words
    private Scanner reader;         // OLD source of command input
    private ReadFile fileReader; 
    private String inputLine;

    /**
     * Create a parser to read from the terminal window.
     */
    public ParserWithFileInput() 
    {
        commands = new CommandWords();
        //reader = new Scanner(System.in);
        fileReader = new ReadFile("GuidedTourCommands.txt");
        
    }

    /**
     * @return The next command from the user.
     */
    public Command getCommand() 
    {
        String inputLine;   // will hold the full input line
        String word1 = null;
        String word2 = null;

        System.out.print("> ");     // print prompt

        fileReader.readInputLine();
        

        if(fileReader.eof())
        {
            word1="quit";
            word2=null;
            return new Command(commands.getCommandWord(word1), word2);
            //return new Command(word1, word2);
        }
        
        inputLine = fileReader.getWholeLine();
        System.out.println(inputLine);
        // Find up to two words on the line.
        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();      // get first word
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next();      // get second word
                // note: we just ignore the rest of the input line.
            }
        }

        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).
        if(commands.isCommand(word1)) {
            return new Command(commands.getCommandWord(word1), word2);
            //return new Command(word1, word2);
        }
        else {
            return new Command(null, word2); 
        }
    }
    //public void showCommands()
    //{
    //    commands.showAll();
    //}
}
