package org.ucm.tp1.Control.Commands;
import org.ucm.tp1.Control.Exceptions.*;
import org.ucm.tp1.Control.Exceptions.NumberFormatException;
import org.ucm.tp1.Logic.Game;
import org.ucm.tp1.Logic.GameObjects.GameObject;

public class AddVampireCommand extends Command{
	int posX;
	int posY;
	String vampireType;
	
	public AddVampireCommand() {
		super("vampire", "v", "[v]ampire [<type>] <x> <y>", "add a vampire type in position x, y");
	}
	
	public AddVampireCommand(int x, int y, String vampireType) {
		super("vampire", "v", "[v]ampire [<type>] <x> <y>", "add a vampire type in position x, y");
		this.posX = x;
		this.posY = y;
		this.vampireType = vampireType;
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException{
	    boolean refreshDisplay = true;
	    try {
		    if (game.getGameObjectBoard().getObjectList().getvRemaining() <= 0) {		//no vampires reamining
		        throw new NoMoreVampiresException("[ERROR]: No more remaining vampires left");
		    }
		    if (posX < 0 || posX >= game.getLevel().getDim_x() || posY < 0 || posY >= game.getLevel().getDim_y()) {		//invalid position
		    	throw new InvalidPositionException("[ERROR]: Command " + name + ": " + invalidPosMsg);
		    }
		    else {
		    	switch (vampireType) {
		    	case "d":		//dracula
		    		if(GameObject.isDraculaAlive()) throw new DraculaIsAliveException("[ERROR]: Dracula is alive");
		    		game.getGameObjectBoard().addDraculaCommand(posY, posX, game);
		    		break;
		    	case "e":		//explosive vampire
		    		game.getGameObjectBoard().addExpVampireCommand(posY, posX, game);
		    		break;
		    	case " ":		//vampire
		    		game.getGameObjectBoard().addVampireCommand(posY, posX, game);
		    		break;
		    	default:		//unknown vampire
		    		throw new CommandExecuteException("[ERROR]: Unknown vampire type");    			    	
		    	}
		    }
	    }catch(CommandExecuteException e) {
	    	throw new CommandExecuteException(e.getMessage() + "\n[ERROR]: Failed to add vampire.");
	    }
	    return refreshDisplay;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException{
		try {		//return command with attributes
			if(commandWords.length == 3 && matchCommandName(commandWords[0])) {
				return new AddVampireCommand(Integer.parseInt(commandWords[1]), Integer.parseInt(commandWords[2]), " ");
			}
			if(commandWords.length == 4 && matchCommandName(commandWords[0])) {
				return new AddVampireCommand(Integer.parseInt(commandWords[2]), Integer.parseInt(commandWords[3]), commandWords[1]);
			}
		}catch(java.lang.NumberFormatException e) {
			throw new NumberFormatException("[ERROR]: Command " + name + ": " + incorrectArgsMsg);
		}
		if (commandWords.length == 1 && matchCommandName(commandWords[0])) {
			throw new CommandParseException("[ERROR]: Command " + name + ": " + incorrectNumberOfArgsMsg);
		}
		return parseNoParamsCommand(commandWords);
	}
}
