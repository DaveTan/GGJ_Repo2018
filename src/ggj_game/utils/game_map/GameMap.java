package ggj_game.utils.game_map;

import ggj_game.utils.ImageRes;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

/**
 * Created by Clientrace on 26/01/2018.
 */
public class GameMap {
    private int hollows[][];
    private int solids[][];
    private int TileSize;

    public GameMap(String mapDir, int size){
        ImageRes.init();
        MapParser.load(mapDir);
        hollows = MapParser.mapHollows;
        solids = MapParser.mapSolids;
        TileSize = size;
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
                if(parseImageVal(solids[i][j])!=null)
                    g.drawImage(parseImageVal(solids[i][j]), i*TileSize, j*TileSize);
            }
        }
    }

    public Image parseImageVal(int index){
        if(index==0) return null;
        index-=1;

        SpriteSheet sheet = ImageRes.getSprite("tilesheet");
        int vertical = sheet.getVerticalCount();
        int horizontal = sheet.getHorizontalCount();

        System.out.println(vertical);
        System.out.println(horizontal);

        int x = (index % horizontal);
        int y = (index / vertical);
        return sheet.getSubImage(x,y);
    }
}
