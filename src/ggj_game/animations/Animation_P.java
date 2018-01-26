package ggj_game.animations;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

public class Animation_P {
	public static Animation LoadAnimation ( SpriteSheet SS_Input, int Row, int FrameCount, int Speed ){
		Animation retval = new Animation();
		
		for( int a=0; a<FrameCount; a++ ){
			retval.addFrame( SS_Input.getSprite(a, Row), Speed );
		}
		
		return retval;
	}
}
