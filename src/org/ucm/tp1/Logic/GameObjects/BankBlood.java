package org.ucm.tp1.Logic.GameObjects;

import org.ucm.tp1.Logic.Game;

public class BankBlood extends GameObject {
	private int cost; 
    private int refound;
	
	public BankBlood(int row, int column, int cost, Game game) {
        this.cost = cost;
        this.refound = (int) (cost*0.10);
        setTotalRefound(getTotalRefound() + refound);
        deploy(row, column, game);
    }
	
	public boolean receiveVampireAttack(int damage) {		//delete bankblood
		if(this.isAlive()) {
			this.setAlive(false);
			setTotalRefound(getTotalRefound() - refound);
		}
		return true;
	}
	
	public boolean receiveDraculaAttack() {		//delete bankblood
		if(this.isAlive()) {
			this.setAlive(false);
			setTotalRefound(getTotalRefound() - refound);
		}
		return true;
	}
	
	public void attack() {		//heal +1
		IAttack other = this.getGame().getAttackableInPosition(this.getRow(), this.getColumn()+1);
		if(other != null) other.receiveHealBankBlood();
	}
	
	public boolean receiveLightFlash() {
		return false;
	}
    public boolean staticObject() {
    	return true;
    }
    public boolean push() {
    	return false;
    }
    public boolean move() {
    	return false;
    }  
    public String toString() {
    	return "B[" + this.cost + "]";
    }
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getRefound() {
		return refound;
	}
	public void setRefound(int refound) {
		this.refound = refound;
	}  
}
