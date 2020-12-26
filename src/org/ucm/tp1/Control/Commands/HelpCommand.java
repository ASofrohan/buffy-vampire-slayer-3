package org.ucm.tp1.Control.Commands;

import org.ucm.tp1.Logic.Game;

public class HelpCommand extends Command {
	
	public static final String helpMsg = String.format(
            "Available commands:%n" +
            "[a]dd <x> <y>: add a slayer in position x, y%n" +
            "[h]elp: show this help%n" + 
            "[r]eset: reset game%n" + 
            "[e]xit: exit game%n"+ 
            "[n]one | []: update%n");
	
	public HelpCommand() {
		super("help", "h", "details", "help");
	}

	@Override
	public boolean execute(Game game) {
		// TODO Auto-generated method stub
		System.out.print(helpMsg);
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		return parseNoParamsCommand(commandWords);
	}

}
