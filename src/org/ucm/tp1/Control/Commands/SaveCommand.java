package org.ucm.tp1.Control.Commands;

import org.ucm.tp1.Control.Exceptions.CommandExecuteException;
import org.ucm.tp1.Control.Exceptions.CommandParseException;
import org.ucm.tp1.Logic.Game;

public class SaveCommand extends Command {
	
	public SaveCommand() {
		super("save", "s", "[s]ave", "save the game state in a file.dat");
	}
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		boolean refreshDisplay = false;
		return refreshDisplay;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		return parseNoParamsCommand(commandWords);
	}

}
