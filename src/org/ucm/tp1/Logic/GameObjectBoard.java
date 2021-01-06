package org.ucm.tp1.Logic;
import org.ucm.tp1.Logic.Lists.GameObjectList;
import org.ucm.tp1.Logic.GameObjects.Player;
import org.ucm.tp1.Logic.GameObjects.IAttack;
import org.ucm.tp1.Control.Exceptions.*;
import org.ucm.tp1.Logic.GameObjects.GameObject;

public class GameObjectBoard {
	
	private Player player;
	private GameObjectList objectList;
	
	public GameObjectBoard(Level l) {
		this.player = new Player();
		this.objectList = new GameObjectList(l);
	}
	
	public boolean checkWin(boolean print) {
		boolean win = false;	
		if(objectList.getvRemaining() == 0 && objectList.getvAlive() == 0) {
			win = true;	//no v left on the board and remaining
			if(print) System.out.println("[GAME OVER]: Player wins!");
		}
		return win;
	}
	
	public boolean checkLose(boolean print) {		//vampire column -1
		boolean lose = false;	
		for(int i = 0; i < objectList.getGameObjects().size(); i++) {
			if(objectList.getGameObjects().get(i).getColumn() == (-1)) {
				lose = true;
				if(print) System.out.println("[GAME OVER]: Vampires win!");
				break;
			}
		}
		return lose;
	}
	
	public void update(boolean addCoins){
		if(addCoins) this.player.setCoins(this.player.getCoins()+10);		//add coins
		objectList.move();		//move all
		this.player.setCoins(this.player.getCoins() + GameObject.getTotalRefound());		//blood bank refound
	}
	
	public void addSlayer(int row, int column, Game game) throws CommandExecuteException{
		if(this.player.getCoins() < 50) throw new NotEnoughCoinsException("[ERROR]: Slayer cost is 50: Not enough coins");
		objectList.addSlayer(row, column, game);
		this.getPlayer().setCoins(this.getPlayer().getCoins()-50);		//update coins
	}
	
	public void addBankBlood(int row, int column, int cost, Game game) throws CommandExecuteException{
		if(this.player.getCoins() < cost)  throw new NotEnoughCoinsException("[ERROR]: Bank cost is " + cost + ": Not enough coins");
		objectList.addBankBlood(row, column, cost, game);
		this.getPlayer().setCoins(this.getPlayer().getCoins()-cost);		//update coins
	}
	
	public void addVampireCommand(int row, int column, Game game) throws CommandExecuteException{
		objectList.addVampireCommand(row, column, game);
	}
	
	public void addDraculaCommand(int row, int column, Game game) throws CommandExecuteException{
		objectList.addDraculaCommand(row, column, game);
	}
	
	public void addExpVampireCommand(int row, int column, Game game) throws CommandExecuteException{
		objectList.addExpVampireCommand(row, column, game);
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
