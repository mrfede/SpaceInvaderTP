package fr.univ_artois.iut_lens.spaceinvader.entities.ship;

import fr.univ_artois.iut_lens.spaceinvader.EntitiesManager;
import fr.univ_artois.iut_lens.spaceinvader.entities.shot.EntityShotFromAllyComplex;
import fr.univ_artois.iut_lens.spaceinvader.util.Vector2d;

public class EntityShipSecretShip extends EntityShip {
	protected long lastFireTime = 0; //Dernier tir du vaisseau
	private long fireInterval = 1000; //Intervalle de temps par défaut pour lequel le vaisseau peut tirer

	public EntityShipSecretShip(EntitiesManager eM) {
		super("sprites/SecretShip.png", 500, eM);
	}

	@Override
	public void tryToShoot(long currentTime) {
		if(currentTime - lastFireTime < fireInterval) return; //L'interval de tir est trop court
		lastFireTime = currentTime; //On met le dernier tire au temps actuel
		
		entitiesManager.getEntitiesList().add(new EntityShotFromAllyComplex(new Vector2d(position.x+getBoundingBox().width/2-32, position.y), entitiesManager));
		entitiesManager.getEntitiesList().add(new EntityShotFromAllyComplex(new Vector2d(position.x+getBoundingBox().width/2, position.y), entitiesManager));
	}

}
