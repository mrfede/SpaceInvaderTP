package fr.univ_artois.iut_lens.spaceinvader.server.entities.ship;

import fr.univ_artois.iut_lens.spaceinvader.server.EntitiesManager;
import fr.univ_artois.iut_lens.spaceinvader.server.entities.shot.EntityShotFromAllyAdvanced;
import fr.univ_artois.iut_lens.spaceinvader.server.entities.shot.EntityShotFromAllyBasic;
import fr.univ_artois.iut_lens.spaceinvader.server.players.ShipManager;
import fr.univ_artois.iut_lens.spaceinvader.util.Vector2d;

public class EntityShipMegaShip extends EntityShip {
	
	
	public EntityShipMegaShip(EntitiesManager eM, ShipManager sm) {
		super("sprites/Spaceship_tut.png", 600, eM, 50, sm);
	}

	@Override
	public void shoot(long currentTime) {
		
		entitiesManager.add(new EntityShotFromAllyBasic(new Vector2d(getPosition().x+getBoundingBox().width/2-23, getPosition().y+25), new Vector2d(-300, -800), entitiesManager, this));
		entitiesManager.add(new EntityShotFromAllyAdvanced(new Vector2d(getPosition().x+getBoundingBox().width/2-20, getPosition().y), new Vector2d(0, -300), entitiesManager, this));
		entitiesManager.add(new EntityShotFromAllyBasic(new Vector2d(getPosition().x+getBoundingBox().width/2-10, getPosition().y), new Vector2d(-150, -1000), entitiesManager, this));
		entitiesManager.add(new EntityShotFromAllyAdvanced(new Vector2d(getPosition().x+getBoundingBox().width/2-5, getPosition().y), new Vector2d(0, -300), entitiesManager, this));
		entitiesManager.add(new EntityShotFromAllyBasic(new Vector2d(getPosition().x+getBoundingBox().width/2, getPosition().y), new Vector2d(150, -1000), entitiesManager, this));
		entitiesManager.add(new EntityShotFromAllyAdvanced(new Vector2d(getPosition().x+getBoundingBox().width/2+10, getPosition().y), new Vector2d(0, -300), entitiesManager, this));
		entitiesManager.add(new EntityShotFromAllyBasic(new Vector2d(getPosition().x+getBoundingBox().width/2+13, getPosition().y+25), new Vector2d(300, -800), entitiesManager, this));

	}

}
