package fr.univ_artois.iut_lens.spaceinvader.server.entities.shot;

import fr.univ_artois.iut_lens.spaceinvader.server.EntitiesManager;
import fr.univ_artois.iut_lens.spaceinvader.server.Server;
import fr.univ_artois.iut_lens.spaceinvader.server.entities.Entity;
import fr.univ_artois.iut_lens.spaceinvader.server.entities.ennemy.EntityEnnemy;
import fr.univ_artois.iut_lens.spaceinvader.server.entities.ship.EntityShip;
import fr.univ_artois.iut_lens.spaceinvader.util.Vector2d;

public class EntityShotFromAllyComplex extends EntityShotFromAlly {

	double i = 0;
	double tourParSeconde = 4;
	public EntityShotFromAllyComplex(Vector2d p, EntitiesManager eM, EntityShip ship) {
		super("sprites/ComplexShot.png", p, 50, 50, new Vector2d(0, -50), eM, ship);
	}
	
	@Override
	public void move(long delta) {
		super.move(delta);
		entitiesManager.add(new EntityShotFromAllySubComplex(new Vector2d(getPosition().x+getBoundingBox().width/2.0-5, getPosition().y+getBoundingBox().height/2.0-5), new Vector2d((Math.cos(i)*300),(Math.sin(i)*300)), entitiesManager, ship));
		entitiesManager.add(new EntityShotFromAllySubComplex2(new Vector2d(getPosition().x+getBoundingBox().width/2.0-5, getPosition().y+getBoundingBox().height/2.0-5), new Vector2d((Math.cos(Math.PI+i)*300),(Math.sin(Math.PI+i)*300)), entitiesManager, ship));
		
		i += tourParSeconde*2*Math.PI*(delta/1000000000D);
	}
	
	@Override
	public synchronized void collidedWith(Entity other) {
		
		// if we've hit an alien, kill it!
		if (other instanceof EntityEnnemy) {
			// other prends les dégats donnés par le tir
			if (other.receiveDegat(this)) {
				// notify the game that the alien has been killed
				Server.serverInstance.notifyAlienKilled();
				degat++;
			}
		}

		//Si deux tirs se touchent (les 2 tirs dans les camps différents)
		if(other instanceof EntityShotFromEnnemy) {
			entitiesManager.remove(other);
		}
	}
	
}
