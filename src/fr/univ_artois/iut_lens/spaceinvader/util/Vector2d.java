package fr.univ_artois.iut_lens.spaceinvader.util;

public class Vector2d {
	/** Horizontal position from the left of the screen */
	public double x;
	
	/** Vertical position from the top of the screen */
	public double y;
	
	/**
	 * Constructor to set position
	 * @param x Coordinates left to right
	 * @param y Coordinates up to down
	 */
	public Vector2d(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Default constructor
	 */
	public Vector2d() {
		this(0,0);
	}
	
	/**
	 * Copy constructor
	 */
	public Vector2d(Vector2d c) {
		this(c.x,c.y);
	}
	
	
	public Vector2d add(Vector2d v)
	{
		return new Vector2d(x+v.x, y+v.y);
	}
	
	public Vector2d dotProduct(double k)
	{
		return new Vector2d(x*k, y*k);
	}
	
	public Vector2d invert()
	{
		return new Vector2d(-x, -y);
	}
	
	public Vector2d invertX()
	{
		return new Vector2d(-x, y);
	}
	
	public Vector2d invertY()
	{
		return new Vector2d(x, -y);
	}
	
	public double distanceSquaredOf(Vector2d v)
	{
		return (x - v.x)*(x - v.x) + (y - v.y)*(y - v.y);
	}
	
	public double distanceOf(Vector2d v)
	{
		return Math.sqrt(distanceSquaredOf(v));
	}
}
