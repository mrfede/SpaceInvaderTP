package fr.univ_artois.iut_lens.spaceinvader.entities.shot;

import fr.univ_artois.iut_lens.spaceinvader.EntitiesManager;
import fr.univ_artois.iut_lens.spaceinvader.entities.Entity;
import fr.univ_artois.iut_lens.spaceinvader.util.Position;

/**
 * An entity representing a shot fired by the player's ship
 * 
 * @author Kevin Glass
 */
public abstract class EntityShot extends Entity {
	/** True if this shot has been "used", i.e. its hit something */
	protected boolean used = false;
	/** Points de dégat */
	protected int degat = 0;
	
	/**
	 * Create a new shot from the player
	 * 
	 * @param game The game in which the shot has been created
	 * @param sprite The sprite representing this shot
	 * @param x The initial x location of the shot
	 * @param y The initial y location of the shot
	 */
	public EntityShot(String sprite,double x,double y, int d, Position s, EntitiesManager eM) {
		super(sprite,x,y,eM);
		
		degat = d;
		
		speed = s;
	}

	/**
	 * Request that this shot moved based on time elapsed
	 * 
	 * @param delta The time that has elapsed since last move
	 */
	public void move(long delta) {
		// proceed with normal move
		super.move(delta);
		
		// if we shot off the screen, remove ourselfs
		if (position.getY() < -100) {
			entitiesManager.removeEntity(this);
		}
	}
	
	/**
	 * Notification that this shot has collided with another
	 * entity
	 * 
	 * @parma other The other entity with which we've collided
	 */
	public void collidedWith(Entity other) {
		
	}
	
	
	public int getDegat() { return degat; }
}