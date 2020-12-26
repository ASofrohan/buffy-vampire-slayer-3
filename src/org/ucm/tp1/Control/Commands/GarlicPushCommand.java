package org.ucm.tp1.Control.Commands;

import org.ucm.tp1.Logic.Game;
import org.ucm.tp1.Logic.GameObjects.IAttack;

public class GarlicPushCommand extends Command{
	public GarlicPushCommand() {
		super("garlic", "g", "details", "help");
	}
	
	@Override
	public boolean execute(Game game) {
		boolean validCommand = false;
		if(game.getGameObjectBoard().getPlayer().getCoins() >= 10) {	//garlic push to every objects
			game.getGameObjectBoard().getPlayer().setCoins(game.getGameObjectBoard().getPlayer().getCoins()-10);
			for(int i = 0; i < game.getGameObjectBoard().getObjectList().getGameObjects().size() ; i++ ) {
				IAttack other = game.getGameObjectBoard().getObjectList().getGameObjects().get(i);
				if(other != null) other.receiveGarlicPush();
			}
			validCommand = true;
			game.update();
		}
		else System.out.println("[ERROR] You don't have enough coins.");
		return validCommand;
	}
	
	@Override
	public Command parse(String[] commandWords) {
		return parseNoParamsCommand(commandWords);
	}
}
