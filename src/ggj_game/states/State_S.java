package ggj_game.states;

/* SLICK LIBRARY */
import ggj_game.states.menu.Menu_P;

import org.newdawn.slick.state.BasicGameState;

public class State_S {
	/* INITIAL STATE */
	public static int initialState 				= 	StateID_C.MENU;
	
	/* STATE OBJECTS */
	public static BasicGameState StateList[] 	= {
		new Menu_P(),		/* StateID: 0 */
	};
}
