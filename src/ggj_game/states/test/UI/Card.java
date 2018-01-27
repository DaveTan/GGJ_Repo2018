package ggj_game.states.test.UI;

/**
 * Created by Clientrace on 27/01/2018.
 */
public class Card {
    private int type;
    private int x;
    private int y;
    private boolean selected;
    public void setType(int type){
        this.type = type;
        this.selected = false;
    }
    public int getType(){
        return type;
    }

    public void touch(){
        selected = true;
    }

    public void untouch (){
        selected = false;
    }

    public boolean isSelected(){
        return selected;
    }

    public boolean checkMouseTouch(int mx, int my){
        if(mx>=x && mx<=x+100 && my>=y && my<=y+150)
            return true;
        return false;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }
}
