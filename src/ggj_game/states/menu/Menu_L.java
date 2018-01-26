package ggj_game.states.menu;

import ggj_game.sound.Sound_C;
import ggj_game.sound.Sound_P;

public class Menu_L {
	
	
	public static void getMessage(Object input){
		
	}
	
	public static void event1(){
		Menu_V.EventVar = 1;
		
		Sound_P.Play(Sound_C.Bell_ID);
	}
	public static void event2(){
		Menu_V.EventVar = 2;
	}
	public static void event3(){
		Menu_V.EventVar = 3;
	}
	public static void event4(){
		Menu_V.EventVar = 4;
	}
	public static void event5(){
		Menu_V.EventVar = 5;
	}
	public static void event6(){
		Menu_V.EventVar = 6;
	}
}
