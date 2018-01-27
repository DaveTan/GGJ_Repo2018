package ggj_game.animations;

import ggj_game.sprites.SpriteSheets_R;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

public class ZombieContact extends Animation_T{
	/* SPRITE SHEET */
	private static SpriteSheet SS = SpriteSheets_R.SS_ZombieContact;
	
	/* STATES */
	public static final int WALK_LEFT = 0;
	public static final int WALK_RIGHT = 1;
	
	/* SETTINGS */
	public static final int FRAME_SPEED = 320;
	
	public static ArrayList<Animation> get(){
		ArrayList<Animation> animationStates = new ArrayList<Animation>();
		
		for(int a=0; a< SS.getVerticalCount(); a++){
			animationStates.add(Get_Animation(SS, a, SS.getHorizontalCount(), FRAME_SPEED));
		}
		
		return animationStates;
	}
}
