package ggj_game.sprites;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class SpriteSheets_R {
	/* EXPLOSION */
	public static SpriteSheet SS_Expl, SS_Exp2;
	
	/* Humans */
	public static SpriteSheet SS_HumanMelee;
	public static SpriteSheet SS_HumanRifle;
	
	/* Zombies */
	public static SpriteSheet SS_ZombieMelee;
	
	public static SpriteSheet SS_ZombieAir;
	
	/* ETC */
	public static SpriteSheet SS_DeadBody;
	
	public static SpriteSheet SS_Blood;
	
	public static SpriteSheet SS_ZombieDeath;

	public static SpriteSheet SS_Explostion3;
	
	public static void initialize(){
		try {
			SS_HumanMelee = new SpriteSheet( new Image(SpriteSheets_C.SPRITE_DIR + SpriteSheets_C.HUMAN_MELEE), SpriteSheets_C.SPRITE_SIZE, SpriteSheets_C.SPRITE_SIZE );
			
			SS_HumanRifle = new SpriteSheet( new Image(SpriteSheets_C.SPRITE_DIR + SpriteSheets_C.HUMAN_RIFLE), SpriteSheets_C.SPRITE_SIZE, SpriteSheets_C.SPRITE_SIZE );
			
			SS_ZombieMelee = new SpriteSheet( new Image(SpriteSheets_C.SPRITE_DIR + SpriteSheets_C.ZOMBIE_MELEE), SpriteSheets_C.SPRITE_SIZE, SpriteSheets_C.SPRITE_SIZE );
			
			SS_ZombieAir = new SpriteSheet( new Image(SpriteSheets_C.SPRITE_DIR + SpriteSheets_C.ZOMBIE_AIR), SpriteSheets_C.SPRITE_SIZE, SpriteSheets_C.SPRITE_SIZE );
			
			SS_Expl = new SpriteSheet( new Image(SpriteSheets_C.SPRITE_DIR + SpriteSheets_C.EFF_EXPL), 160, 120 );
		
			SS_Expl = new SpriteSheet( new Image(SpriteSheets_C.SPRITE_DIR + SpriteSheets_C.EFF_EXPL), 160, 120 );
			
			SS_Exp2 = new SpriteSheet( new Image(SpriteSheets_C.SPRITE_DIR + SpriteSheets_C.EFF_EXPL2), 80, 60 );

			SS_DeadBody = new SpriteSheet( new Image(SpriteSheets_C.SPRITE_DIR + SpriteSheets_C.DEAD_BODY), 32, 32 );
			
			SS_Blood = new SpriteSheet( new Image(SpriteSheets_C.SPRITE_DIR + SpriteSheets_C.BLOOD), 32, 32 );
			
			SS_ZombieDeath = new SpriteSheet( new Image(SpriteSheets_C.SPRITE_DIR + "Zombie1_Death.png"), 32, 32 );

			SS_Explostion3 = new SpriteSheet( new Image(SpriteSheets_C.SPRITE_DIR + "explosion3.png"), 80, 80);
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
