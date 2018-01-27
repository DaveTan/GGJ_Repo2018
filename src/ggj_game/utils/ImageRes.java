package ggj_game.utils;

import ggj_game.window.Window_C;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Clientrace on 26/01/2018.
 */
public class ImageRes {
    private static Map<String, Image> images;
    private static Map<String, SpriteSheet> sprites;

    public static void init(){
        images = new HashMap<>();
        sprites = new HashMap<>();
        try{
            sprites.put("tilesheet",loadSprite("res/tiles.png",32,32));
            sprites.put("cards",loadSprite("res/card.png",100,150));
            sprites.put("upgrades", loadSprite("res/card.png",150,100));
            sprites.put("range",loadSprite("res/range.png",150,150));
            sprites.put("shadow",loadSprite("res/shadow.png",25,13));
            sprites.put("ui",loadSprite("res/UI.png", Window_C.SIZE_W,Window_C.SIZE_H));
            sprites.put("mine",loadSprite("res/mine.png",11,11));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static Image loadImages(String path) throws SlickException{
        return new Image(path,false,Image.FILTER_NEAREST);
    }

    public static Image getImage(String id){return images.get(id);}

    public static SpriteSheet loadSprite(String path, int tw, int th)throws SlickException {
        return new SpriteSheet(loadImages(path),tw,th);
    }

    public static SpriteSheet getSprite(String getter){
        return sprites.get(getter);
    }

    public static Image getSpriteImage(String getter, int y, int x){
        return sprites.get(getter).getSubImage(x,y);
    }

    public static Image image(String getter){
        return sprites.get(getter);
    }
}
