package org.ucm.tp1.Logic.GameObjects;

import org.ucm.tp1.Logic.Game;

public class ExplosiveVampire extends Vampire{
	
	private int expDammage;
	
	public ExplosiveVampire(int row, int column, Game game) {
		super(row, column, game);
		this.expDammage = 1;
	}
	
	@Override
	public boolean receiveSlayerAttack(int damage) {
		if(this.isAlive()) this.setHealth(this.getHealth()-damage);
		if(this.getHealth() <= 0) {
			explosionAttack();							//explosion
			setvAliveStatic(getvAliveStatic()-1);
			this.setAlive(false);
		}
		return true;
	}
	
	public void explosionAttack() {	
		int intRow = super.getRow()-1;				//starting grid
		int intColumn = super.getColumn()-1;		//starting grid
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(this.getRow() != (intRow + i) || this.getColumn() != (intColumn +j)) {		//same coordinates
					IAttack other = this.getGame().getAttackableInPosition(intRow + i, intColumn +j);
					if(other != null) other.receiveSlayerAttack(this.expDammage);
				}
			}
		}		
	}
	
	public String toString() {
    	return "EV[" + super.getHealth() + "]";
    }

	public int getExpDammage() {
		return expDammage;
	}
	public void setExpDammage(int expDammage) {
		this.expDammage = expDammage;
	}	
}