package ggj_game.utils.game_map;

import ggj_game.utils.ImageRes;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

/**
 * Created by Clientrace on 26/01/2018.
 */
public class GameMap {
    public static int binaryMap[][];
    private int hollows[][];
    private int solids[][];
    private int spawnpoints[][];
    
    public static int TileSize;

    public GameMap(String mapDir, int size){
        ImageRes.init();
        MapParser.load(mapDir);
        hollows = MapParser.mapHollows;
        solids = MapParser.mapSolids;
        spawnpoints = MapParser.mapSpawnPoints;
        TileSize = size;
        binaryMap = new int[MapParser.WIDTH][MapParser.HEIGHT];
    }

    public void render(Graphics g, float worldX, float worldY){
        // RENDER HOLLOWS
        for(int i=0;i<MapParser.WIDTH;i++) {
            for(int j=0;j<MapParser.HEIGHT;j++) {
                if(parseImageVal(hollows[i][j])!=null)
                    g.drawImage(parseImageVal(hollows[i][j]), i*TileSize, j*TileSize);
            }
        }

        // RENDER SOLIDS
        for(int i=0;i<MapParser.WIDTH;i++) {
            for(int j=0;j<MapParser.HEIGHT;j++) {
                if(parseImageVal(solids[i][j])!=null) {
                    g.drawImage(parseImageVal(solids[i][j]), i * TileSize, j * TileSize);
                    binaryMap[i][j] = 1;
                }
                else
                    binaryMap[i][j] = 0;
            }
        }
        
        // RENDER SPAWN POINTS
        for(int i=0;i<MapParser.WIDTH;i++) {
            for(int j=0;j<MapParser.HEIGHT;j++) {
                if(parseImageVal(spawnpoints[i][j])!=null){
                    g.drawImage(parseImageVal(spawnpoints[i][j]), i*TileSize, j*TileSize);
                    binaryMap[i][j] = 2;
                }
            }
        }
    }

    public Image parseImageVal(int index){
        if(index==0) return null;
        index-=1;

        SpriteSheet sheet = ImageRes.getSprite("tilesheet");
        int vertical = sheet.getVerticalCount();
        int horizontal = sheet.getHorizontalCount();

        int x = (index % horizontal);
        int y = (index / vertical);
        return sheet.getSubImage(x,y);
    }
}
