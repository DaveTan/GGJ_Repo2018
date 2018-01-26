package ggj_game.states.test;

import ggj_game.utils.game_map.GameMap;

/**
 * Created by Clientrace on 26/01/2018.
 */
public class Test_V {
    public static int TILE_SIZE = 32;
    public static GameMap gameMap1;
    

    public static void initialize(){
        gameMap1 = new GameMap("res/maps/main_map.json",TILE_SIZE);
    }
}
