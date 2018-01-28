package ggj_game.states.game_over;

import java.awt.Font;
import java.io.InputStream;
import java.util.ArrayList;

import ggj_game.entities.Entities_P;
import ggj_game.input.Keyboard_P;
import ggj_game.states.StateID_C;
import ggj_game.states.State_S;
import ggj_game.utils.EventHandler;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

public class GameOver_P extends BasicGameState implements KeyListener{
	
	private TrueTypeFont font,font2;
	private Font awtFont2;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
        try {
            InputStream inputStream = ResourceLoader.getResourceAsStream("res/dpcomic.ttf");
            awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            awtFont2 = awtFont2.deriveFont(20f); // set font size
            font2 = new TrueTypeFont(awtFont2, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		g.setFont(font2);
		g.drawString("GameOver!! Press Enter to Restart", 200, 200);
		g.drawString("You Survived for: " + EventHandler.DAY + " Days", 200, 250);
		
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame sb, int arg2)
			throws SlickException {
		if(Keyboard_P.isPressed(Keyboard.KEY_RETURN)){
			System.out.println(">>>");
			EventHandler.init();
			Entities_P.initialize();
			
			State_S.StateList[StateID_C.TEST_PATHFIND].init(arg0, sb);
			sb.enterState(StateID_C.TEST_PATHFIND);
		}
	}

	@Override
	public int getID() {
		return StateID_C.GAMEOVER;
	}
	
	public void keyPressed(int key, char c) {
    	Keyboard_P.addKeypress(key);
    }
	
}
