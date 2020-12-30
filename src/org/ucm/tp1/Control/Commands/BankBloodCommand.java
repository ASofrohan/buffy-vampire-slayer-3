package org.ucm.tp1.Control.Commands;

import org.ucm.tp1.Control.Exceptions.CommandExecuteException;
import org.ucm.tp1.Control.Exceptions.CommandParseException;
import org.ucm.tp1.Control.Exceptions.InvalidPositionException;
import org.ucm.tp1.Control.Exceptions.NumberFormatException;
import org.ucm.tp1.Logic.Game;

public class BankBloodCommand extends Command{
	int posX;
	int posY;
	int cost;
	
	public BankBloodCommand() {
		super("bank", "b", "[b]ank <x> <y> <z>", "add a blood bank in position x, y with z cost");
	}
	
	public BankBloodCommand(int x, int y, int cost) {
		super("bank", "b", "[b]ank <x> <y> <z>", "add a blood bank in position x, y with z cost");
		this.posX = x;
		this.posY = y;
		this.cost = cost;
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		boolean refreshDisplay = true;
		try {
			if (posX < 0 || posX >= game.getLevel().getDim_x()-1 || posY < 0 || posY >= game.getLevel().getDim_y()) {		//invalid position
	    		throw new InvalidPositionException("[ERROR]: Command " + name + ": " + invalidPosMsg);
	    	}
	    	game.getGameObjectBoard().addBankBlood(posY, posX, cost, game);		//bankblood not added
	    	game.update();
	    }catch(CommandExecuteException e) {
	    	throw new CommandExecuteException(e.getMessage() + "\n[ERROR]: Failed to add blood bank.");
	    }
	    return refreshDisplay;
	}
	
	@Override
	public Command parse(String[] commandWords) throws CommandParseException{
		if(commandWords.length == 4 && matchCommandName(commandWords[0])) {		//return command with attributes
			try {
				return new BankBloodCommand(Integer.parseInt(commandWords[1]), Integer.parseInt(commandWords[2]), Integer.parseInt(commandWords[3]));
			}catch(java.lang.NumberFormatException e) {
				throw new NumberFormatException("[ERROR]: Command " + name + ": " + incorrectArgsMsg);
			}
		}
		return parseNoParamsCommand(commandWords);
	}
}
