package ggj_game.utils;

import ggj_game.utils.game_map.GameMap;
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
    private int mapX;
    private int mapY;
    private int worldX;
    private int worldY;
    private int ticks = 0;
    private int speed = 1;
    private int destX = 0;
    private int destY = 0;

    public Entity(){
        worldX = 0;
        worldY = 0;
        mapX = 0;
        mapY = 0;
        range = 100;
        gMap = new GMap(MapParser.WIDTH,MapParser.HEIGHT);
        pathFinder = new AStar(gMap,range,false);
    }

    public void update(){
        updatePos(destX,destY);
    }

    public void updatePos(int destX, int destY){
        pathFinder = new AStar(gMap,range,false);
        path = new Path();
        mapX = worldX/ GameMap.TileSize;
        mapY = worldY/ GameMap.TileSize;

        path = pathFinder.findPath(mapX,mapY,destX,destY);
        gMap.clearVisited();
        if(path!=null){
            System.out.println("yeah");
            if(path.contains(mapX-1,mapY)) {
                worldX-=speed;
                System.out.println("1 FOUND");
            }
            else if(path.contains(mapX+1,mapY)) {
                worldX+=speed;
                System.out.println("2 FOUND");
            }
            else if(path.contains(mapX,mapY-1)) {
                worldY-=speed;
                System.out.println("3 FOUND");
            }
            else if(path.contains(mapX,mapY+1)) {
                worldY+=speed;
                System.out.println("4 FOUND");
            }
        }
    }

    public void setDest(int x, int y){
        destX = x;
        destY = y;
    }

    public int getX(){
        return worldX;
    }

    public int getY(){
        return worldY;
    }
}
