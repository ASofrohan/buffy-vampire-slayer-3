package org.ucm.tp1.Control.Commands;
import java.util.Scanner;
import java.io.*;
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
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.print("File name where you want to save the game:\n");
		String fileName = scanner.nextLine();		//enter file name
		fileName += ".dat";
		File fileOut = new File(fileName);
		
		try{
		BufferedWriter outSource = new BufferedWriter(new FileWriter(fileOut));
		String data = "Buffy the Vampire Slayer v3.0\n" + game.serialize();		//add game info
		outSource.write(data);
		outSource.close();
		System.out.print("Game successfully saved in file " + fileName + "\n");
		}catch(IOException e) {
			throw new CommandExecuteException("[ERROR]: Failed to create file " + fileName + "\n[ERROR]: Failed to save game");
		}
		return refreshDisplay;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		return parseNoParamsCommand(commandWords);
	}

}
