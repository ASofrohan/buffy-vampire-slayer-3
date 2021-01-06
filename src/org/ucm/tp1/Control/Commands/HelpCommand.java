package org.ucm.tp1.Control.Commands;
import org.ucm.tp1.Control.Commands.CommandGenerator;
import org.ucm.tp1.Control.Exceptions.*;
import org.ucm.tp1.Logic.Game;

public class HelpCommand extends Command {
	
	public static final String helpMsg = String.format("\nAvailable commands:\n");
	
	public HelpCommand() {
		super("help", "h", "[h]elp", "show this help");
	}

	@Override
	public boolean execute(Game game) {
		System.out.print(helpMsg);
		System.out.print(CommandGenerator.commandHelp());		//print commands help
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException{
		return parseNoParamsCommand(commandWords);
	}

}
