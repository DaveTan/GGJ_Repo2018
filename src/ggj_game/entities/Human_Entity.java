package ggj_game.entities;

import ggj_game.animations.HumanRifle;
import ggj_game.sound.Sound_C;
import ggj_game.sound.Sound_P;
import ggj_game.utils.EventHandler;
import ggj_game.utils.ImageRes;
import ggj_game.utils.game_map.GameMap;
import ggj_game.utils.game_map.MapParser;
import ggj_game.utils.pathfinder.AStar;
import ggj_game.utils.pathfinder.GMap;
import ggj_game.utils.pathfinder.Path;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Sound;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Clientrace on 27/01/2018.
 */
public class Human_Entity extends Entity{
    ArrayList<Animation> animationStates;
    
    Sound Gunshot;
    
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
    private int speed ;
    private int destX = 0;
    private int destY = 0;
    private int type = 0;
    private int ID;
    private Random rand;
    private int atkSpeed = 10;
    private boolean move;
    private int rallyX;
    private int rallyY;
    private boolean isIDLE;
    public static boolean event_rally;
    public static int event_rally_x;
    public static int event_rally_y;

    public Human_Entity(int x, int y) {
        super(x, y, Entities_P.entCount++);
    }

    @Override
    public void initialize(int x, int y, int ID) {
    	Gunshot = Sound_P.SoundList.get(Sound_C.SMG_ID);
    	this.ID = ID;
    	rand = new Random();
        animationStates = HumanRifle.get();
        for(int a=0; a<animationStates.size();a++){
            animationStates.get(a).start();
        }

        currentState = Test_Entity_C.INITIAL_STATE;
        move = false;
        isIDLE = false;
        speed = 1;
        worldX = x;
        worldY = y;
        event_rally = false;
        rallyX = worldX;
        rallyY = worldY;
        mapX = x/ GameMap.TileSize;
        mapY = y/GameMap.TileSize;
        range = 100;
        gMap = new GMap(MapParser.WIDTH,MapParser.HEIGHT);
        pathFinder = new AStar(gMap,range,false);

    }

    @Override
    public void render(Graphics g) {
        if(currentState == STATE_WALKING_RIGHT){
    		animationStates.get(0).draw(worldX-16, worldY-10, 32, 32);
    	}
    	else if(currentState == STATE_WALKING_LEFT){
    		animationStates.get(0).draw(worldX+16, worldY-10, -32, 32);
    	}
    	else if(currentState == STATE_ATTACKING_RIGHT){
    		animationStates.get(1).draw(worldX-16, worldY-10, 32, 32);
    	}
    	else if(currentState == STATE_ATTACKING_LEFT){
    		animationStates.get(1).draw(worldX+16, worldY-10, -32, 32);
    	}
    }

    @Override
    public void update(int i) {
        ticks++;
        if(event_rally){
        	setDest(event_rally_x,event_rally_y);
        	updatePos(destX,destY);
        	move = false;
        }
        if(ticks>atkSpeed) {
            ticks = 0;

            for (int a = 0; a < Entities_P.zombies.size(); a++) {
                int zombieX = Entities_P.zombies.get(a).getX();
                int zombieY = Entities_P.zombies.get(a).getY();
                double dist = Math.sqrt(Math.pow((zombieX - worldX), 2) + Math.pow((zombieY - worldY), 2));
                if (dist <= 100) {
                    // PLAY ATTACK ANIMATION HERE
                	Gunshot.play();
                    if(zombieX<worldX)
                        currentState = STATE_ATTACKING_LEFT;
                    if(zombieX>worldX)
                        currentState = STATE_ATTACKING_RIGHT;
                    int randShot = rand.nextInt(100);
                    if (randShot >= 0 && randShot <= EventHandler.humanAccuracy){
                    	Sound_P.Play(Sound_C.IDLE_ZOMBIE_ID);
                    	Entities_P.doodads.add(new Doodads_Entity(Entities_P.zombies.get(a).getX(), Entities_P.zombies.get(a).getY(), 3, 2));
                        Entities_P.delete(Entities_P.zombies.get(a).getID(), 1);
                    }
                    move = false;
                    isIDLE = true;
                }
                else
                    move = true;
            }
        }
        else
            move = true;

        for(int a=0;a<animationStates.size();a++){
            animationStates.get(a).update(i);
        }
        if(move) {
            int mapMinX = rallyX - (rallyX % 32);
            int mapMinY = rallyY - (rallyY % 32);
            int mapMaxX = mapMinX + 12;
            int mapMaxY = mapMinY + 12;

            int dir = rand.nextInt(4);
            if (dir == 0) {
                if (worldX < mapMaxX - speed)
                    worldX += speed;
            }
            if (dir == 1) {
                if (worldX > mapMinX + speed)
                    worldX -= speed;
            }
            if (dir == 2) {
                if (worldY < mapMaxY - speed)
                    worldY += speed;
            }
            if (dir == 3) {
                if (worldY > mapMinY + speed)
                    worldY -= speed;
            }
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


