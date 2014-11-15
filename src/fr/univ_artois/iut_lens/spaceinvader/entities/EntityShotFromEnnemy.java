package fr.univ_artois.iut_lens.spaceinvader.entities;

import fr.univ_artois.iut_lens.spaceinvader.EntitiesManager;
import fr.univ_artois.iut_lens.spaceinvader.Game;

/**
 * An entity representing a ennemy shot
 * @author Maxime
 *
 */
public class EntityShotFromEnnemy extends EntityShot {
	/** The vertical speed at which the players shot moves */
	private int moveSpeed = 300;
	
	/**
	 * Create a new shot from the player
	 * 
	 * @param game The game in which the shot has been created
	 * @param sprite The sprite representing this shot
	 * @param x The initial x location of the shot
	 * @param y The initial y location of the shot
	 */
	public EntityShotFromEnnemy(Game game,String sprite,int x,int y, EntitiesManager eM) {
		super(sprite,x,y,1,eM);
		
		speed.setY(moveSpeed);
	}
	
	
	@Override
	public void collidedWith(Entity other) {
		// prevents double kills, if we've already hit something,
		// don't collide
		if (used) {
			return;
		}
		
		// if we've hit an alien, kill it!
		if (other instanceof EntityShip) {
			// remove the affected entities
			entitiesManager.removeEntity(this);
			// other prends les dégats donnés par le tir
			if (other.receiveDegat(this))
				// notify the game that the alien has been killed
				Game.gameInstance.notifyAlienKilled();
			
			used = true;
		}
	}
}