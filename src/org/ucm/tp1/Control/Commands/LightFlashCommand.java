package org.ucm.tp1.Control.Commands;

import org.ucm.tp1.Logic.Game;
import org.ucm.tp1.Logic.GameObjects.IAttack;

public class LightFlashCommand extends Command{
	
	public LightFlashCommand() {
		super("light", "l", "details", "help");
	}
	
	@Override
	public boolean execute(Game game) {
		boolean validCommand = false;
		if(game.getGameObjectBoard().getPlayer().getCoins() >= 50) {	
			game.getGameObjectBoard().getPlayer().setCoins(game.getGameObjectBoard().getPlayer().getCoins()-50);
			for(int i = 0; i < game.getGameObjectBoard().getObjectList().getGameObjects().size() ; i++ ) {		//execute light flash to every object
				IAttack other = game.getGameObjectBoard().getObjectList().getGameObjects().get(i);
				if(other != null) other.receiveLightFlash();
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
