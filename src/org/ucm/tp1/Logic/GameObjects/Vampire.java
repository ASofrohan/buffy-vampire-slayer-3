package org.ucm.tp1.Logic.GameObjects;
import org.ucm.tp1.Logic.Game;


public class Vampire extends GameObject{
	private int health;
	private int fireRate;
	private int damage;
	private boolean move;		//indica si le toca moverse ese turno o no
	private int stunned;
	
	public Vampire(int row, int column, Game game){
		this.health = 3;
        this.fireRate = 1;
        this.damage = 1;
        this.move = false;		//it changes each turn      
        this.stunned = 0;
        setvAliveStatic(getvAliveStatic()+1);
        deploy(row, column, game);
	}
	
	@Override
	public void attack() {
		IAttack other = this.getGame().getAttackableInPosition(this.getRow(), this.getColumn()-1);
		if(other != null) other.receiveVampireAttack(this.damage);
	}
	
	public boolean receiveSlayerAttack(int damage) {
		if(this.isAlive()) this.health = this.health-damage;
		if(this.health <= 0) {
			setvAliveStatic(getvAliveStatic()-1);
			this.setAlive(false);
		}
		return true;
	}
	
	public boolean move() {
		if(this.move && isAlive() && this.stunned == 0) {
			this.setColumn(getColumn()-1);
		}
		if(this.stunned != 0) this.stunned--;
		if (this.stunned == 0) this.move = !this.move;
		return !this.move;
    }
	
    public boolean receiveGarlicPush() {   
   		this.move = false;
   		this.stunned = 2;
    	IAttack other = this.getGame().getAttackableInPosition(this.getRow(), this.getColumn()+1);
		if(other == null || (other != null && !other.staticObject())) {
			this.setColumn(this.getColumn()+1);
	    	if(this.getColumn() >= this.getGame().getLevel().getDim_x()) {
	    		this.setAlive(false);
	    		setvAliveStatic(getvAliveStatic()-1);
	    	}
		}
    	return true;
    } 
    
    public String toString() {
    	return "V[" + this.health + "]";
    }
    
	public boolean receiveLightFlash() {
		this.health = 0;
		setvAliveStatic(getvAliveStatic()-1);
		this.setAlive(false);
		return true;
	}
	public boolean receiveHealBankBlood() {
		if(this.health < 3) this.health++;
		return true;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getFireRate() {
		return fireRate;
	}
	public void setFireRate(int fireRate) {
		this.fireRate = fireRate;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public boolean getMove() {
		return move;
	}
	public void setMove(boolean move) {
		this.move = move;
	}
	public boolean isAlive() {
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	public int getStunned() {
		return stunned;
	}
	public void setStunned(int stunned) {
		this.stunned = stunned;
	}	
}
