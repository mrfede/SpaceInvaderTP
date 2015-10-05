package fr.univ_artois.iut_lens.spaceinvader.client;

import java.awt.Color;
import java.awt.Graphics;

import fr.univ_artois.iut_lens.spaceinvader.sprites_manager.Sprite;
import fr.univ_artois.iut_lens.spaceinvader.sprites_manager.SpriteStore;
import fr.univ_artois.iut_lens.spaceinvader.util.Vector2d;

public class EntityRepresenter {
	
	private Vector2d position;
	private Vector2d speed;
	private String name;
	private Sprite sprite;
	private int currentLife = 0;
	private int maxLife = 0;
	
	public EntityRepresenter(int sprId, Vector2d pos, Vector2d sp) {
		setPosition(pos);
		setSpeed(sp);
		sprite = SpriteStore.get().getSpriteById(sprId);
	}

	public Vector2d getPosition() {
		return position;
	}

	public void setPosition(Vector2d position) {
		this.position = position;
	}

	public Vector2d getSpeed() {
		return speed;
	}

	public void setSpeed(Vector2d speed) {
		this.speed = speed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCurrentLife() {
		return currentLife;
	}

	public void setCurrentLife(int currentLife) {
		this.currentLife = currentLife;
	}

	public int getMaxLife() {
		return maxLife;
	}

	public void setMaxLife(int maxLife) {
		this.maxLife = maxLife;
	}
	

	
	/**
	 * Draw this entity to the graphics context provided
	 * 
	 * @param g The graphics context on which to draw
	 */
	public void draw(Graphics g) {
		sprite.draw(g,(int)position.x,(int)position.y);

		if (currentLife!=maxLife && currentLife > 0) {
			g.setColor(Color.DARK_GRAY);
			g.fillRect((int)position.x,(int)position.y, sprite.getWidth(), 3);
			g.setColor(new Color((float)Math.sqrt(1-(currentLife/(float)maxLife)), (float)Math.sqrt(currentLife/(float)maxLife), 0F));
			g.fillRect((int)position.x,(int)position.y, (int)((double)sprite.getWidth()*(currentLife/(double)maxLife)), 3);
		}
		
		if (name != null && name.length()>0) {
			g.setColor(Color.WHITE);
			g.drawString(name,(int)position.x,(int)position.y+5);
		}
		
	}

}
