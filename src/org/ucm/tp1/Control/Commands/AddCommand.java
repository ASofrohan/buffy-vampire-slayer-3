package org.ucm.tp1.Control.Commands;
import org.ucm.tp1.Control.Exceptions.*;
import org.ucm.tp1.Control.Exceptions.NumberFormatException;
import org.ucm.tp1.Logic.Game;

public class AddCommand extends Command{
	
	int posX;
	int posY;
	
	public AddCommand() {
		super("add", "a", "details", "help");
	}
	
	public AddCommand(int x, int y) {
		super("add", "a", "details", "help");
		this.posX = x;
		this.posY = y;
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{
	    boolean validCommand = false;
	    if (posX < 0 || posX >= game.getLevel().getDim_x()-1 || posY < 0 || posY >= game.getLevel().getDim_y()) {		//invalid position
	        throw new InvalidPositionException("[ERROR]: Command " + name + ": " + invalidPosMsg);
	    }
	    else {
	        if (!game.getGameObjectBoard().addSlayer(posY, posX, game)) {		//slayer not added
	            validCommand = false;
	            System.out.println("[ERROR] Could not add slayer in that position. The position is occupied or you don't have enough coins.");
	        }
	        else {		//update game
	        	validCommand = true;
	    	    game.update();
	        }
	    }
	    return validCommand;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException{
		if(commandWords.length == 3 && matchCommandName(commandWords[0])) {			//return command with attributes
			try {
			return new AddCommand(Integer.parseInt(commandWords[1]), Integer.parseInt(commandWords[2]));
			}catch(java.lang.NumberFormatException e) {
				throw new NumberFormatException("[ERROR]: Command " + name + ": " + incorrectArgsMsg);
			}
		}
		return parseNoParamsCommand(commandWords);
	}
}
