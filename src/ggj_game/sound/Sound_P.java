package ggj_game.sound;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Sound_P {
	private static ArrayList<Sound> SoundList;
	
	public static void Initialize(){
		SoundList = new ArrayList<Sound>();
		
		for(int a=0; a<Sound_S.SoundFiles.length; a++){
			try {
				SoundList.add(new Sound(Sound_C.Sound_Dir + Sound_S.SoundFiles[a]));
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void Play(int SoundID){
		SoundList.get(SoundID).play();
	}
	
	public static void Loop(int SoundID){
		SoundList.get(SoundID).loop();
	}
	
	public static void Stop(int SoundID){
		SoundList.get(SoundID).stop();
	}
}
