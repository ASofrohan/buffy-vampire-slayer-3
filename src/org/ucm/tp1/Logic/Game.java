package org.ucm.tp1.Logic;
import org.ucm.tp1.Logic.GameObjects.IAttack;
import org.ucm.tp1.view.IPrintable;
import java.util.*;

public class Game implements IPrintable {
    private Long seed;
    private Level level;
    private int cycles;
    private GameObjectBoard gameObjectBoard;
    private boolean exitGame;
    private boolean win;
    private long seedBackup;		//reset
    private Level levelBackup;		//reset
    
    
    public Game(long seed, Level level) {
        this.seed = seed;
        this.level = level;
        this.cycles = 0;
        this.gameObjectBoard = new GameObjectBoard(level);
        this.exitGame = false;
        this.seedBackup = seed;
        this.levelBackup = level;
    }
    
    public void update() {
    	gameObjectBoard.getObjectList().attack();		//attack
    	gameObjectBoard.update(randomGenerator(seed) >= 0.5);		//give coins && move
    	gameObjectBoard.addVampire(randomGenerator(seed), level.getDim_y()-1, level.getDim_x()-1, level.getVampireFrequency(), this);		//add vampires
    	gameObjectBoard.addDracula(randomGenerator(seed), level.getDim_y()-1, level.getDim_x()-1, level.getVampireFrequency(), this);
    	gameObjectBoard.addExpVampire(randomGenerator(seed), level.getDim_y()-1, level.getDim_x()-1, level.getVampireFrequency(), this);
    	gameObjectBoard.removeDead();		//remove dead objects
    	isFinished(false);
    	cycles++;
    }
    
    public boolean isFinished(boolean print) {
    	this.exitGame = (gameObjectBoard.checkWin(print) || gameObjectBoard.checkLose(print));
    	if(print & !exitGame) System.out.println("[GAME OVER] Nobody wins...");
    	return this.exitGame;
    }
    
    public double randomGenerator(Long seed) {		//generator
    	Random generator = new Random(seed);
    	this.seed = (long)generator.nextInt();
    	generator.setSeed(seed);
    	return generator.nextDouble();
    }
    
    public String serialize() {
    	String info = "\nCycles: " + this.cycles + "\n";
    	info = info + "Coins: " + gameObjectBoard.getPlayer().getCoins() + "\n";
    	info = info + "Level: " + this.level + "\n";
    	info = info + "Remaining Vampires: " + gameObjectBoard.getObjectList().getvRemaining() + "\n";
    	info = info + "Vampires on Board: " + gameObjectBoard.getObjectList().getvAlive() + "\n";
    	
    	info = info + "\nGame Object List:\n";
    	for(int i = 0; i < gameObjectBoard.getObjectList().getGameObjects().size(); i++) {
    		info = info + gameObjectBoard.getObjectList().getGameObjects().get(i).serialize();
    	}
    	info = info + "\n";
    	return info;
    }
    
    public IAttack getAttackableInPosition(int row, int column) {
    	return gameObjectBoard.getObjectPosition(row, column);
    }
    
    public String getPositionToString(int x, int y) {
    	return gameObjectBoard.getObjectList().toStringSearch(x,y);
    }
    
    public String getInfo() {

    	String info = "\n\nNumber of cycles: " + this.cycles + "\n";
    	info = info + "Coins: " + gameObjectBoard.getPlayer().getCoins() + "\n";
    	info = info + "Remaining vampires: " + gameObjectBoard.getObjectList().getvRemaining() + "\n";
    	info = info + "Vampires on the board: " + gameObjectBoard.getObjectList().getvAlive() + "\n";
    	if(gameObjectBoard.getObjectList().isDraculaAlive()) {
    		info = info + "Dracula is alive\n";
    	}
    	return info;
    }
    
    public int getCycles()  {
        return cycles;
    }
    
    public void setCycles(int nCycles) {
        this.cycles = nCycles;
    }

	public long getSeedBackup() {
		return seedBackup;
	}

	public void setSeedBackup(long seedBackup) {
		this.seedBackup = seedBackup;
	}

	public Level getLevelBackup() {
		return levelBackup;
	}

	public void setLevelBackup(Level levelBackup) {
		this.levelBackup = levelBackup;
	}

	public Level getLevel() {
        return level;
    }
    
    public void setLevel(Level newLevel) {
        this.level = newLevel;
    }   
    
    public Long getSeed() {
		return seed;
	}

	public void setSeed(Long seed) {
		this.seed = seed;
	}

	public GameObjectBoard getGameObjectBoard() {
        return gameObjectBoard;
    }
    
    public void setGameObjectBoard (GameObjectBoard newGameObjectBoard) {
        this.gameObjectBoard = newGameObjectBoard;
    }
    
    public boolean getExitGame() {
    	return exitGame;
    }
    
    public void setExitGame(boolean exit) {
    	this.exitGame = exit;
    }
    
    public boolean getWin() {
    	return win;
    }
    
    public void setWin(boolean w) {
    	this.win = w;
    }

}