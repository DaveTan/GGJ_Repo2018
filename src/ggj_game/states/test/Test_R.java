package ggj_game.states.test;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by Clientrace on 26/01/2018.
 */
public class Test_R {
	public static Image Shade;
	
	public static Image Card1;
	public static Image Card2;
	public static Image Card3;
	
	public static void initialize(){
		try {
			Shade = new Image("res/shade.png");
			Card1 = new Image("res/card1.png");
			Card2 = new Image("res/card2.png");
			Card3 = new Image("res/card3.png");
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
