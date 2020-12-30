package org.ucm.tp1.Control.Commands;
import java.util.Scanner;

import org.ucm.tp1.Control.Exceptions.CommandParseException;
import org.ucm.tp1.Logic.Game;

public class ExitCommand extends Command {
	
	public static final String confirmationMsg = String.format("Are you sure? (y/n)");
	public static final String unknownCommandMsg = String.format("Unknown command.");
    
	public ExitCommand() {
		super("exit", "e", "[e]xit", "exit game");
	}

	@Override
	public boolean execute(Game game) {
		if(confirm()) {
			System.out.println("[DEBUG]: Executing: " + this.name);
			game.setExitGame(true);
		}
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		return parseNoParamsCommand(commandWords);
	}
	
	public boolean confirm() {		//confirm exit
		boolean unknown = false;
	    boolean ret = false;
	    @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		do {
	       System.out.print(confirmationMsg);
	       System.out.print("\n" + "Command > ");
	       String input = scanner.nextLine();
	       if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
	           ret = true;
	           unknown = false;
	       }
	       else if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")) {
	           ret = false;
	           unknown = false;
	       }
	       else {
	    	   unknown = true;
	    	   System.out.print("Please, write yes or no. ");
	       }
		}while(unknown); 
	    return ret;
	}
}
