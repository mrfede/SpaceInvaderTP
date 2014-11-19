package fr.univ_artois.iut_lens.spaceinvader.levels;

import java.util.ArrayList;

import fr.univ_artois.iut_lens.spaceinvader.EntitiesManager;
import fr.univ_artois.iut_lens.spaceinvader.entities.Entity;
import fr.univ_artois.iut_lens.spaceinvader.entities.ennemy.EntityEnnemy;
import fr.univ_artois.iut_lens.spaceinvader.entities.ennemy.strategy.move.StrategyMoveEnnemyFinalBoss;
import fr.univ_artois.iut_lens.spaceinvader.entities.ennemy.strategy.move.StrategyMoveEnnemyRandom;
import fr.univ_artois.iut_lens.spaceinvader.entities.ennemy.strategy.shot.StrategyShotEnnemyAimFor;
import fr.univ_artois.iut_lens.spaceinvader.entities.ennemy.strategy.shot.StrategyShotEnnemyMegaBoss;
import fr.univ_artois.iut_lens.spaceinvader.util.Vector2d;

public class LevelFinalBoss2 extends Level {

	public LevelFinalBoss2(EntitiesManager entitiesManager) {
		super(entitiesManager,
				1,
				1,
				"sprites/spartanm1.png",
				new Vector2d(50, 50),
				new Vector2d(100, 50),
				new StrategyMoveEnnemyRandom(),
				new StrategyShotEnnemyMegaBoss());
		
	}
	
	@Override
	public ArrayList<Entity> generateLevel() {
		SquadList = new ArrayList<Entity>();
		SquadList.add(new EntityEnnemy(sprite,new Vector2d(10,10), 999999, entitiesManager));
		SquadList.add(new EntityEnnemy(sprite,new Vector2d(250,10), 999999, entitiesManager));
		SquadList.add(new EntityEnnemy(sprite,new Vector2d(510,10), 999999, entitiesManager));
		nbCount = 3;
		return SquadList;
	}
	
	public boolean hasOneDestroyed() {
		
		boolean ret = super.hasOneDestroyed();
		
		if(nbCount==2) {
			strategyMove = new StrategyMoveEnnemyFinalBoss();
			strategyShot = new StrategyShotEnnemyAimFor(2);
		}
		return ret;
		
	}

}