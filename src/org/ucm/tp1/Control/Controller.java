package org.ucm.tp1.Control;
import org.ucm.tp1.Control.Exceptions.*;
import java.util.Scanner;
import org.ucm.tp1.Logic.*;
import org.ucm.tp1.Control.Commands.*;
import org.ucm.tp1.view.Gameprinter;


public class Controller {
    
    public final String prompt = "Command > ";
    public static final String helpMsg = String.format(
            "Available commands:%n" +
            "[a]dd <x> <y>: add a slayer in position x, y%n" +
            "[h]elp: show this help%n" + 
            "[r]eset: reset game%n" + 
            "[e]xit: exit game%n"+ 
            "[n]one | []: update%n");
    
    public static final String unknownCommandMsg = String.format("Unknown command.");
    public static final String invalidCommandMsg = String.format("Invalid command.");
    public static final String invalidPositionMsg = String.format("Invalid position.");
    public static final String confirmationMsg = String.format("Are you sure? (y/n)");
    public static final String tooManyArgsMsg = String.format("You introduced more arguments than necessary.");

    private Game game;
    private Scanner scanner;
    private Gameprinter gameprinter;
    private boolean refreshDisplay;
    
    public Controller(Game game, Scanner scanner) {
        this.game = game;;
        this.scanner = scanner;
        this.refreshDisplay = true;
    }
    
    public void  printGame() {		//info messages & board
        this.gameprinter = new Gameprinter(game, game.getLevel().getDim_y(), game.getLevel().getDim_x());
        System.out.println(gameprinter.toString(this.game));
    }
    
    public void run() {
    	while (!game.isFinished()){					//exit game
    		if (refreshDisplay) printGame();		//refresh display
    		refreshDisplay = false;
    		System.out.println(prompt);
    		String s = scanner.nextLine();
    		String[] parameters = s.toLowerCase().trim().split(" ");
    		System.out.println("[DEBUG] Executing: " + s);
    		try {
    			Command command = CommandGenerator.parseCommand(parameters);
    		} catch(CommandParseException) {
    			System.out.println("[ERROR]: " + unknownCommandMsg);
    		}
    		if (command != null) {
    			refreshDisplay = command.execute(game);		//execute command
    		}
    		else {
    			System.out.println("[ERROR]: " + unknownCommandMsg);
    		}
    	}
    	System.out.println("Game over.");
    	
    }    
}