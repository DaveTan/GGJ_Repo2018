package ggj_game.entities;

import java.util.ArrayList;

public class Entities_P {
	public static ArrayList<Entity> entList;
	public static int entCount = 0;
	
	public static void initialize(){
		entList = new ArrayList<Entity>();
	}
	
	public static void add(Entity ent){
		entList.add(ent);
	}
	
	public static void update(int i){
		for(int a=0; a<entList.size(); a++){
			entList.get(a).update(i);
		}
	}
	
	public static void draw(){
		for(int a=0; a<entList.size(); a++){
			entList.get(a).render();
		}
	}
	
	public static void delete(int ID){
		for(int a=0; a<entList.size(); a++){
			if( entList.get(a).getID() == ID ){
				entList.remove(a);
				break;
			}
		}
	}
}
