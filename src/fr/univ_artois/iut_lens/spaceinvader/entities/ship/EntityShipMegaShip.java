package fr.univ_artois.iut_lens.spaceinvader.entities.ship;

import fr.univ_artois.iut_lens.spaceinvader.entities.shot.EntityShotFromAllyAdvanced;
import fr.univ_artois.iut_lens.spaceinvader.entities.shot.EntityShotFromAllyBasic;
import fr.univ_artois.iut_lens.spaceinvader.server.EntitiesManager;
import fr.univ_artois.iut_lens.spaceinvader.util.Vector2d;

public class EntityShipMegaShip extends EntityShip {
	
	
	public EntityShipMegaShip(EntitiesManager eM) {
		super("sprites/Spaceship_tut.png", 600, eM, 50);
	}

	@Override
	public void shoot(long currentTime) {
		
		entitiesManager.getEntitiesList().add(new EntityShotFromAllyBasic(new Vector2d(position.x+getBoundingBox().width/2-23, position.y+25), new Vector2d(-300, -800), entitiesManager));
		entitiesManager.getEntitiesList().add(new EntityShotFromAllyAdvanced(new Vector2d(position.x+getBoundingBox().width/2-20, position.y), new Vector2d(0, -300), entitiesManager));
		entitiesManager.getEntitiesList().add(new EntityShotFromAllyBasic(new Vector2d(position.x+getBoundingBox().width/2-10, position.y), new Vector2d(-150, -1000), entitiesManager));
		entitiesManager.getEntitiesList().add(new EntityShotFromAllyAdvanced(new Vector2d(position.x+getBoundingBox().width/2-5, position.y), new Vector2d(0, -300), entitiesManager));
		entitiesManager.getEntitiesList().add(new EntityShotFromAllyBasic(new Vector2d(position.x+getBoundingBox().width/2, position.y), new Vector2d(150, -1000), entitiesManager));
		entitiesManager.getEntitiesList().add(new EntityShotFromAllyAdvanced(new Vector2d(position.x+getBoundingBox().width/2+10, position.y), new Vector2d(0, -300), entitiesManager));
		entitiesManager.getEntitiesList().add(new EntityShotFromAllyBasic(new Vector2d(position.x+getBoundingBox().width/2+13, position.y+25), new Vector2d(300, -800), entitiesManager));

	}

}
