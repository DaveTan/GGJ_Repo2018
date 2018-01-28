package ggj_game.entities;

import java.util.ArrayList;

import ggj_game.animations.Explosion;
import ggj_game.animations.HumanRifle;
import ggj_game.animations.ZombieContact;
import ggj_game.animations.ZombieMelee;
import ggj_game.sound.Sound_C;
import ggj_game.sound.Sound_P;
import ggj_game.sound.Sound_S;
import ggj_game.states.menu.Menu_R;
import ggj_game.utils.EventHandler;
import ggj_game.utils.ImageRes;
import ggj_game.utils.MapEffects;
import ggj_game.utils.game_map.GameMap;
import ggj_game.utils.game_map.MapParser;
import ggj_game.utils.pathfinder.AStar;
import ggj_game.utils.pathfinder.GMap;
import ggj_game.utils.pathfinder.Map;
import ggj_game.utils.pathfinder.Path;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Sound;

import java.util.Random;

public class Zombie_Entity extends Entity{
	ArrayList<Animation> animationStates;
	Sound Explosion;
	Sound Summon;
	int currentState;

	private int ID;
	private int moveState;
	private GMap gMap;
    private AStar pathFinder;
    private Path path;
    private int range;
    private int mapX;
    private int mapY;
    private int worldX;
    private int worldY;
    private int speed;
    private int destX;
    private int destY;
    private int type;
    private int destination;
    private int destDist;
    private int rallyX;
    private int rallyY;
    private boolean destinationSet;
    private Random random;

    private boolean isIDLE;

	public Zombie_Entity(int x, int y) {
		super(x, y, Entities_P.entCount++);
	}

	@Override
	public void initialize(int x, int y, int ID) {
		Explosion = Sound_P.SoundList.get(Sound_C.DIARRHEA_ID);
		Summon = Sound_P.SoundList.get(Sound_C.IDLE_ZOMBIE_ID);
		Summon.play();
		this.ID = ID;
		random = new Random();
		this.type = ZombieContact.Type;
		animationStates = ZombieContact.get();
		for(int a=0; a<animationStates.size();a++){
			animationStates.get(a).start();
		}

		destinationSet = false;

		currentState = Test_Entity_C.INITIAL_STATE;

		speed = 1;
		isIDLE = false;

		worldX = x;
        worldY = y;
        rallyX = worldX;
        rallyY = worldY;
        mapX = x/GameMap.TileSize;
        mapY = y/GameMap.TileSize;
        range = 8;
        gMap = new GMap(MapParser.WIDTH,MapParser.HEIGHT);
        pathFinder = new AStar(gMap,range,false);
	}

	@Override
	public void render(Graphics g) {
		if(moveState == 0){
			animationStates.get(currentState).draw(worldX-16, worldY-16, 32, 32);
		}
		else if( moveState == 1 ){
			animationStates.get(currentState).draw(worldX+16, worldY-16, -32, 32);
		}
	}

	@Override
	public void update(int i) {
		for(int a=0;a<animationStates.size();a++){
			animationStates.get(a).update(i);
		}
		
		updatePos(destX,destY);

		if(Entities_P.humans.size()>0) {
            int nearest = getNearestHuman();
            int humanX = Entities_P.humans.get(nearest).getX()/32;
            int humanY = Entities_P.humans.get(nearest).getY()/32;
            setDest(humanX,humanY);
            updatePos(destX, destY);

            // CHECK FOR HUMAN CONTACT
            if((worldX+35>=destX*32 && worldX-35<=destX*32) && (worldY+40>=destY*32 && worldY-40<=destY*32)){

                int exp_x = worldX - 80;
                int exp_y = worldY - 60;
            	Entities_P.add_effects(new Effects_entity(exp_x, exp_y, 0));

            	for(int a=0;a<Entities_P.humans.size();a++){
            	    int hx = Entities_P.humans.get(a).getX();
            	    int hy = Entities_P.humans.get(a).getY();
            	    if(hx>=exp_x && hx<=exp_x+180 && hy>=exp_y && hy<=exp_y+120) {
            	    	Entities_P.doodads.add(new Doodads_Entity(Entities_P.humans.get(a).getX(), Entities_P.humans.get(a).getY(), 3, 1));
            	    	Explosion.play();
//            	    	Entities_P.effects.add(new Effects_entity(Entities_P.humans.get(a).getX(), Entities_P.humans.get(a).getY(), 3));
            	    	Entities_P.delete(Entities_P.humans.get(a).getID(), 3);
                    }
                }

            	Entities_P.delete(this.ID, 1);
                MapEffects.vibrate = true;
            }
        }

        // CHECK FOR MINE CONTACT
        int mineIndex = 0;
		boolean mineExploded = false;
        for(int a=0;a<EventHandler.mines.size();a++){
		    int mineX = EventHandler.mines.get(a).getX();
		    int mineY = EventHandler.mines.get(a).getY();

            if((worldX+15>=mineX && worldX-15<=mineX) && (worldY+20>=mineY && worldY-20<=mineY)){
                Entities_P.doodads.add(new Doodads_Entity(mineX-10, mineY-10, 3, 4));
                Entities_P.add_effects(new Effects_entity(mineX-80, mineY-60, 0));
                Entities_P.delete(this.ID, 1);
                MapEffects.vibrate = true;
                Explosion.play();
                mineExploded = true;
                mineIndex = a;
            }
        }
        if(mineExploded)
        	EventHandler.mines.remove(mineIndex);

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
	            if(destination==0){
	                worldX -= speed;
	                moveState = 0;
	            }
	            else if(destination==1){
	                worldX += speed;
	                moveState =1;
	            }
	            
	            if(destination==2)
	                worldY -= speed;
	            else if(destination==3)
	                worldY += speed;
	
	            if(destDist==0)
	                destinationSet = false;
	            rallyX = worldX;
	            rallyY = worldY;
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
            rallyX = worldX;
            rallyY = worldY;
        }
        else{
            isIDLE = true;
            int mapMinX = rallyX-(rallyX%32);
            int mapMinY = rallyY-(rallyY%32);
            int mapMaxX = mapMinX+32;
            int mapMaxY = mapMinY+32;

            int dir = random.nextInt(4);
            if(dir==0){
                if(worldX<mapMaxX-speed)
                    worldX+=speed;
            }
            if(dir==1){
                if(worldX>mapMinX+speed)
                    worldX-=speed;
            }
            if(dir==2){
                if(worldY<mapMaxY-speed)
                    worldY+=speed;
            }
            if(dir==3){
                if(worldY>mapMinY+speed)
                    worldY-=speed;
            }
        }
    }

    public int getNearestHuman(){
	    int index = 0;
	    double oldDist = 1000000;
        double dist;

	    for(int i = 0; i< Entities_P.humans.size(); i++){
	        int x1 = worldX;
	        int y1 = worldY;
	        int x2 = Entities_P.humans.get(i).getX();
	        int y2 = Entities_P.humans.get(i).getY();

	        dist = Math.sqrt(Math.pow((x2-x1),2) + Math.pow((y2-y1),2));
	        if(dist<=oldDist){
	            oldDist = dist;
	            index = i;
            }
	    }
        return index;
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
