package org.ucm.tp1.Control.Commands;

import org.ucm.tp1.Control.Exceptions.CommandExecuteException;
import org.ucm.tp1.Control.Exceptions.CommandParseException;
import org.ucm.tp1.Control.Exceptions.NotEnoughCoinsException;
import org.ucm.tp1.Logic.Game;
import org.ucm.tp1.Logic.GameObjects.IAttack;

public class GarlicPushCommand extends Command{
	public GarlicPushCommand() {
		super("garlic", "g", "[g]arlic", "push 1 position back every vampire");
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		boolean refreshDisplay = true;
		try {
			if(game.getGameObjectBoard().getPlayer().getCoins() < 10) {
				throw new NotEnoughCoinsException("[ERROR]: Garlic push cost is 10: Not enough coins");
			}
			//garlic push to every objects
			game.getGameObjectBoard().getPlayer().setCoins(game.getGameObjectBoard().getPlayer().getCoins()-10);
			for(int i = 0; i < game.getGameObjectBoard().getObjectList().getGameObjects().size() ; i++ ) {
				IAttack other = game.getGameObjectBoard().getObjectList().getGameObjects().get(i);
				if(other != null) other.receiveGarlicPush();
			}
			game.update();
		}catch(CommandExecuteException e) {
	    	throw new CommandExecuteException(e.getMessage() + "\n[ERROR]: Failed to Garlic push.");
	    }
		return refreshDisplay;
	}
	
	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		return parseNoParamsCommand(commandWords);
	}
}
