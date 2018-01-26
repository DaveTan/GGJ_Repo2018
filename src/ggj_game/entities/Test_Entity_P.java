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
		
		this.posX = x;
		this.posY = y;
		
		changeState(Test_Entity_C.INITIAL_STATE);
	}

	@Override
	public void render() {
		animationStates.get(currentState).draw(posX, posY);
	}

	@Override
	public void update() {
		if(Menu_V.EventVar == 2){
			changeState(Test_Entity_C.WALK_LEFT);
		}
		else if(Menu_V.EventVar == 3){
			changeState(Test_Entity_C.WALK_RIGHT);
		}
	}
	
	private void changeState(int state){
		animationStates.get(currentState).stop();
		
		currentState = state;
		
		animationStates.get(currentState).start();
	}
}
