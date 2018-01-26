package ggj_game.utils;

import ggj_game.utils.game_map.MapParser;
import ggj_game.utils.pathfinder.AStar;
import ggj_game.utils.pathfinder.GMap;
import ggj_game.utils.pathfinder.Path;

/**
 * Created by Clientrace on 27/01/2018.
 */
public class Entity {
    private GMap gMap;
    private AStar pathFinder;
    private Path path;
    private int range;
    private int x;
    private int y;

    public Entity(){
        x = 0;
        y = 0;
        range = 100;
        gMap = new GMap(MapParser.WIDTH,MapParser.HEIGHT);
        pathFinder = new AStar(gMap,range,false);
    }

    public void updatePos(int destX, int destY){
        pathFinder = new AStar(gMap,range,false);
        path = new Path();
        path = pathFinder.findPath(x,y,destX,destY);
        gMap.clearVisited();
        if(path!=null){
            System.out.println("yeah");
            if(path.contains(x-1,y)) {
                x--;
                System.out.println("1 FOUND");
            }
            else if(path.contains(x+1,y)) {
                x++;
                System.out.println("2 FOUND");
            }
            else if(path.contains(x,y-1)) {
                y--;
                System.out.println("3 FOUND");
            }
            else if(path.contains(x,y+1)) {
                y++;
                System.out.println("4 FOUND");
            }
        }
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
