package ggj_game.entities;

public abstract class Entity {

	public Entity(int x, int y, int ID) {
		initialize(x, y, ID);
	}
	
	public abstract void initialize( int x, int y , int ID);
	public abstract void render();
	public abstract void update(int i) throws InterruptedException;
	public abstract int getID();
	public abstract int getX();
	public abstract int getY();
}
