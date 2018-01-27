package ggj_game.states.test;

import ggj_game.entities.Entities_P;
import ggj_game.entities.Test_Entity_P;
import ggj_game.input.Keyboard_P;
import ggj_game.utils.ImageRes;
import ggj_game.utils.game_map.GameMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Clientrace on 26/01/2018.
 */
public class Test_P extends BasicGameState implements MouseListener{

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        Test_V.initialize();
        ImageRes.init();
//        Test_V.entity.setDest(5,5);
        Entities_P.add(new Test_Entity_P(3, 3));
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sb, Graphics g) throws SlickException {
        Test_V.gameMap1.render(g,0,0);
//        g.setColor(new Color(255,255,255));
//        g.fillRect(Test_V.entity.getX(),Test_V.entity.getY(),32,32);
//        Test_V.entity.render();
        Entities_P.draw();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sb, int i) throws SlickException {
//        Test_V.entity.update(i);
    	Entities_P.update(i);
    }
    
    @Override
    public void mousePressed(int button, int x, int y){
    	System.out.println(Thread.currentThread().getStackTrace()[1]);
    	Entities_P.add(new Test_Entity_P(x, y));
    }
}
