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
		new LightFlashCommand()
	};
	
	public static Command parseCommand(String[] commandWords) throws CommandParseException{
		Command ret = null;	
		for (int i = 0; i < availableCommands.length; i++) {
			ret = availableCommands[i].parse(commandWords);
			if (ret != null) break;
		}
		if (ret == null) throw new CommandParseException("[ERROR]" + UnknownMessage);
		return ret;
	}
	
	public static String commandHelp() {
		return availableCommands[0].helpText() + "%n" +
				availableCommands[1].helpText() + "%n" +
				availableCommands[2].helpText() + "%n" +
				availableCommands[3].helpText() + "%n" +
				availableCommands[4].helpText() + "%n" +
				availableCommands[5].helpText() + "%n" +
				availableCommands[6].helpText() + "%n" +
				availableCommands[7].helpText() + "%n" +
				availableCommands[8].helpText() + "%n" +
				availableCommands[9].helpText() + "%n";
	}

}
