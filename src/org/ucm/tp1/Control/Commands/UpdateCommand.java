package org.ucm.tp1.Control.Commands;

import org.ucm.tp1.Control.Exceptions.CommandParseException;
import org.ucm.tp1.Logic.Game;

public class UpdateCommand extends Command {
	
	public UpdateCommand() {
		super("none", "n", "[n]one | []", "update");
	}

	@Override
	public boolean execute(Game game) {
		game.update();
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		return parseNoParamsCommand(commandWords);
	}
	
	@Override
	public Command parseNoParamsCommand(String[] words) throws CommandParseException {
		if (matchCommandName(words[0]) || words[0].length() == 0) {
			if (words.length != 1) {
				throw new CommandParseException("[ERROR]: Command " + name + ": " + incorrectNumberOfArgsMsg);
			}
			return this;
		}
		return null;
	}

}
