package org.ucm.tp1.Logic;
import org.ucm.tp1.Logic.Lists.GameObjectList;
import org.ucm.tp1.Logic.GameObjects.Player;
import org.ucm.tp1.Logic.GameObjects.IAttack;
import org.ucm.tp1.Logic.GameObjects.GameObject;

public class GameObjectBoard {
	
	private Player player;
	private GameObjectList objectList;
	
	public GameObjectBoard(Level l) {
		this.player = new Player();
		this.objectList = new GameObjectList(l);
	}
	
	public boolean checkWin() {
		boolean win = false;	
		if(objectList.getvRemaining() == 0 && objectList.getvAlive() == 0) {
			win = true;	//no v left on the board and remaining
			System.out.println("You won the game!!!");
		}
		return win;
	}
	
	public boolean checkLose() {		//vampire column -1
		boolean lose = false;	
		for(int i = 0; i < objectList.getGameObjects().size(); i++) {
			if(objectList.getGameObjects().get(i).getColumn() == (-1)) {
				lose = true;
				System.out.println("You lost the game!!!");
				break;
			}
		}
		return lose;
	}
	
	public void update(boolean addCoins){
		if(addCoins) this.player.setCoins(this.player.getCoins()+10);		//add coins
		objectList.move();		//move all
		this.player.setCoins(this.player.getCoins() + GameObject.getTotalRefound());
	}
	
	public boolean addSlayer(int row, int column, Game game){
		row--;
		column--;
		boolean added = false;
		if(this.player.getCoins() >= 50) {
			added = objectList.addSlayer(row, column, game);
			if(added) this.getPlayer().setCoins(this.getPlayer().getCoins()-50);		//update coins
		}
		return added;
	}
	
	public boolean addBankBlood(int row, int column, int cost, Game game){
		row--;
		column--;
		boolean added = false;
		if(this.player.getCoins() >= cost) {
			added = objectList.addBankBlood(row, column, cost, game);
			if(added) this.getPlayer().setCoins(this.getPlayer().getCoins()-cost);		//update coins
		}
		return added;
	}
	
	public boolean addVampireCommand(int row, int column, Game game){
		row--;
		column--;
		boolean added = false;
		added = objectList.addVampireCommand(row, column, game);
		return added;
	}
	
	public boolean addDraculaCommand(int row, int column, Game game){
		row--;
		column--;
		boolean added = false;
		added = objectList.addDraculaCommand(row, column, game);
		return added;
	}
	
	public boolean addExpVampireCommand(int row, int column, Game game){
		row--;
		column--;
		boolean added = false;
		added = objectList.addExpVampireCommand(row, column, game);
		return added;
	}
	
	public void addVampire(double rand, int nRows, int nColumns, double frequency, Game game){
		objectList.addVampire(rand, nRows, nColumns, frequency, game);
	}
	
	public void addDracula(double rand, int nRows, int nColumns, double frequency, Game game){
		objectList.addDracula(rand, nRows, nColumns, frequency, game);
	}
	
	public void addExpVampire(double rand, int nRows, int nColumns, double frequency, Game game){
		objectList.addExpVampire(rand, nRows, nColumns, frequency, game);
	}
	
	public void removeDead(){
		objectList.removeDead();
	}
	
	public IAttack getObjectPosition(int row, int column) {
		boolean found = false;
		int counter = 0;
		while(!found && counter < objectList.getGameObjects().size()) {
			if(objectList.getGameObjects().get(counter).checkPos(row, column)) {
				return objectList.getGameObjects().get(counter);
			};
			counter++;
		}
		return null;	
	}
	
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public GameObjectList getObjectList() {
		return objectList;
	}
	public void setObjectList(GameObjectList objectList) {
		this.objectList = objectList;
	}
}
