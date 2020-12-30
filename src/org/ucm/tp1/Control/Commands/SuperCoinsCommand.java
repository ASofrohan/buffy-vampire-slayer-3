package org.ucm.tp1.Control.Commands;

import org.ucm.tp1.Control.Exceptions.CommandParseException;
import org.ucm.tp1.Logic.Game;

public class SuperCoinsCommand extends Command {
	
	public SuperCoinsCommand() {
		super("coins", "c", "[c]oins", "give 1000 coins to the player");
	}
	
	public boolean execute(Game game) {
		game.getGameObjectBoard().getPlayer().setCoins(game.getGameObjectBoard().getPlayer().getCoins()+1000);;		
		return true;
	}
	
	public Command parse(String[] commandWords) throws CommandParseException {
		return parseNoParamsCommand(commandWords);
	}
}
