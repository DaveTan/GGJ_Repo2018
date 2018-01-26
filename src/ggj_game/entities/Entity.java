package ggj_game.entities;

public abstract class Entity {
	public Entity(int x, int y) {
		initialize(x, y);
	}
	
	public abstract void initialize( int x, int y );
	public abstract void render();
	public abstract void update();
}
