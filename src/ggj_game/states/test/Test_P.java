package ggj_game.states.test;

import java.awt.GraphicsEnvironment;

import ggj_game.entities.*;
import ggj_game.animations.Explosion;
import ggj_game.entities.Effects_entity;
import ggj_game.entities.Entities_P;
import ggj_game.entities.Human_Entity;
import ggj_game.entities.Zombie_Entity;


import ggj_game.states.test.UI.Side_Upgrades_List;
import ggj_game.entities.Zombie_Types;

import ggj_game.entities.Zombie_Types;
import ggj_game.sound.Sound_C;
import ggj_game.sound.Sound_P;
import ggj_game.states.test.UI.Zombie_List;
import ggj_game.utils.EventHandler;
import ggj_game.utils.ImageRes;
import ggj_game.utils.MapEffects;
import ggj_game.window.Window_C;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

import java.awt.Font;
import java.io.InputStream;

/**
 * Created by Clientrace on 26/01/2018.
 */
public class Test_P extends BasicGameState implements MouseListener {
	
	private TrueTypeFont font,font2;
	private Font awtFont2;
	private boolean game_over;
	private int Timer;
	
    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        Test_V.initialize();
        Test_R.initialize();
        ImageRes.init();
        Zombie_List.initCards();
//        Test_V.entity.setDest(5,5);
        EventHandler.init();
        Sound_P.Loop(Sound_C.MAIN_ID);
        game_over = false;
        Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
        font = new TrueTypeFont(awtFont, false);

        // load font from a .ttf file
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
    public void render(GameContainer gc, StateBasedGame sb, Graphics g) throws SlickException {
        
    	if(MapEffects.vibrate){
            MapEffects.vibrate(g,10);
        }
        Test_V.gameMap1.render(g,0,0);
        g.setColor(new Color(255,255,255));
//        g.setColor(new Color(255,255,255));
//        g.fillRect(Test_V.entity.getX(),Test_V.entity.getY(),32,32);
//        Test_V.entity.render();

        // SHADOWS
        for(int a = 0; a<Entities_P.zombies.size(); a++){
            int zombieX = Entities_P.zombies.get(a).getX();
            int zombieY = Entities_P.zombies.get(a).getY();
            g.drawImage(ImageRes.getSpriteImage("shadow",0,0),zombieX-11,zombieY+7);
        }
        for(int a = 0; a<Entities_P.humans.size(); a++){
            int humansX = Entities_P.humans.get(a).getX();
            int humansY = Entities_P.humans.get(a).getY();
            g.drawImage(ImageRes.getSpriteImage("shadow",0,0),humansX-11,humansY+15);
        }

        for(int i=0;i<EventHandler.mines.size();i++) {
            int mineX = EventHandler.mines.get(i).getX();
            int mineY = EventHandler.mines.get(i).getY();
            g.drawImage(ImageRes.getSpriteImage("mine", 0, 0), mineX,mineY);
        }
        Entities_P.draw_doodads(g);
        Entities_P.draw_human(g);
        Entities_P.draw_zombie(g);
        Entities_P.draw_effects(g);
        Zombie_List.render(g);
  //      Side_Upgrades_List.render(g);


        g.drawImage(ImageRes.getSpriteImage("ui",0,0),0,0);
        //Zombie_List.render(g);
//        awtFont2 = awtFont2.deriveFont(20f); // set font size
//        font2 = new TrueTypeFont(awtFont2, false);
        g.setFont(font2);
        g.drawImage(ImageRes.getSpriteImage("ui",0,0),0,0);
        g.drawImage(ImageRes.getSpriteImage("entity_icons",0,0),970,30);
        g.drawString("ZOMBIES:", 1030, 30);
        g.drawString(" "+Entities_P.zombies.size(), 1030, 50);
        g.drawString("HUMANS:", 1030, 80);
        g.drawString(" "+Entities_P.humans.size(), 1030, 100);
        g.drawString("TRANSMISSION CHARGE: ",1030,130);
        g.drawString(" "+EventHandler.deployCharge,1030,150);
        g.drawString("NEXT DISEASE TRANSMISSION:",990,200);
        if(EventHandler.zombieType==0)
            g.drawString("MELEE",990,220);
        if(EventHandler.zombieType==1)
            g.drawString("AIRBORNE",990,220);
        if(EventHandler.zombieType==2)
            g.drawString("SPECIAL",990,220);
        
        if(EventHandler.zombieDeployed){
        	Test_R.Shade.getScaledCopy(960, 800).draw(0, 0, new Color(1,1,1, (float)(((Timer%10000)/1000))/10));
        }
        if(game_over){
//            awtFont2 = awtFont2.deriveFont(50f); // set font size
//            font2 = new TrueTypeFont(awtFont2, false);
        	g.setFont(font2);
        	g.drawString("GAME OVER",Window_C.SIZE_W/2-150,350);
        	g.drawString("DAYS SURVIVED: "+EventHandler.DAY,Window_C.SIZE_W/2-130,390);
        }
        else{
            g.drawString("NEXT EVENT: "+EventHandler.eventName, Window_C.SIZE_W/2-100, 5);
            g.drawString("DAY "+EventHandler.DAY, Window_C.SIZE_W/2-100, 20);
            g.drawString(EventHandler.eventDescription, Window_C.SIZE_W/2-50, 750);
            if(!EventHandler.zombieDeployed)
            	g.drawString("DEPLOY YOUR ZOMBIES",Window_C.SIZE_W/2-150,720);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sb, int i) throws SlickException {
//        System.out.println(Timer/1000);
        
    	if(!game_over){
	        Entities_P.update_doodad(i);
	    	Entities_P.update_zombie(i);
	        Entities_P.update_human(i);
	        Entities_P.update_effects(i);
	        
	        if(EventHandler.zombieDeployed && Timer/1000 > 10){
	        	Timer = 0;
	        	EventHandler.exec_event();
	        }
	        else if(EventHandler.zombieDeployed){
	        	Timer+=i;
	        }
    	}
        if(Entities_P.zombies.size()==0 && EventHandler.DAY>0){
        	game_over = true;
        }
        
    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount){
    	Zombie_List.update(x,y);
    }

    @Override
    public void mouseDragged(int oldx, int oldy, int newx, int newy){
        System.out.println(Thread.currentThread().getStackTrace()[1]);
        //Entities_P.add_zombie(new Zombie_Entity(newx, newy));
    }

    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy){
        
    }
    
