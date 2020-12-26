package org.ucm.tp1.Control.Commands;

import org.ucm.tp1.Logic.Game;
import org.ucm.tp1.Logic.GameObjects.GameObject;

public class AddVampireCommand extends Command{
	int posX;
	int posY;
	String vampireType;
	
	public AddVampireCommand() {
		super("vampire", "v", "details", "help");
	}
	
	public AddVampireCommand(int x, int y, String vampireType) {
		super("vampire", "v", "details", "help");
		this.posX = x;
		this.posY = y;
		this.vampireType = vampireType;
	}
	
	@Override
	public boolean execute(Game game) {
	    boolean validCommand = false;
	    if (posX <= 0 || posX > game.getLevel().getDim_x() || posY <= 0 || posY > game.getLevel().getDim_y()) {		//invalid position
	        System.out.print(incorrectArgsMsg + "\nInvalid position.\n");
	    }
	    if (game.getGameObjectBoard().getObjectList().getvRemaining() <= 0) {		//no vampires reamining
	        System.out.print("[ERROR] No more vampires remaining.\n");
	    }
	    else {
	    	switch (vampireType) {
	    	case "d":		//dracula
	    		if(!GameObject.isDraculaAlive()) {
	    			if (!game.getGameObjectBoard().addDraculaCommand(posY, posX, game)) {
	    				validCommand = false;
	    				System.out.println("[ERROR] Could not add Dracula in that position. The position is occupied or you don't have enough coins.");
	    			}
	    			else validCommand = true;
	    		}
		        else {
		        	System.out.println("[ERROR] Dracula is already alive");
		        	validCommand = false;
		        }
	    		break;
	    	case "e":		//explosive vampire
	    		if (!game.getGameObjectBoard().addExpVampireCommand(posY, posX, game)) {
		            validCommand = false;
		            System.out.println("[ERROR] Could not add Explosive Vampire in that position. The position is occupied or you don't have enough coins.");
		        }
		        else validCommand = true;
	    		break;
	    	case " ":		//vampire
	    		if (!game.getGameObjectBoard().addVampireCommand(posY, posX, game)) {
		            validCommand = false;
		            System.out.println("[ERROR] Could not add Vampire in that position. The position is occupied or you don't have enough coins.");
		        }
		        else validCommand = true;
	    		break;
	    	default:		//unknown vampire
	    		System.out.println("[ERROR] Unknown vampire type.");	    			    	
	    	}
	    }
	    return validCommand;
	}

	@Override
	public Command parse(String[] commandWords) {
		if(commandWords.length == 3 && matchCommandName(commandWords[0])) {
		    return new AddVampireCommand(Integer.parseInt(commandWords[1]), Integer.parseInt(commandWords[2]), " ");
		}
		if(commandWords.length == 4 && matchCommandName(commandWords[0])) {
		    return new AddVampireCommand(Integer.parseInt(commandWords[2]), Integer.parseInt(commandWords[3]), commandWords[1]);
		}
		return parseNoParamsCommand(commandWords);
	}
}
