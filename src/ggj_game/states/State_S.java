package ggj_game.states;

/* SLICK LIBRARY */
import ggj_game.states.game_over.GameOver_P;
import ggj_game.states.menu.Menu_P;
import ggj_game.states.test.Test_P;

import org.newdawn.slick.state.BasicGameState;

public class State_S {
	/* INITIAL STATE */
	public static int initialState 				= 	StateID_C.MENU;
	public static int testState					=	StateID_C.TEST_PATHFIND;
	public static int gameOverState				=	StateID_C.GAMEOVER;
	
	/* STATE OBJECTS */
	public static BasicGameState StateList[] 	= {
			new Menu_P(),		/* StateID: 0 */
			new Test_P(),
			new GameOver_P()
	};
}
