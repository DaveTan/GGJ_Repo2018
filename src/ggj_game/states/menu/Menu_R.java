package ggj_game.states.menu;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Menu_R {
	/* 64 x 128 */
	public static SpriteSheet Test;
	
	/* 32 x32 */
	public static SpriteSheet Human;
	public static SpriteSheet Zombie;
	
	public static int Test_State;
	public static int Test_Frame;
	
	public static void initialize(){
		try {
			Test = new SpriteSheet( new Image("res/Human_Melee.png"), 64, 128 );
//			Human = new SpriteSheet( new Image("res/zombehh.png"), 32, 32 );
			Zombie = new SpriteSheet( new Image("res/Zombie_Contact.png"), 32, 32 );
//			Human = new SpriteSheet( new Image("res/human_melee_walk_spritesheet.png"), 32, 32 );
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
