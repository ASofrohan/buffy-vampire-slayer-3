package org.ucm.tp1.Logic.GameObjects;
import org.ucm.tp1.Logic.Game;

public class Dracula extends Vampire{
	public Dracula(int row, int column, Game game) {
		super(row, column, game);
		setDraculaAlive(true);
		System.out.print("DRACULA IS ALIVE!\n");
	}
	
	@Override
	public void attack() {
		IAttack other = this.getGame().getAttackableInPosition(this.getRow(), this.getColumn()-1);
		if(other != null) other.receiveDraculaAttack();
	}
	
	@Override
	public boolean receiveSlayerAttack(int damage) {
		if(this.isAlive()) this.setHealth(this.getHealth()-damage);
		if(this.getHealth() <= 0) {
			setvAliveStatic(getvAliveStatic()-1);
			this.setAlive(false);
			setDraculaAlive(false);
		}
		return true;
	}
	
	@Override
    public boolean receiveGarlicPush() {
		setMove(false);
		setStunned(2);
    	IAttack other = this.getGame().getAttackableInPosition(this.getRow(), this.getColumn()+1);		//get possible static object
		if(other == null || (other != null && !other.staticObject())) {
			this.setColumn(this.getColumn()+1);
    		if(this.getColumn() >= this.getGame().getLevel().getDim_x()) {
    			this.setAlive(false);
				setvAliveStatic(getvAliveStatic()-1);
				setDraculaAlive(false);
    		}
		}
    	return true;
    } 
	
	public String toString() {
    	return "D[" + super.getHealth() + "]";
    }
	
	@Override
	public boolean receiveLightFlash() {
		return false;
	}
}
