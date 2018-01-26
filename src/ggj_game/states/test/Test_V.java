package ggj_game.states.test;

import ggj_game.utils.game_map.MapParser;

/**
 * Created by Clientrace on 26/01/2018.
 */
public class Test_V {
    public static void initialize(){
        MapParser.load("res/maps/main_map.json");
        System.out.println(MapParser.mapSolids);
    }
}
