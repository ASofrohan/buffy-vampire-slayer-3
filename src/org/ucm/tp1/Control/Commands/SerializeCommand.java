package org.ucm.tp1.Control.Commands;

import org.ucm.tp1.Control.Exceptions.CommandExecuteException;
import org.ucm.tp1.Control.Exceptions.CommandParseException;
import org.ucm.tp1.Logic.Game;

public class SerializeCommand extends Command {
	SerializeCommand(){
		super("serialze", "z", "[s]erialize", "shows the game serialized ");
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		boolean refreshDisplay = false;
		System.out.print(game.serialize());		//print game info
		return refreshDisplay;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		return parseNoParamsCommand(commandWords);
	}

}
