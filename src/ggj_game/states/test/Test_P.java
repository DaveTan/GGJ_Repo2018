package ggj_game.states.test;

import ggj_game.utils.ImageRes;
import ggj_game.utils.game_map.GameMap;
import org.newdawn.slick.Color;
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
        Test_V.entity.setDest(5,5);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sb, Graphics g) throws SlickException {
        Test_V.gameMap1.render(g,0,0);
        g.setColor(new Color(255,255,255));
        g.fillRect(Test_V.entity.getX(),Test_V.entity.getY(),32,32);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sb, int i) throws SlickException {
        Test_V.entity.update();
    }
}
