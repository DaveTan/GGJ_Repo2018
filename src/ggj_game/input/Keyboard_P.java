package ggj_game.input;

/* LWJGL LIBRARY */
import org.lwjgl.input.Keyboard;


/* JAVA LIBRARY */
import java.util.ArrayList;

public class Keyboard_P {
	private static ArrayList<Integer> KeyInput;
	
	/* INITIALIZE KEYPRESS CONTAINER */
	public static void initialize(){
		KeyInput = new ArrayList<Integer>();
	}
	
	/* REMOVE KEYPRESS AT RELEASE */
	public static void removeKeypress(int key){
		if(KeyInput.contains((Integer)key)){
			KeyInput.remove((Integer)key);
		}
	}
	
	/* ADD KEYPRESS  AT PRESS */
	public static void addKeypress(int key){
		if(Keyboard.isKeyDown(key) && !KeyInput.contains(key)){
			KeyInput.add((Integer)key);
		}
	}
	
	/* PRINTS ALL PRESSED KEYS TO CONSOLE */
	public static void printControls(){
		System.out.println(KeyInput.toString());
	}
	
	/* RETURNS AN ARRAY OF PRESSED KEYS */
	public static Object[] getKeyList(){
		return KeyInput.toArray();
	}
	
	/* RETURNS TRUE IF ALL OF THE PARAMETERS ARE PRESSED */
	public static boolean isPressed(int... args){
		for(int a=0; a<args.length;a++){
			if(!KeyInput.contains(args[a])){
				return false;
			}
		}
		return true;
	}
	
	/* RETURNS TRUE IF THE SEQUENCE OF KEYS ARE PRESSED */
	public static boolean isSequence(int... args){
		for(int a=0; a<args.length;a++){
			if(!KeyInput.contains(args[a])){
				return false;
			}
		}
		return true;
	}
}
