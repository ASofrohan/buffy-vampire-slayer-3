package org.ucm.tp1.Control.Commands;

import java.io.*;
import org.ucm.tp1.Control.Exceptions.CommandExecuteException;
import org.ucm.tp1.Control.Exceptions.CommandParseException;
import org.ucm.tp1.Logic.Game;

public class ReloadCommand extends Command {
	
	String filename;
	
	public ReloadCommand() {
		super("reload", "r", "[r]eload <filename>", "reload the game state from a file.dat");
	}
	
	public ReloadCommand(String filename) {
		super("reload", "r", "[r]eload <filename>", "reload the game state from a file.dat");
		this.filename = filename;
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		boolean refreshDisplay = false;
		filename += ".dat";
		FileReader fr = null;
		BufferedReader inSource = null;
		try {
			fr = new FileReader(filename);
			inSource = new BufferedReader(fr);
			System.out.println(inSource.read());
		}catch(IOException e){
			throw new CommandExecuteException("[ERROR]: Failed to read from file " + filename + "\n[ERROR]: Failed to load game");
		}finally {
			if(inSource != null) {
				try{
					inSource.close();
				}catch (Exception ignore) {
					//nothing to do
				}
			}
		}
		return refreshDisplay;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if(commandWords.length == 2 && matchCommandName(commandWords[0])) {			//return command with attributes
			return new ReloadCommand(commandWords[1]);
		}
		else if (commandWords.length == 1 && matchCommandName(commandWords[0])) {
			throw new CommandParseException("[ERROR]: Command " + name + ": " + incorrectNumberOfArgsMsg);
		}
		return parseNoParamsCommand(commandWords);
	}

}