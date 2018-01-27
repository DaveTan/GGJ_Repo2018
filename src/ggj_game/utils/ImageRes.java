package ggj_game.utils;

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
<<<<<<< HEAD
            sprites.put("upgrades", loadSprite("res/card.png",150,100));
=======
            sprites.put("range",loadSprite("res/range.png",150,150));
>>>>>>> clarence
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
