package ggj_game.states.test;

import ggj_game.entities.Entities_P;
import ggj_game.entities.Test_Entity_P;
import ggj_game.states.test.UI.Zombie_List;
import ggj_game.utils.ImageRes;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Clientrace on 26/01/2018.
 */
public class Test_P extends BasicGameState implements MouseListener {

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        Test_V.initialize();
        ImageRes.init();
        Test_V.entity.setDest(5,5);
        Zombie_List.initCards();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sb, Graphics g) throws SlickException {
        Test_V.gameMap1.render(g,0,0);
        g.setColor(new Color(255,255,255));
        Zombie_List.render(g);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sb, int i) throws SlickException {

        Test_V.entity.update();

    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount){
        Entities_P.add(new Test_Entity_P(x, y));
    }

    @Override
    public void mouseDragged(int oldx, int oldy, int newx, int newy){
        System.out.println(Thread.currentThread().getStackTrace()[1]);
        Entities_P.add(new Test_Entity_P(newx, newy));
    }

    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy){
        Zombie_List.update(newx,newy);
    }

    @Override
    public void mousePressed(int button, int x, int y){
        System.out.println(Thread.currentThread().getStackTrace()[1]);
    }

    @Override
    public void mouseReleased(int button, int x, int y){
        System.out.println(Thread.currentThread().getStackTrace()[1]);
    }

    @Override
    public void mouseWheelMoved(int change){
        System.out.println(Thread.currentThread().getStackTrace()[1]);
    }


}
