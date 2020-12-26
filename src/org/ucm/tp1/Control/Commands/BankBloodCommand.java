package org.ucm.tp1.Control.Commands;

import org.ucm.tp1.Logic.Game;

public class BankBloodCommand extends Command{
	int posX;
	int posY;
	int cost;
	
	public BankBloodCommand() {
		super("bank", "b", "details", "help");
	}
	
	public BankBloodCommand(int x, int y, int cost) {
		super("bank", "b", "details", "help");
		this.posX = x;
		this.posY = y;
		this.cost = cost;
	}
	
	@Override
	public boolean execute(Game game) {
		boolean validCommand = false;
	    if (posX <= 0 || posX >= game.getLevel().getDim_x() || posY <= 0 || posY > game.getLevel().getDim_y()) {		//invalid position
	        System.out.print(incorrectArgsMsg + "\nInvalid position.\n");
	    }
	    else {
	        if (!game.getGameObjectBoard().addBankBlood(posY, posX, cost, game)) {		//bankblood not added
	            validCommand = false;
	            System.out.println("[ERROR] Could not add bank blood in that position. The position is occupied or you don't have enough coins.");
	        }
	        else {
	        	validCommand = true;		//update game
	    	    game.update();
	        }
	    }
	    return validCommand;
	}
	
	@Override
	public Command parse(String[] commandWords) {
		if(commandWords.length == 4 && matchCommandName(commandWords[0])) {		//return command with attributes
		    return new BankBloodCommand(Integer.parseInt(commandWords[1]), Integer.parseInt(commandWords[2]), Integer.parseInt(commandWords[3]));
		}
		return parseNoParamsCommand(commandWords);
	}
}
