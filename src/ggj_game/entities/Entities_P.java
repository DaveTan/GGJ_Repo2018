package ggj_game.entities;

import java.util.ArrayList;

public class Entities_P {
	public static ArrayList<Entity> entList;
	
	public static void initialize(){
		entList = new ArrayList<Entity>();
	}
	
	public static void add(Entity ent){
		entList.add(ent);
	}
	
	public static void update(){
		for(int a=0; a<entList.size(); a++){
			entList.get(a).update();
		}
	}
	
	public static void draw(){
		for(int a=0; a<entList.size(); a++){
			entList.get(a).render();
		}
	}
}
