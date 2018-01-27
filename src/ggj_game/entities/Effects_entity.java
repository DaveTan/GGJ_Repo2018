package ggj_game.entities;

import ggj_game.animations.Explosion;
import ggj_game.animations.HumanMelee;

import org.newdawn.slick.Animation;

import java.util.ArrayList;

/**
 * Created by Clientrace on 27/01/2018.
 */
public class Effects_entity extends Entity{
    ArrayList<Animation> animationStates;
    int currentState;
    private int ID = 0;
    
    private int worldX;
    private int worldY;
    private int index;
    
    public Effects_entity(int x, int y, int index) {
        super(x, y, Entities_P.entCount++);
    }

    @Override
    public void initialize(int x, int y, int ID) {
    	this.ID = ID;
    	
    	worldX = x;
        worldY = y;

        if(index==0)
            animationStates = Explosion.get();
        if(index==1)
            animationStates = Explosion.get2();

        for(int a=0; a<animationStates.size();a++){
        	animationStates.get(a).setLooping(false);
            animationStates.get(a).start();
        }

        currentState = Test_Entity_C.INITIAL_STATE;
        
    }

    @Override
    public void render() {
        animationStates.get(currentState).draw(this.worldX, this.worldY);
    }

    @Override
    public void update(int i) {
        for(int a=0;a<animationStates.size();a++){
            animationStates.get(a).update(i);
            if(animationStates.get(a).isStopped()){
            	Entities_P.delete(this.ID, 2);
            }
        }
    }

    public int getX(){
        return worldX;
    }

    public int getY(){
        return worldY;
    }

	@Override
	public int getID() {
		return this.ID;
	}

}


