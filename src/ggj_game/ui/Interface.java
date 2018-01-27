package ggj_game.ui;

public abstract class Interface {
	
	
	public Interface(int x, int y) {
		initialize(x, y);
	}
	
	public abstract void initialize( int x, int y );
	public abstract void render();
	public abstract void update(int i);
}
