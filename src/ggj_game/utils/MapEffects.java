package ggj_game.utils;

import org.newdawn.slick.Graphics;
import java.util.Random;

/**
 * Created by Clientrace on 27/01/2018.
 */
public class MapEffects {
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

}
