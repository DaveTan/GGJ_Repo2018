package ggj_game.states.test;

import ggj_game.entities.Entities_P;
import ggj_game.entities.Human_Entity;
import ggj_game.entities.Zombie_Entity;
import ggj_game.states.test.UI.Side_Upgrades_List;
import ggj_game.states.test.UI.Zombie_List;
import ggj_game.utils.ImageRes;
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
public class Test_P extends BasicGameState implements MouseListener {
    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        Test_V.initialize();
        ImageRes.init();
        Zombie_List.initCards();
//        Test_V.entity.setDest(5,5);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sb, Graphics g) throws SlickException {
        Test_V.gameMap1.render(g,0,0);
        g.setColor(new Color(255,255,255));
//        g.setColor(new Color(255,255,255));
//        g.fillRect(Test_V.entity.getX(),Test_V.entity.getY(),32,32);
//        Test_V.entity.render();
        Entities_P.draw_human(g);
        Entities_P.draw_zombie(g);
        Entities_P.draw_effects(g);
        Zombie_List.render(g);
  //      Side_Upgrades_List.render(g);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sb, int i) throws SlickException {
        Entities_P.update_zombie(i);
        Entities_P.update_human(i);
        Entities_P.update_effects(i);
    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount){
    }

    @Override
    public void mouseDragged(int oldx, int oldy, int newx, int newy){
        System.out.println(Thread.currentThread().getStackTrace()[1]);
        //Entities_P.add_zombie(new Zombie_Entity(newx, newy));
    }

    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy){
        Zombie_List.update(newx,newy);
    }
    
    @Override
    public void mousePressed(int button, int x, int y){
    	System.out.println(Thread.currentThread().getStackTrace()[1]);
    	//Entities_P.add_zombie(new Zombie_Entity(x, y));
        if(button==0) {
            Entities_P.add_zombie(new Zombie_Entity(x, y));
            Entities_P.add_zombie(new Zombie_Entity(x+15, y));
            Entities_P.add_zombie(new Zombie_Entity(x+30, y+10));
            Entities_P.add_zombie(new Zombie_Entity(x+45, y+15));
            Entities_P.add_zombie(new Zombie_Entity(x+60, y+20));
            Entities_P.add_zombie(new Zombie_Entity(x, y));
            Entities_P.add_zombie(new Zombie_Entity(x+15, y+15));
            Entities_P.add_zombie(new Zombie_Entity(x+30, y+30));
            Entities_P.add_zombie(new Zombie_Entity(x+45, y+45));
            Entities_P.add_zombie(new Zombie_Entity(x+60, y+60));
        }
        if(button==1)
            Entities_P.add_human(new Human_Entity(x,y));
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
