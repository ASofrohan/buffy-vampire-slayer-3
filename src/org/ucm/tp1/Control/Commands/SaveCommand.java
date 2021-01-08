package org.ucm.tp1.Control.Commands;
import java.io.*;
import org.ucm.tp1.Control.Exceptions.CommandExecuteException;
import org.ucm.tp1.Control.Exceptions.CommandParseException;
import org.ucm.tp1.Logic.Game;

public class SaveCommand extends Command {
	
	String filename;
	
	public SaveCommand() {
		super("save", "s", "[s]ave <filename>", "save the game state in a file.dat");
	}
	
	public SaveCommand(String filename) {
		super("save", "s", "[s]ave <filename>", "save the game state in a file.dat");
		this.filename = filename;
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		boolean refreshDisplay = false;
		filename += ".dat";
		File fileOut = new File(filename);
		try{
		BufferedWriter outSource = new BufferedWriter(new FileWriter(fileOut));
		String data = "Buffy the Vampire Slayer v3.0\n" + game.serialize();		//add game info
		outSource.write(data);
		outSource.close();
		System.out.print("Game successfully saved in file " + filename + "\n");
		}catch(IOException e) {
			throw new CommandExecuteException("[ERROR]: Failed to create file " + filename + "\n[ERROR]: Failed to save game");
		}
		return refreshDisplay;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if(commandWords.length == 2 && matchCommandName(commandWords[0])) {			//return command with attributes
			return new SaveCommand(commandWords[1]);
		}
		else if (commandWords.length == 1 && matchCommandName(commandWords[0])) {
			throw new CommandParseException("[ERROR]: Command " + name + ": " + incorrectNumberOfArgsMsg);
		}
		return parseNoParamsCommand(commandWords);
	}

}
