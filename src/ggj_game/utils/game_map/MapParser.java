package ggj_game.utils.game_map;



import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

import ggj_game.utils.ImageRes;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;


/**
 * Created by Clientrace on 26/01/2018.
 */
public class MapParser {
    public static int WIDTH;
    public static int HEIGHT;
    public static int mapSolids [][];
    public static int mapHollows[][];

    public static void load(String path){

        try{
            JSONParser parser = new JSONParser();
            Object object = parser.parse(new FileReader(path));
            JSONObject jobj = (JSONObject) object;

            JSONArray layers = (JSONArray) jobj.get("layers");
            int amount = layers.size();
            for(int i=0; i<amount; i++){
                JSONObject layer = (JSONObject) layers.get(i);
                String type = (String) layer.get("name");
                WIDTH  = (int)(long) layer.get("width");
                HEIGHT = (int)(long) layer.get("height");
                switch (type){
                    case "solids" : {
                        mapSolids = new int[WIDTH][HEIGHT];
                        JSONArray arr = (JSONArray) layer.get("data");
                        for(int j=0;j<WIDTH;j++){
                            for(int k=0;k<HEIGHT;k++)
                                mapSolids[j][k] = (int)((long)arr.get((k * WIDTH) + j));
                        }
                    }break;
                    case "hollows" : {
                        mapHollows = new int[WIDTH][HEIGHT];
                        JSONArray arr = (JSONArray) layer.get("data");
                        for(int j=0;j<WIDTH;j++){
                            for(int k=0;k<HEIGHT;k++)
                                mapHollows[j][k] = (int)((long)arr.get((k * WIDTH) + j));
                        }
                    }break;
                }
            }
        }catch (Exception e){
            System.out.println("Can't load map");
            e.printStackTrace();
        }
    }

    private static Image[][] parse(JSONArray arr, int width, int height){
        Image[][] layer = new Image[width][height];
        int index;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                index = (int)((long)arr.get((j * width) + i));
                layer[i][j] = getSpriteImage(index);
            }
        }

        return layer;
    }


    private static Image getSpriteImage(int index){
        if(index==0) return null;
        index-=1;

        SpriteSheet sheet = ImageRes.getSprite("tilesheet");
        int vertical = sheet.getVerticalCount();
        int horizontal = sheet.getHorizontalCount();


        int x = (index % horizontal);
        int y = (index / vertical);

        System.out.println(horizontal);
        System.out.println("INDEX: "+index+" X: "+x+" Y: "+y);

        System.out.println("X: "+x+"Y: "+y);
        return sheet.getSubImage(x,y);
    }

}
