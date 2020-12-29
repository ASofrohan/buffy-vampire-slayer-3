package org.ucm.tp1.Control.Commands;

import java.util.Scanner;
import org.ucm.tp1.Logic.Level;
import org.ucm.tp1.Logic.GameObjects.GameObject;
import org.ucm.tp1.Control.Exceptions.CommandParseException;
import org.ucm.tp1.Logic.Game;
import org.ucm.tp1.Logic.GameObjectBoard;

public class ResetCommand extends Command {
	
	long seedBup;
	Level lBup;
	
	public static final String helpMsg = String.format(
            "Available commands:%n" +
            "[a]dd <x> <y>: add a slayer in position x, y%n" +
            "[h]elp: show this help%n" + 
            "[r]eset: reset game%n" + 
            "[e]xit: exit game%n"+ 
            "[n]one | []: update%n");
	public static final String confirmationMsg = String.format("Are you sure? (y/n)");
	public static final String unknownCommandMsg = String.format("Unknown command.");
	
	public ResetCommand() {
		super("reset", "r", "details", "help");
	}

	@Override
	public boolean execute(Game game) {
		if (confirm()) {		//reset attributes
			game.setSeed(game.getSeedBackup());
			game.setCycles(0);
			game.setGameObjectBoard(new GameObjectBoard(game.getLevel()));
			game.setExitGame(false);
			game.setWin(false);
			GameObject.setDraculaAlive(false);
			GameObject.setvAliveStatic(0);
			GameObject.setTotalRefound(0);
        }
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		return parseNoParamsCommand(commandWords);
	}
	
	public boolean confirm() {
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
