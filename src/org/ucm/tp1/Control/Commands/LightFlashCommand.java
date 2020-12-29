package org.ucm.tp1.Control.Commands;

import org.ucm.tp1.Control.Exceptions.CommandExecuteException;
import org.ucm.tp1.Control.Exceptions.CommandParseException;
import org.ucm.tp1.Control.Exceptions.NotEnoughCoinsException;
import org.ucm.tp1.Logic.Game;
import org.ucm.tp1.Logic.GameObjects.IAttack;

public class LightFlashCommand extends Command{
	
	public LightFlashCommand() {
		super("light", "l", "details", "help");
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		boolean refreshDisplay = true;
		try {
			if(game.getGameObjectBoard().getPlayer().getCoins() < 50) {
				throw new NotEnoughCoinsException("[ERROR]: Light flash cost is 50: Not enough coins");
			}
			game.getGameObjectBoard().getPlayer().setCoins(game.getGameObjectBoard().getPlayer().getCoins()-50);
			for(int i = 0; i < game.getGameObjectBoard().getObjectList().getGameObjects().size() ; i++ ) {		//execute light flash to every object
				IAttack other = game.getGameObjectBoard().getObjectList().getGameObjects().get(i);
				if(other != null) other.receiveLightFlash();
			}
			game.update();
	    }catch(CommandExecuteException e) {
	    	throw new CommandExecuteException(e.getMessage() + "\n[ERROR]: Failed to Light flash.");
	    }
		return refreshDisplay;
	}
	
	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		return parseNoParamsCommand(commandWords);
	}
}
