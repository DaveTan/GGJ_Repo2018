package ggj_game.entities;

import java.util.ArrayList;

import ggj_game.animations.Animation_P;
import ggj_game.states.menu.Menu_R;
import ggj_game.states.menu.Menu_V;
import ggj_game.utils.game_map.GameMap;
import ggj_game.utils.game_map.MapParser;
import ggj_game.utils.pathfinder.AStar;
import ggj_game.utils.pathfinder.GMap;
import ggj_game.utils.pathfinder.Path;

import org.newdawn.slick.Animation;

public class Zombie_Entity extends Entity{
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
    private int destination;
    private int destDist;
    private boolean destinationSet;

	public Zombie_Entity(int x, int y) {
		super(x, y);
	}

	@Override
	public void initialize(int x, int y) {
		animationStates = new ArrayList<Animation>();

		animationStates.add(Animation_P.LoadAnimation(Menu_R.Human, Test_Entity_C.IDLE_LEFT, 2, 250));
//		animationStates.add(Animation_P.LoadAnimation(Menu_R.Test, Test_Entity_C.WALK_LEFT, 6, 250));
//		animationStates.add(Animation_P.LoadAnimation(Menu_R.Test, Test_Entity_C.IDLE_RIGHT, 10, 250));
//		animationStates.add(Animation_P.LoadAnimation(Menu_R.Test, Test_Entity_C.WALK_RIGHT, 6, 250));

		for(int a=0; a<animationStates.size();a++){
			animationStates.get(a).start();
		}

		destinationSet = false;

		currentState = Test_Entity_C.INITIAL_STATE;

		worldX = x;
        worldY = y;
        mapX = x/GameMap.TileSize;
        mapY = y/GameMap.TileSize;
        range = 100;
        gMap = new GMap(MapParser.WIDTH,MapParser.HEIGHT);
        pathFinder = new AStar(gMap,range,false);

        setDest(5,5);
	}

	@Override
	public void render() {
		animationStates.get(currentState).draw(worldX, worldY-32);
	}

	@Override
	public void update(int i) {
		for(int a=0;a<animationStates.size();a++){
			animationStates.get(a).update(i);
		}

        updatePos(destX,destY);
        if(worldX==destX && worldX==destY){
            //EXPLOSION
        }
	}

	public void updatePos(int destX, int destY){
        pathFinder = new AStar(gMap,range,false);
        path = new Path();
        mapX = worldX/ GameMap.TileSize;
        mapY = worldY/ GameMap.TileSize;

        path = pathFinder.findPath(mapX,mapY,destX,destY);
        gMap.clearVisited();
        if(destinationSet){
            destDist--;
            if(destination==0)
                worldX -= speed;
            if(destination==1)
                worldX += speed;
            if(destination==2)
                worldY -= speed;
            if(destination==3)
                worldY += speed;

            if(destDist==0)
                destinationSet = false;
        }
        else if(path!=null){
            if(path.contains(mapX-1,mapY)) {
                if(!Entities_P.isPosOccupied(worldX,worldY,0)) {
                    destinationSet = true;
                    destination = 0;
                    destDist = 32 - worldX%32;
                }
            }
            else if(path.contains(mapX+1,mapY)) {
                if(!Entities_P.isPosOccupied(worldX,worldY,1)) {
                    destinationSet = true;
                    destination = 1;
                    destDist = 32 -worldX%32;
                }
            }
            else if(path.contains(mapX,mapY-1)) {
                if(!Entities_P.isPosOccupied(worldX,worldY,2)) {
                    destinationSet = true;
                    destination = 2;
                    destDist = 32 -worldX%32;
                }
            }
            else if(path.contains(mapX,mapY+1)) {
                if(!Entities_P.isPosOccupied(worldX,worldY,3)) {
                    destinationSet = true;
                    worldY += speed;
                    destination = 3;
                    destDist = 32 -worldX%32;
                }
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
