package ggj_game.states.test.UI;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import ggj_game.utils.ImageRes;
import ggj_game.window.Window_C;

public class Side_Upgrades_List {
	public static ArrayList<Upgrade> upgrades;
	public static void initSideMenu() {
		upgrades = new ArrayList<>();
		upgrades.add(new Upgrade());
		upgrades.add(new Upgrade());
		upgrades.add(new Upgrade());
		upgrades.add(new Upgrade());
	}
    public static void render(Graphics g){
        int upgradeY = (int) (Window_C.SIZE_H * .75);
        for(int i=0;i<upgrades.size();i++){
            if(upgrades.get(i).isSelected())
                upgradeY = upgradeY - 20;
            else
                upgradeY = (int) (Window_C.SIZE_H * .75);

            upgrades.get(i).setX((110*i)+50);
            upgrades.get(i).setY(upgradeY);

            g.drawImage(ImageRes.getSpriteImage("nodes",0,0),(110*i)+50,upgradeY);
        }
    }
	public static void update(int mx, int my){
        for(int i=0;i<upgrades.size();i++){
            if(upgrades.get(i).checkMouseTouch(mx,my))
                upgrades.get(i).touch();
            else
                upgrades.get(i).untouch();

        }
    }
}
