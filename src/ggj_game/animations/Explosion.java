package ggj_game.animations;

import ggj_game.sprites.SpriteSheets_R;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

public class Explosion extends Animation_T{
	/* SPRITE SHEET */
	private static SpriteSheet SS = SpriteSheets_R.SS_Expl;
	
	private static SpriteSheet SS2 = SpriteSheets_R.SS_Exp2;
	
	/* Dead Body */
	private static SpriteSheet SS3 = SpriteSheets_R.SS_DeadBody;
	
	/* BLood */
	private static SpriteSheet SS4 = SpriteSheets_R.SS_Blood;
	
	private static SpriteSheet SS5 = SpriteSheets_R.SS_ZombieDeath;


	private static SpriteSheet SS6 = SpriteSheets_R.SS_Explostion3;
	
	/* STATES */
	public static final int WALK_LEFT = 0;
	public static final int WALK_RIGHT = 1;
	
	/* SETTINGS */
	public static final int FRAME_SPEED = 250;
	
	public static ArrayList<Animation> get_Explosion(){
		ArrayList<Animation> animationStates = new ArrayList<Animation>();
		
		for(int a=0; a< SS.getVerticalCount(); a++){
			animationStates.add(Get_Animation(SS, a, SS.getHorizontalCount(), FRAME_SPEED));
		}
		
		return animationStates;
	}

	public static ArrayList<Animation> get_Explosion2(){
		ArrayList<Animation> animationStates = new ArrayList<Animation>();

		for(int a=0; a< SS2.getVerticalCount(); a++){
			animationStates.add(Get_Animation(SS2, a, SS2.getHorizontalCount(), FRAME_SPEED));
		}

		return animationStates;
	}

	public static ArrayList<Animation> get_Explosion3(){
		ArrayList<Animation> animationStates = new ArrayList<Animation>();

		for(int a=0; a< SS6.getVerticalCount(); a++){
			animationStates.add(Get_Animation(SS6, a, SS6.getHorizontalCount(), FRAME_SPEED));
		}

		return animationStates;
	}
	
	public static ArrayList<Animation> get_DeadBody(){
		ArrayList<Animation> animationStates = new ArrayList<Animation>();

		for(int a=0; a< SS3.getVerticalCount(); a++){
			animationStates.add(Get_Animation(SS3, a, SS3.getHorizontalCount(), FRAME_SPEED));
		}

		return animationStates;
	}
	
	public static ArrayList<Animation> get_Blood(){
		ArrayList<Animation> animationStates = new ArrayList<Animation>();
		
		Random rand = new Random();
		
		int randomNum = rand.nextInt(4);
		
		for(int a=0; a< SS4.getVerticalCount(); a++){
			animationStates.add(Get_Animation(SS4, randomNum, SS4.getHorizontalCount(), 200));
		}
		
		return animationStates;
	}
	
	public static ArrayList<Animation> get_ZombieDeath1(){
		ArrayList<Animation> animationStates = new ArrayList<Animation>();
		
		animationStates.add(Get_Animation(SS5, 1, SS5.getHorizontalCount(), 200));
		
		return animationStates;
	}
}
