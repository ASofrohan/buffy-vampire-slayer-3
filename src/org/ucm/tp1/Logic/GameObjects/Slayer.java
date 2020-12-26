package org.ucm.tp1.Logic.GameObjects;
import org.ucm.tp1.Logic.Game;

public class Slayer extends GameObject{
    private static int cost;
    private int health; 
    private int fireRate;
    private int damage;

    public Slayer(int row, int column, Game game) {
        deploy(row, column, game);
    	this.health = 3;
        this.fireRate = 1;
        this.damage = 1;
    }
    static {
    	cost = 50;
    }
    
    @Override
	public void attack() {
		for(int i=this.getColumn(); i < this.getGame().getLevel().getDim_x(); i++) {
			IAttack other = this.getGame().getAttackableInPosition(this.getRow(), i);
			if(other != null) other.receiveSlayerAttack(this.damage);
		}
	}
    
	public boolean receiveVampireAttack(int damage) {
		if(this.isAlive()) this.health = this.health-damage;
		if(this.health <= 0) this.setAlive(false);
		return true;
	}
	
	public boolean receiveDraculaAttack() {
		if(this.isAlive()) this.health = 0;
		if(this.health <= 0) this.setAlive(false);
		return true;
	}
    /* TODO When new types of damage are added, such as area or diagonal,
     * create an enum with the different types. */
	public boolean receiveLightFlash() {
		return false;
	}
    public boolean move() {
    	return false;
    }
    public boolean receiveGarlicPush() {
    	return false;
    }  
    public boolean staticObject() {
    	return true;
    }
    public String toString() {
    	return "S[" + this.health + "]";
    }
	public static int getCost() {
        return cost;
    }
    public static void setCost(int newCost) {
        cost = newCost;
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
    public static final String usageMsg = "Usage: [a]dd <x> <y>";

}