    @Override
    public void mousePressed(int button, int x, int y){
//    	System.out.println(Thread.currentThread().getStackTrace()[1]);
    	if(Test_V.isHovered == false){
    		if(button==0) {
    			boolean validClick = true;
    			for(int i=0;i<Entities_P.humans.size();i++){
    				int hX = Entities_P.humans.get(i).getX();
    				int hY = Entities_P.humans.get(i).getY();
    				if((x+35>=hX && x-35<=hX) && (y+40>=hY && y-40<=hY)){
    					validClick = false;
    					break;
    				}
    			}
    			if(validClick){
	    			EventHandler.zombieDeployed = true;
	    			if(EventHandler.deployCharge>0){
	    			    if(EventHandler.zombieType==0)
    	    				Entities_P.add_zombie(new Zombie_Entity2(x, y));
                        if(EventHandler.zombieType==1)
                            Entities_P.add_zombie(new Zombie_Entity(x, y));
                        if(EventHandler.zombieType==2)
                            Entities_P.add_zombie(new Zombie_Entity3(x, y));
	    				EventHandler.deployCharge--;
	    				EventHandler.genZombie();
	    			}
    				if(x < 960){
		    			EventHandler.zombieDeployed = true;
		    			if(EventHandler.deployCharge>0){
		    				Entities_P.add_effects(new Effects_entity(x-32, y-32, 1));
		    				Sound_P.Play(Sound_C.CHICKS_ID);
		    				EventHandler.deployCharge--;
		    			}
    				}
    			}
            }
            if(button==1){
            	if(EventHandler.zombieDeployed)
            		EventHandler.exec_event();
            }
    	}
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
