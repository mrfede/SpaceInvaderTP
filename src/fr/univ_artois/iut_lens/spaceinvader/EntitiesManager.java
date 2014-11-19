package fr.univ_artois.iut_lens.spaceinvader;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.univ_artois.iut_lens.spaceinvader.entities.Entity;
import fr.univ_artois.iut_lens.spaceinvader.entities.ennemy.EntityEnnemy;
import fr.univ_artois.iut_lens.spaceinvader.entities.shot.EntityShotFromAlly;
import fr.univ_artois.iut_lens.spaceinvader.entities.shot.EntityShotFromEnnemy;

/**
 * Cette classe sert juste à gérer les entitées
 *
 */
public class EntitiesManager {

	private List<Entity> entities = new ArrayList<Entity>();
	private List<Entity> removeList = Collections.synchronizedList(new ArrayList<Entity>());
	
	private CollisionThread[] threads = new CollisionThread[Runtime.getRuntime().availableProcessors()];
	
	private int lastCollisionComputingNumber = 0;
	
	//Méthode déssinant les entités
	public void draw(Graphics2D g) {
		// cycle round drawing all the entities we have in the game
		try
		{
	        for(Entity entity : entities.toArray(new Entity[entities.size()]))
				entity.draw(g);
		}
		catch(Exception e)
		{
			
		}
	}
	
	//Fonction permettant déplacer les entités
	public void moveEntities(long delta, LevelManager levelMan) {
		
		levelMan.getCurrentLevel().getCurrentStrategyMove().performMove(delta, this);
		
		for(Entity entity : entities.toArray(new Entity[entities.size()]))
		{
	    	if (entity instanceof EntityEnnemy)
	    		continue; // géré par la stratégie de déplacement
			entity.move(delta);
		}
	}
	
	public void makeEntitiesShoot(LevelManager levelMan) {
		levelMan.getCurrentLevel().getCurrentStrategyShot().performShot(this);
	}
	
	
	
	
	public void doCollisions() {
		
		//List<Entity[]> collisionsWork = new ArrayList<Entity[]>();
		
		
		// brute force collisions, compare every entity against
		// every other entity. If any of them collide notify 
		// both entities that the collision has occured
		
		// listage des paires d'entités à comparer
		// -> calcul multi-threadé
		int nbColision = 0;
		for (int p=0;p<entities.size();p++) {
			for (int s=p+1;s<entities.size();s++) {
				//Entity[] ents = new Entity[2];
				Entity me = entities.get(p);
				Entity him = entities.get(s);
				if (me instanceof EntityShotFromAlly && him instanceof EntityShotFromAlly) continue;
				if (me instanceof EntityShotFromEnnemy && him instanceof EntityShotFromEnnemy) continue;
				if (me instanceof EntityEnnemy && him instanceof EntityEnnemy) continue;
				//ents[0] = me;
				//ents[1] = him;
				
				// multiThread
				// collisionsWork.add(ents);
				
				
				// monothread
				if (removeList.contains(me) || removeList.contains(him)) continue;
				
				if (me.collidesWith(him)) {
					me.collidedWith(him);
					him.collidedWith(me);
				}
				// // monothread */
				
				nbColision++;
			}
		}
		
		lastCollisionComputingNumber = nbColision;
		
		// calcul des collisions en multithread
		/*
		int[][] threadDistrib = CollisionThread.getThreadDistribution(collisionsWork.size());
		for (int i=0; threadDistrib != null && i<threadDistrib.length; i++)
		{
			threads[i] = new CollisionThread(threadDistrib[i][0], threadDistrib[i][1], collisionsWork, this);
			threads[i].setName("Collision Thread #"+i);
			threads[i].start();
		}
		
		for (int i=0; threadDistrib != null && i<threadDistrib.length; i++)
		{
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}*/
		
		
		
		// remove any entity that has been marked for clear up
		entities.removeAll(removeList);
		removeList.clear();
	}
	
	
	public List<Entity> getEntitiesList() { return entities; }
	
	
	public int getTotalRemainingEnnemyLife()
	{
		int s = 0;
		for (Entity e : entities.toArray(new Entity[entities.size()]))
		{
			if (e instanceof EntityEnnemy)
				s+= e.getLife();
		}
		return s;
	}
	
	
	/**
	 * Remove an entity from the game. The entity removed will
	 * no longer move or be drawn.
	 * 
	 * @param entity The entity that should be removed
	 */
	public void removeEntity(Entity entity) {
		removeList.add(entity);
	}
	
	
	
	
	List<Entity> getRemovedList() { return removeList; }

	public int getLastCollisionComputingNumber() { return lastCollisionComputingNumber; }
	
	
	
	
	
	
	
}
















class CollisionThread extends Thread {
	private EntitiesManager entitiesManager;
	private List<Entity[]> collisionWorks;
	private int start, end;
	public CollisionThread(int s, int e, List<Entity[]> cW, EntitiesManager eM) {
		if (cW == null || cW.size() == 0 || s > e || s < 0 || e >= cW.size())
			throw new IllegalArgumentException();
		collisionWorks = cW;
		start = s;
		end = e;
		entitiesManager = eM;
	}
	
	@Override
	public void run() {
		
		for (int i=start; i<=end; i++)
		{
			Entity e1 = collisionWorks.get(i)[0], e2 = collisionWorks.get(i)[1];
			
			if (entitiesManager.getRemovedList().contains(e1) || entitiesManager.getRemovedList().contains(e2)) continue;
			
			if (e1.collidesWith(e2)) {
				e1.collidedWith(e2);
				e2.collidedWith(e1);
			}
			
		}
		
	}
	
	
	
	
	
	
	public static int[][] getThreadDistribution(int dataSize) {
		if (dataSize <= 0)
			return null;
		
		int t = Runtime.getRuntime().availableProcessors();
		
		int th = (dataSize<t)?Math.max(1, dataSize):t;
		
		int[][] r = new int[th][2];
		
		// t : thread number max ; dataSize : number of workunit to do
		
		int nb = dataSize/th;
		for (int cTh=0;cTh<th;cTh++)
		{
			r[cTh][0] = cTh*nb;
			r[cTh][1] = (cTh!=th-1)?(cTh+1)*nb-1:dataSize-1;
		}
		return r;
	}
	
}
