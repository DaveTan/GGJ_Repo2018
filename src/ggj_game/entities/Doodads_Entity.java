package ggj_game.entities;

import ggj_game.animations.Explosion;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;

public class Doodads_Entity extends Entity{
	ArrayList<Animation> animationStates;
	
	private int worldX;
    private int worldY;
    private int ID;
	
	public Doodads_Entity(int x, int y, int ID) {
		super(x, y, Entities_P.entCount++);
	}

	public void initialize(int x, int y, int ID){
		this.ID = ID;
		
		worldX = x;
		worldY = y;
		
		animationStates = Explosion.get_DeadBody();
	}
	
	public void update(int i) {
		for(int a=0;a<animationStates.size();a++){
            animationStates.get(a).update(i);
        }
	}
	
	public void render(Graphics g){
		animationStates.get(0).draw(worldX, worldY, 32, 32);
	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public int getX() {
		return worldX;
	}

	@Override
	public int getY() {
		return worldY;
	}
	
	
}
