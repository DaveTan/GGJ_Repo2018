package ggj_game.entities;

import java.util.ArrayList;

import ggj_game.animations.Animation_P;
import ggj_game.states.menu.Menu_R;
import ggj_game.states.menu.Menu_V;

import org.newdawn.slick.Animation;

public class Test_Entity_P extends Entity{
	ArrayList<Animation> animationStates;
	int currentState;
	int posX;
	int posY;
	
	public Test_Entity_P(int x, int y) {
		super(x, y);
	}
	
	@Override
	public void initialize(int x, int y) {
		animationStates = new ArrayList<Animation>();
		
		animationStates.add(Animation_P.LoadAnimation(Menu_R.Test, Test_Entity_C.IDLE_LEFT, 10, 250));
		animationStates.add(Animation_P.LoadAnimation(Menu_R.Test, Test_Entity_C.WALK_LEFT, 6, 250));
		animationStates.add(Animation_P.LoadAnimation(Menu_R.Test, Test_Entity_C.IDLE_RIGHT, 10, 250));
		animationStates.add(Animation_P.LoadAnimation(Menu_R.Test, Test_Entity_C.WALK_RIGHT, 6, 250));
		
		for(int a=0; a<animationStates.size();a++){
			animationStates.get(a).start();
		}
		
		this.posX = x;
		this.posY = y;
		
		currentState = Test_Entity_C.INITIAL_STATE;
	}

	@Override
	public void render() {
		animationStates.get(currentState).draw(posX, posY);
	}

	@Override
	public void update(int i) {
		for(int a=0;a<animationStates.size();a++){
			animationStates.get(a).update(i);
		}
		
		if(Menu_V.EventVar == 2){
			currentState = Test_Entity_C.WALK_LEFT;
			posX-=1;
		}
		else if(Menu_V.EventVar == 3){
			currentState = Test_Entity_C.WALK_RIGHT;
			posX+=1;
		}
	}
}
