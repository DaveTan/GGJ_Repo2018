package ggj_game.utils;

import ggj_game.entities.Entities_P;
import ggj_game.entities.Human_Entity;
import ggj_game.entities.Mine;
import ggj_game.utils.game_map.GameMap;

import java.util.ArrayList;
import java.util.Random;

public class EventHandler {
	
	public static int humanAccuracy;
	public static int eventNum;
    public static ArrayList<Mine> mines;
    private static Random random;
    public static String eventName;
    public static String eventDescription;
    public static int DAY;
    public static boolean zombieDeployed;
    public static int deployCharge;
    public static int zombieType;
    
    public static void init(){
    	mines = new ArrayList<Mine>();
    	random  = new Random();
    	humanAccuracy = 5;
    	eventNum = 0;
    	eventDescription = "Humans Will Arrive Soon...";
    	eventName = getEventName(eventNum);
    	zombieDeployed = false;
    	DAY = 0;
    	zombieType = 0;
    	deployCharge = 5;
    }
	
	public static void exec_event(){
		humanAccuracy = 5;
		eventDescription = getEventDescription(eventNum);
		if(eventNum==0){
			//SPAWN HUMAN
			spawnHuman(5);
		}
		else if(eventNum==1){
			//PANIC
			rally();
		}
		else if(eventNum==2){
			//STEROIDS
			humanAccuracy = 55;
		}
		else if(eventNum==3){
			//MINES
	        EventHandler.generateMines(25);
		}
		else if(eventNum==4){
			//HUMAN BARRAGE
			spawnHumanRandom(15+DAY);
		}
		DAY++;
		int randomEvent = random.nextInt(5);
		if(randomEvent==1){
			if(Entities_P.zombies.size()==0)
				randomEvent = 0;
		}
		if(randomEvent==2){
			if(eventNum==2 || Entities_P.humans.size()==0)
				randomEvent = 0;
		}
		eventNum = randomEvent;
		eventName = getEventName(eventNum);
		deployCharge = deployCharge+1+random.nextInt(5);
		zombieDeployed = false;
	}

	public static void genZombie(){
		int randZombie = random.nextInt(100);
		if(randZombie>=0&&randZombie<=50)
			zombieType = 0;
		if(randZombie>=50&&randZombie<=80)
			zombieType = 1;
		if(randZombie>=80&&randZombie<=99)
			zombieType = 2;
	}
	
	public static String getEventName(int num){
		String name = "";
		if(num==0)
			name = "Human's Arrival";
		
		if(num==1)
			name = "Panic!";
		
		if(num==2)
			name = "Human's on Steroid";
		
		if(num==3)
			name = "Mines!";
		
		if(num==4)
			name = "Human Barrage!";
		
		return name;
	}
	
	public static String getEventDescription(int num){
		String eventDescription = "";
		if(num==0)
			eventDescription = "\"Humans have arrived!\"";
		
		if(num==1)
			eventDescription = "\"The Humans are hunting zombies!\"";
		
		if(num==2)
			eventDescription = "\"They are too strong today...\"";
		
		if(num==3)
			eventDescription = "\"Mines! Mines everywhere!\"";
		
		if(num==4)
			eventDescription = "\"They are everywhere!\"";
		
		return eventDescription;
	}
	
	public static void spawnHumanRandom(int num){
		Random rand = new Random();
		int count = 0;
		while(true){
			int x = rand.nextInt(960);
			int y = rand.nextInt(800);
			int mapX = x/32;
			int mapY = y/32;
			if(GameMap.binaryMap[mapX][mapY]==0){
				Entities_P.add_human(new Human_Entity(x,y));
				count++;
			}
			if(count==num)
				break;
		}
	}
	
	public static void spawnHuman(int num){
		Random rand = new Random();
		int count = 0;
		while(true){
			int x = rand.nextInt(960);
			int y = rand.nextInt(800);
			int mapX = x/32;
			int mapY = y/32;
			if(GameMap.binaryMap[mapX][mapY]==2){
				Entities_P.add_human(new Human_Entity(x,y));
				count++;
			}
			if(count==num)
				break;
		}
	}
	
	public static void rally(){
		Human_Entity.event_rally = true;
		Human_Entity.event_rally_x = Entities_P.zombies.get(0).getX()/32;
		Human_Entity.event_rally_y =  Entities_P.zombies.get(0).getY()/32;
	}
	
    public static void generateMines(int mineNum){
        mines = new ArrayList<>();
        for(int i=0;i<mineNum;i++){
            while(true) {
                int mineX = random.nextInt(960);
                int mineY = random.nextInt(800);
                if (GameMap.binaryMap[mineX/32][mineY/32] == 0){
                    mines.add(new Mine(mineX,mineY));
                    break;
                }
            }
        }
    }
}
