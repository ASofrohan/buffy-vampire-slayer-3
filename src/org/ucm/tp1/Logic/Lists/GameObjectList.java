package org.ucm.tp1.Logic.Lists;
import org.ucm.tp1.Logic.GameObjects.*;
import java.util.*;
import org.ucm.tp1.Logic.Level;
import org.ucm.tp1.Logic.Game;

public class GameObjectList {
	private ArrayList<GameObject>gameObjects;
	private int vRemaining;	//restar cada vez que aparezca uno
	private int vAlive;		//v on board
	private int vCounter;	//vampiros que han aparecido
	private int sCounter;	//siguiente slayer
	private boolean draculaAlive;
	
	public GameObjectList(Level l) {
		this.gameObjects = new ArrayList<GameObject>();
		this.vRemaining = l.getNumberOfVampires();
		this.vCounter = 0;
		this.vAlive = 0;
		this.sCounter = 0;
		this.draculaAlive = false;
	}

	public boolean addSlayer(int row, int column, Game game){
		boolean added = false;
		if(freePos(row, column)) {
			gameObjects.add(new Slayer(row, column, game));
			added = true;
			sCounter++;
		}
		return added;
	}
	
	public boolean addVampireCommand(int row, int column, Game game){
		boolean added = false;
		if(freePos(row, column)) {
			gameObjects.add(new Vampire(row, column, game));
			added = true;
			this.vRemaining--;
			this.vCounter++;
			this.vAlive = GameObject.getvAliveStatic();
		}
		return added;
	}
	
	public boolean addDraculaCommand(int row, int column, Game game){
		boolean added = false;
		if(freePos(row, column)) {
			gameObjects.add(new Dracula(row, column, game));
			added = true;
			this.vRemaining--;
			this.vCounter++;
			this.vAlive = GameObject.getvAliveStatic();
			this.draculaAlive = GameObject.isDraculaAlive();
		}
		return added;
	}
	
	public boolean addExpVampireCommand(int row, int column, Game game){
		boolean added = false;
		if(freePos(row, column)) {
			gameObjects.add(new ExplosiveVampire(row, column, game));
			added = true;
			this.vRemaining--;
			this.vCounter++;
			this.vAlive = GameObject.getvAliveStatic();
		}
		return added;
	}
	
	public void addVampire(double rand, int nRows, int nColumns, double frequency, Game game){
		//calcular si añadirlo o no
		//calcular en que fila iria
		//añadirlo		
		if(rand <= frequency && this.vRemaining > 0) {
			int row = (int)(Math.round(rand*100) % nRows);
			if(freePos(row, nColumns)) {
				gameObjects.add(new Vampire(row, nColumns, game));
				this.vRemaining--;
				this.vCounter++;
				this.vAlive = GameObject.getvAliveStatic();
			}
		}
	}
	
	public void addDracula(double rand, int nRows, int nColumns, double frequency, Game game) {
		if(rand <= frequency && this.vRemaining > 0 && !GameObject.isDraculaAlive()) {
			int row = (int)(Math.round(rand*100) % nRows);
			if(freePos(row, nColumns)) {
				gameObjects.add(new Dracula(row, nColumns, game));
				this.vRemaining--;
				this.vCounter++;
				this.vAlive = GameObject.getvAliveStatic();
				this.draculaAlive = GameObject.isDraculaAlive();
			}
		}
	}
	
	public void addExpVampire(double rand, int nRows, int nColumns, double frequency, Game game) {
		if(rand <= frequency && this.vRemaining > 0) {
			int row = (int)(Math.round(rand*100) % nRows);
			if(freePos(row, nColumns)) {
				gameObjects.add(new ExplosiveVampire(row, nColumns, game));
				this.vRemaining--;
				this.vCounter++;
				this.vAlive = GameObject.getvAliveStatic();
			}
		}
	}
	
	public boolean addBankBlood(int row, int column, int cost, Game game) {
		boolean added = false;
		if(freePos(row, column)) {
			gameObjects.add(new BankBlood(row, column, cost, game));
			added = true;			
		}
		return added;
	}
	
	public void removeDead() {
		int i = 0;
		while(i < gameObjects.size()) {
			if(!gameObjects.get(i).isAlive()) {
				gameObjects.remove(i);
				i--;
				this.vAlive = GameObject.getvAliveStatic();
			}
			i++;
		}
	}
	
	public void attack() {
		for(int i = 0; i < gameObjects.size(); i++) {
			gameObjects.get(i).attack();
		}
	}
	
	public void move() {
		for(int i = 0; i < gameObjects.size(); i++ ) {
			if(freePos(gameObjects.get(i).getRow(), gameObjects.get(i).getColumn()-1)) {
				gameObjects.get(i).move();
			}
		}
	}
	
	public boolean freePos(int row, int column) {
		boolean freePos = true;
		for(int i = 0; i < gameObjects.size(); i++ ) {
			if(gameObjects.get(i).checkPos(row, column)) freePos = false;
		}		
		return freePos;
	}
	
	public String toStringSearch(int row, int column) {
		for(int i = 0; i < gameObjects.size(); i++) {
			if(gameObjects.get(i).checkPos(row, column)) {
				return gameObjects.get(i).toString();
			};
		}
		return " ";
	}
	
	public ArrayList<GameObject> getGameObjects() {
		return gameObjects;
	}
	public void setGameObjects(ArrayList<GameObject> gameObjects) {
		this.gameObjects = gameObjects;
	}
	public int getvRemaining() {
		return vRemaining;
	}
	public void setvRemaining(int vRemaining) {
		this.vRemaining = vRemaining;
	}
	public int getvAlive() {
		return vAlive;
	}
	public void setvAlive(int vAlive) {
		this.vAlive = vAlive;
	}
	public int getvCounter() {
		return vCounter;
	}
	public void setvCounter(int vCounter) {
		this.vCounter = vCounter;
	}
	public int getsCounter() {
		return sCounter;
	}
	public void setsCounter(int sCounter) {
		this.sCounter = sCounter;
	}

	public boolean isDraculaAlive() {
		return draculaAlive;
	}

	public void setDraculaAlive(boolean draculaAlive) {
		this.draculaAlive = draculaAlive;
	}
		
}
