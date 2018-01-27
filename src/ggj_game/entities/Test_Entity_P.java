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

public class Test_Entity_P extends Entity{
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
	
	public Test_Entity_P(int x, int y) {
		super(x, y);
	}
	
	@Override
	public void initialize(int x, int y) {
		animationStates = new ArrayList<Animation>();
		
		animationStates.add(Animation_P.LoadAnimation(Menu_R.Human, Test_Entity_C.IDLE_LEFT, 5, 250));
//		animationStates.add(Animation_P.LoadAnimation(Menu_R.Test, Test_Entity_C.WALK_LEFT, 6, 250));
//		animationStates.add(Animation_P.LoadAnimation(Menu_R.Test, Test_Entity_C.IDLE_RIGHT, 10, 250));
//		animationStates.add(Animation_P.LoadAnimation(Menu_R.Test, Test_Entity_C.WALK_RIGHT, 6, 250));
		
		for(int a=0; a<animationStates.size();a++){
			animationStates.get(a).start();
		}
		
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
		animationStates.get(currentState).draw(worldX, worldY);
	}

	@Override
	public void update(int i) {
		for(int a=0;a<animationStates.size();a++){
			animationStates.get(a).update(i);
		}
		
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
//                System.out.println("1 FOUND");
            }
            else if(path.contains(mapX+1,mapY)) {
                worldX+=speed;
//                System.out.println("2 FOUND");
            }
            else if(path.contains(mapX,mapY-1)) {
                worldY-=speed;
//                System.out.println("3 FOUND");
            }
            else if(path.contains(mapX,mapY+1)) {
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
}
