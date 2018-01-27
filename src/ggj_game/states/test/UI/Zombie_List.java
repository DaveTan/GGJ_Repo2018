package ggj_game.states.test.UI;

import ggj_game.states.test.Test_V;
import ggj_game.utils.ImageRes;
import ggj_game.window.Window_C;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import java.util.ArrayList;

/**
 * Created by Clientrace on 27/01/2018.
 */
public class Zombie_List {
    private static ArrayList<Card> cards;

    public static final int SELECTION_MELEE = 0;
    public static final int SELECTION_AIR = 1;
    
    public static int Selection;
    
    public static void initCards(){
        cards = new ArrayList<>();
        cards.add(new Card());
        cards.add(new Card());
    }

    public static void render(Graphics g){
        int cardY = (int) (Window_C.SIZE_H * .75);
        Test_V.isHovered = false;
        
        for(int i=0;i<cards.size();i++){
        	
            if(cards.get(i).isSelected() || i == Test_V.Selection){
//            	Test_V.isHovered |= true;
//            	Test_V.Selection = i;
            	cardY = cardY - 20;
            }
            else{
//            	Test_V.isHovered |= false;
            	cardY = (int) (Window_C.SIZE_H * .75);
            }
            cards.get(i).setX((110*i)+50);
            cards.get(i).setY(cardY);

            g.drawImage(ImageRes.getSpriteImage("cards",0,0),(110*i)+50,cardY);
        }
    }

    public static void update(int mx, int my){
        for(int i=0;i<cards.size();i++){
            if(cards.get(i).checkMouseTouch(mx,my))
                cards.get(i).touch();
            else
                cards.get(i).untouch();

        }
    }

}
