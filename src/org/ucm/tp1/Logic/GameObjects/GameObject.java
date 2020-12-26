package org.ucm.tp1.Logic.GameObjects;
import org.ucm.tp1.Logic.Game;

public abstract class GameObject implements IAttack{
	
	private int column;
	private int row;
	private Game game;
	protected boolean isAlive;
	private static int totalRefound;
	private static boolean draculaAlive;
	private static int vAliveStatic;
	public abstract boolean move();
	public abstract String toString();
	//public abstract boolean receiveGarlicPush();
	
	public void deploy(int row, int column, Game game) {
		this.row = row;
		this.column = column;
		this.game = game;
		this.isAlive = true;
	};
	
    static {
    	totalRefound = 0;
    	vAliveStatic = 0;
    	draculaAlive = false;
    }

    public boolean checkPos(int row, int column) {
    	boolean match = false;
    	if(row == this.row && column == this.column) {
    		match = true;
    	}
    	return match;
    }
	
	public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public int getColumn() {
        return column;
    }
    public void setColumn(int column) {
        this.column = column;
    }
	public void moveForward() {
		this.column--;
	}
	public static int getvAliveStatic() {
		return vAliveStatic;
	}
	public static void setvAliveStatic(int vAlive) {
		GameObject.vAliveStatic = vAlive;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public static boolean isDraculaAlive() {
		return draculaAlive;
	}
	public static void setDraculaAlive(boolean draculaAlive) {
		GameObject.draculaAlive = draculaAlive;
	}
	public static int getTotalRefound() {
		return totalRefound;
	}
	public static void setTotalRefound(int totalRefound) {
		GameObject.totalRefound = totalRefound;
	}
	public boolean isAlive() {
    	System.out.print("ffkhf\n");
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
};
