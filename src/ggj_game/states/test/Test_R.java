package ggj_game.states.test;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by Clientrace on 26/01/2018.
 */
public class Test_R {
	public static Image Shade;
	
	public static void initialize(){
		try {
			Shade = new Image("res/shade.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
