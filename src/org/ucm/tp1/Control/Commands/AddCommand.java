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
	    boolean refreshDisplay = true;
	    try {
	    	if (posX < 0 || posX >= game.getLevel().getDim_x()-1 || posY < 0 || posY >= game.getLevel().getDim_y()) {		//invalid position
	    		throw new InvalidPositionException("[ERROR]: Command " + name + ": " + invalidPosMsg);
	    	}
	    	game.getGameObjectBoard().addSlayer(posY, posX, game);		//slayer not added
	    	game.update();
		}catch(CommandExecuteException e) {
			throw new CommandExecuteException(e.getMessage() + "\n[ERROR]: Failed to add slayer.");
		}
	    return refreshDisplay;
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
