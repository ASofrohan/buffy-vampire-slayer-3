package org.ucm.tp1.Control;
import org.ucm.tp1.Control.Exceptions.*;
import java.util.Scanner;
import org.ucm.tp1.Logic.*;
import org.ucm.tp1.Control.Commands.*;
import org.ucm.tp1.view.Gameprinter;


public class Controller {
    
    public final String prompt = "Command > ";

    private Game game;
    private Scanner scanner;
    private Gameprinter gameprinter;
    private boolean refreshDisplay;
    
    public Controller(Game game, Scanner scanner) {
        this.game = game;
        this.scanner = scanner;
        this.refreshDisplay = true;
    }
    
    public void  printGame() {		//info messages & board
        this.gameprinter = new Gameprinter(game, game.getLevel().getDim_y(), game.getLevel().getDim_x());
        System.out.println(gameprinter.toString(this.game));
    }
    
    public void run() {
    	while (!game.getExitGame()){					//exit game
    		if (refreshDisplay) printGame();		//refresh display
    		refreshDisplay = false;
    		System.out.println(prompt);
    		String s = scanner.nextLine();		//enter command
    		String[] parameters = s.toLowerCase().trim().split(" ");
    		System.out.println("[DEBUG]: Executing: " + s);
    		try {
    			Command command = CommandGenerator.parseCommand(parameters);
    			if (command != null) {
        			refreshDisplay = command.execute(game);		//execute command
    			}
    		} catch(GameException ex) {			//print exception
    			System.out.println(ex.getMessage() + "\n");
    		}
    	}  
    	printGame();
    	game.isFinished(true);
    }    
}