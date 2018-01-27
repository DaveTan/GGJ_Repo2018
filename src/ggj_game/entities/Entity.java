package ggj_game.entities;

import org.newdawn.slick.Graphics;

public abstract class Entity {

	public Entity(int x, int y, int ID) {
		initialize(x, y, ID);
	}
	
	public abstract void initialize( int x, int y , int ID);
	public abstract void render(Graphics g);
	public abstract void update(int i);
	public abstract int getID();
	public abstract int getX();
	public abstract int getY();
}
