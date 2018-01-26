package ggj_game.states.test;

import ggj_game.utils.ImageRes;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Clientrace on 26/01/2018.
 */
public class Test_P extends BasicGameState{

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        Test_V.initialize();
        ImageRes.init();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sb, Graphics g) throws SlickException {
        Test_V.gameMap1.render(g,0,0);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sb, int i) throws SlickException {

    }
}