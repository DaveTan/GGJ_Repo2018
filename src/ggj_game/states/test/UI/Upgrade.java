package ggj_game.states.test.UI;

public class Upgrade {
	private boolean selected;
	private int x;
    private int y;
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
