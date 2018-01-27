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

	public static boolean isPosOccupied(int x, int y, int dir){
		if(dir==0){
			for(int i=0;i<entList.size();i++){
				int pos = x - 20;
				if(entList.get(i).getX()==pos && Math.abs(y-entList.get(i).getY())<10){
					return true;
				}
			}
		}
		if(dir==1){
			for(int i=0;i<entList.size();i++){
				int pos = x + 20;
				if(entList.get(i).getX()==pos && Math.abs(y-entList.get(i).getY())<10){
					return true;
				}
			}
		}
		if(dir==2){
			for(int i=0;i<entList.size();i++){
				int pos = y - 20;
				if(entList.get(i).getY()==pos && Math.abs(x-entList.get(i).getX())<10){
					return true;
				}
			}
		}
		if(dir==3){
			for(int i=0;i<entList.size();i++){
				int pos = y + 20;
				if(entList.get(i).getY()==pos && Math.abs(x-entList.get(i).getX())<10){
					return true;
				}
			}
		}
		return false;
	}
}
