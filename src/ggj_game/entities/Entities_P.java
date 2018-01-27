package ggj_game.entities;

import ggj_game.utils.ImageRes;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class Entities_P {
	public static ArrayList<Entity> entList;
	public static int entCount = 0;
	public static ArrayList<Entity> zombies;
	public static ArrayList<Entity> humans;
	public static ArrayList<Entity> effects;
	public static ArrayList<Entity> doodads;
	
	public static void initialize(){
		zombies = new ArrayList<Entity>();
		humans = new ArrayList<Entity>();
		effects = new ArrayList<Entity>();
		doodads = new ArrayList<Entity>();
	}
	
	public static void add_zombie(Entity ent){
		zombies.add(ent);
	}

	public static void add_human(Entity ent){
		humans.add(ent);
	}
	
	public static void add_effects(Entity ent){
		effects.add(ent);
	}
	
	public static void add_doodad(Entity ent){
		doodads.add(ent);
	}

	public static void update_zombie(int i){
		for(int a = 0; a< zombies.size(); a++){
			zombies.get(a).update(i);
		}
	}

	public static void update_human(int i){
		for(int a = 0; a< humans.size(); a++){	
			humans.get(a).update(i);
		}
	}
	
	public static void update_effects(int i){
		for(int a = 0; a< effects.size(); a++){	
			effects.get(a).update(i);
		}
	}
	
	public static void update_doodad(int i){
		for(int a = 0; a< doodads.size(); a++){	
			doodads.get(a).update(i);
		}
	}
	
	public static void draw_zombie(Graphics g){
		for(int a = 0; a<zombies.size(); a++){
			int zombieX = zombies.get(a).getX();
			int zombieY = zombies.get(a).getY();
			g.drawImage(ImageRes.getSpriteImage("shadow",0,0),zombieX-11,zombieY+7);
		}
		for(int a = 0; a< zombies.size(); a++){
			zombies.get(a).render(g);
		}
	}

	public static void draw_human(Graphics g){

		for(int a = 0;a< humans.size(); a++){
			humans.get(a).render(g);
		}
	}
	
	public static void draw_effects(Graphics g){
		for(int a = 0;a< effects.size(); a++){
			effects.get(a).render(g);
		}
	}
	
	public static void draw_doodads(Graphics g){
		for(int a = 0;a< doodads.size(); a++){
			doodads.get(a).render(g);
		}
	}

	public static boolean isPosOccupied(int x, int y, int dir){
		if(dir==0){
			for(int i = 0; i< zombies.size(); i++){
				int pos = x - 30;
				if(zombies.get(i).getX()==pos && Math.abs(y- zombies.get(i).getY())<10){
					return true;
				}
			}
		}
		if(dir==1){
			for(int i = 0; i< zombies.size(); i++){
				int pos = x + 30;
				if(zombies.get(i).getX()==pos && Math.abs(y- zombies.get(i).getY())<10){
					return true;
				}
			}
		}
		if(dir==2){
			for(int i = 0; i< zombies.size(); i++){
				int pos = y - 30;
				if(zombies.get(i).getY()==pos && Math.abs(x- zombies.get(i).getX())<10){
					return true;
				}
			}
		}
		if(dir==3){
			for(int i = 0; i< zombies.size(); i++){
				int pos = y + 30;
				if(zombies.get(i).getY()==pos && Math.abs(x- zombies.get(i).getX())<10){
					return true;
				}
			}
		}
		return false;
	}
	
	public static void delete(int ID, int type){
		
		if(type == 1){
			for(int a=0; a<zombies.size(); a++){
				if( zombies.get(a).getID() == ID ){
					zombies.remove(a);
					break;
				}
			}
		}
		else if(type ==2){
			for(int a=0; a<effects.size(); a++){
				if( effects.get(a).getID() == ID ){
					effects.remove(a);
					break;
				}
			}
		}
		else if(type == 3){
			for(int a=0; a<humans.size(); a++){
				if( humans.get(a).getID() == ID ){
					humans.remove(a);
					break;
				}
			}
		}
		else{
			for(int a=0; a<doodads.size(); a++){
				if( doodads.get(a).getID() == ID ){
					doodads.remove(a);
					break;
				}
			}
		}
		
	}
}
