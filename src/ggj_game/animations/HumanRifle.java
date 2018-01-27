package ggj_game.animations;

import ggj_game.sprites.SpriteSheets_R;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

public class HumanRifle extends Animation_T{
	/* SPRITE SHEET */
	private static SpriteSheet SS = SpriteSheets_R.SS_HumanRifle;
	
	/* STATES */
	public static final int WALK_LEFT = 0;
	public static final int WALK_RIGHT = 1;
	
	/* SETTINGS */
	public static final int FRAME_SPEED = 250;
	
	public static ArrayList<Animation> get(){
		ArrayList<Animation> animationStates = new ArrayList<Animation>();
		
		animationStates.add(Get_Animation(SS, 0, SS.getHorizontalCount(), FRAME_SPEED));
		animationStates.add(Get_Animation(SS, 1, 3, FRAME_SPEED));
		
		return animationStates;
	}
}
