package ggj_game.sprites;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class SpriteSheets_R {
	/* Humans */
	public static SpriteSheet SS_HumanMelee;
	public static SpriteSheet SS_HumanRifle;
	
	/* Zombies */
	public static SpriteSheet SS_ZombieContact;
	public static SpriteSheet SS_ZombieAir;
	
	public static void initialize(){
		try {
			SS_HumanMelee = new SpriteSheet( new Image(SpriteSheets_C.SPRITE_DIR + SpriteSheets_C.HUMAN_MELEE), SpriteSheets_C.SPRITE_SIZE, SpriteSheets_C.SPRITE_SIZE );
			
			SS_ZombieContact = new SpriteSheet( new Image(SpriteSheets_C.SPRITE_DIR + SpriteSheets_C.ZOMBIE_CONTACT), SpriteSheets_C.SPRITE_SIZE, SpriteSheets_C.SPRITE_SIZE );
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
