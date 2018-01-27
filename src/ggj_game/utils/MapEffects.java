package ggj_game.utils;

import ggj_game.entities.Mine;
import ggj_game.utils.game_map.GameMap;
import ggj_game.utils.game_map.MapParser;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Clientrace on 27/01/2018.
 */
public class MapEffects {
    public static ArrayList<Mine> mines;
    private static Random random = new Random();
    private static int dir;
    private static int dur = 15;
    private static int ticks;
    private static int range;
    public static boolean vibrate;

    public static void vibrate(Graphics g, int intensity){
        range = random.nextInt(intensity);
        dir = random.nextInt(8);
        if(dir == 0){
            g.translate(0,-range);
        }
        if(dir == 1){
            g.translate(0,range);
        }
        if(dir == 2){
            g.translate(-range,0);
        }
        if(dir == 3){
            g.translate(range,0);
        }
        if(dir == 4){
            g.translate(0,range);
        }
        if(dir == 5){
            g.translate(range,0);
        }
        if(dir == 6){
            g.translate(range,range);
        }
        if(dir == 7){
            g.translate(-range,-range);
        }
        ticks++;
        if(ticks>dur){
            ticks = 0;
            vibrate = false;
        }
    }

    public static void generateMines(int mineNum){
        mines = new ArrayList<>();
        for(int i=0;i<mineNum;i++){
            while(true) {
                int mineX = random.nextInt(960);
                int mineY = random.nextInt(800);
                if (GameMap.binaryMap[mineX/32][mineY/32] == 0){
                    mines.add(new Mine(mineX,mineY));
                    break;
                }
            }
        }
    }

}
