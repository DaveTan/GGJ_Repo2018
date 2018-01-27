package ggj_game.entities;

import ggj_game.animations.HumanMelee;
import ggj_game.animations.HumanRifle;
import ggj_game.states.menu.Menu_R;
import ggj_game.utils.game_map.GameMap;
import ggj_game.utils.game_map.MapParser;
import ggj_game.utils.pathfinder.AStar;
import ggj_game.utils.pathfinder.GMap;
import ggj_game.utils.pathfinder.Path;

import org.newdawn.slick.Animation;

import java.util.ArrayList;

/**
 * Created by Clientrace on 27/01/2018.
 */
public class Human_Entity extends Entity{
    ArrayList<Animation> animationStates;
    int currentState;

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
    private int type = 0;
    private int ID;

    public Human_Entity(int x, int y) {
        super(x, y, Entities_P.entCount++);
    }

    @Override
    public void initialize(int x, int y, int ID) {
    	this.ID = ID;
        animationStates = HumanRifle.get();
        for(int a=0; a<animationStates.size();a++){
            animationStates.get(a).start();
        }

        currentState = Test_Entity_C.INITIAL_STATE;

        worldX = x;
        worldY = y;
        mapX = x/ GameMap.TileSize;
        mapY = y/GameMap.TileSize;
        range = 100;
        gMap = new GMap(MapParser.WIDTH,MapParser.HEIGHT);
        pathFinder = new AStar(gMap,range,false);

        setDest(5,5);
    }

    @Override
    public void render() {
        animationStates.get(currentState).draw(worldX, worldY);
    }

    @Override
    public void update(int i) {
        for(int a=0;a<animationStates.size();a++){
            animationStates.get(a).update(i);
        }

    }

    public void updatePos(int destX, int destY){
        pathFinder = new AStar(gMap,range,false);
        path = new Path();
        mapX = worldX/ GameMap.TileSize;
        mapY = worldY/ GameMap.TileSize;

        path = pathFinder.findPath(mapX,mapY,destX,destY);
        gMap.clearVisited();
        if(path!=null){
            if(path.contains(mapX-1,mapY)) {
                if(!Entities_P.isPosOccupied(worldX,worldY,0))
                    worldX-=speed;
//                System.out.println("1 FOUND");
            }
            else if(path.contains(mapX+1,mapY)) {
                if(!Entities_P.isPosOccupied(worldX,worldY,1))
                    worldX+=speed;
//                System.out.println("2 FOUND");
            }
            else if(path.contains(mapX,mapY-1)) {
                if(!Entities_P.isPosOccupied(worldX,worldY,2))
                    worldY-=speed;
//                System.out.println("3 FOUND");
            }
            else if(path.contains(mapX,mapY+1)) {
                if(!Entities_P.isPosOccupied(worldX,worldY,3))
                    worldY+=speed;
//                System.out.println("4 FOUND");
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

	@Override
	public int getID() {
		return this.ID;
	}
}


