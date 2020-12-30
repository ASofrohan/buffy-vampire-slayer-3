package org.ucm.tp1.Control.Commands;
import org.ucm.tp1.Control.Exceptions.*;

public class CommandGenerator {
	private static final String UnknownMessage = "Unknown command.";
	private static Command[] availableCommands = {
		new AddCommand(),
		new HelpCommand(),
		new ResetCommand(),
		new ExitCommand(),
		new UpdateCommand(),
		new SuperCoinsCommand(),
		new AddVampireCommand(),
		new BankBloodCommand(),
		new GarlicPushCommand(),
		new LightFlashCommand(),
		new SerializeCommand(),
		new SaveCommand()
	};
	
	public static Command parseCommand(String[] commandWords) throws CommandParseException{
		Command ret = null;	
		for (int i = 0; i < availableCommands.length; i++) {
			ret = availableCommands[i].parse(commandWords);
			if (ret != null) break;
		}
		if (ret == null) throw new CommandParseException("[ERROR] " + UnknownMessage);
		return ret;
	}
	
	public static String commandHelp() {
		String helpTxt = "";
		for(int i = 0; i < availableCommands.length; i++) {
			helpTxt = helpTxt + availableCommands[i].helpText() + "\n";
		}
		return helpTxt;
	}
}
