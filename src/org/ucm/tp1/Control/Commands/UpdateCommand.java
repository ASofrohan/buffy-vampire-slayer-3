package org.ucm.tp1.Control.Commands;

import org.ucm.tp1.Logic.Game;

public class UpdateCommand extends Command {
	
	public UpdateCommand() {
		super("none", "n", "details", "help");
	}

	@Override
	public boolean execute(Game game) {
		game.update();
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		return parseNoParamsCommand(commandWords);
	}
	
	@Override
	public Command parseNoParamsCommand(String[] words) {
		if (matchCommandName(words[0]) || words[0].length() == 0) {
			if (words.length != 1) {
				System.err.println (incorrectArgsMsg);
				return null;
			}
			return this;
		}
		return null;
	}

}
