package ggj_game.entities;

import ggj_game.animations.HumanRifle;
import ggj_game.utils.ImageRes;
import ggj_game.utils.game_map.GameMap;
import ggj_game.utils.game_map.MapParser;
import ggj_game.utils.pathfinder.AStar;
import ggj_game.utils.pathfinder.GMap;
import ggj_game.utils.pathfinder.Path;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Clientrace on 27/01/2018.
 */
public class Human_Entity extends Entity{
    ArrayList<Animation> animationStates;
    
    public static final int STATE_WALKING_RIGHT = 0;
    public static final int STATE_WALKING_LEFT = 1;
    public static final int STATE_ATTACKING_RIGHT = 2;
    public static final int STATE_ATTACKING_LEFT = 3;
    
    int currentState = 1;

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
    private Random rand;
    private int atkSpeed = 1;

    public Human_Entity(int x, int y) {
        super(x, y, Entities_P.entCount++);
    }

    @Override
    public void initialize(int x, int y, int ID) {
    	this.ID = ID;
    	rand = new Random();
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
    public void render(Graphics g) {
        if(currentState == STATE_WALKING_RIGHT){
    		animationStates.get(0).draw(worldX, worldY, 32, 32);
    	}
    	else if(currentState == STATE_WALKING_LEFT){
    		animationStates.get(0).draw(worldX, worldY, -32, 32);
    	}
    	else if(currentState == STATE_ATTACKING_RIGHT){
    		animationStates.get(1).draw(worldX, worldY, 32, 32);
    	}
    	else if(currentState == STATE_ATTACKING_LEFT){
    		animationStates.get(1).draw(worldX, worldY, -32, 32);
    	}
    	
    }

    @Override
    public void update(int i) {
        ticks++;
        if(ticks>atkSpeed) {
            ticks = 0;
            for (int a = 0; a < Entities_P.zombies.size(); a++) {
                int zombieX = Entities_P.zombies.get(a).getX();
                int zombieY = Entities_P.zombies.get(a).getY();
                double dist = Math.sqrt(Math.pow((zombieX - worldX), 2) + Math.pow((zombieY - worldY), 2));
                if (dist <= 100) {
                    // PLAY ATTACK ANIMATION HERE
                    if(zombieX>worldX)
                        currentState = STATE_ATTACKING_LEFT;
                    if(zombieX>worldX)
                        currentState = STATE_ATTACKING_RIGHT;
                    int randShot = rand.nextInt(100);
                    if (randShot >= 0 && randShot <= 5)
                        Entities_P.delete(Entities_P.zombies.get(a).getID(), 1);
                }
            }
        }

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


