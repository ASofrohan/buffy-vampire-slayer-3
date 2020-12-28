package org.ucm.tp1.Control.Commands;
import org.ucm.tp1.Control.Exceptions.*;
import org.ucm.tp1.Logic.Game;

public class HelpCommand extends Command {
	
	public static final String helpMsg = String.format(
            "Available commands:%n" +
            "[a]dd <x> <y>: add a slayer in position x, y%n" +
            "[v]ampire <x> <y>: add a vampire in position x, y%n" +
            "[v]ampire <e> <x> <y>: add a explosive vampire in position x, y%n" +
            "[v]ampire <d> <x> <y>: add a dracula in position x, y%n" +
            "[b]ank <x> <y> <z>: add a blood bank in position x, y with z cost%n" +
            "[g]arlic: push 1 position back every vampire%n" +
            "[l]ight: deal 1 damage to every vampire unless dracula%n" +
            "[c]oins: give 1000 coins to the player%n" +
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
	public Command parse(String[] commandWords) throws CommandParseException{
		return parseNoParamsCommand(commandWords);
	}

}
