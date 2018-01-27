package ggj_game.utils;


public class CountTime {
	private long initialTime;
	private long secondLeft;
	private long time;
	public CountTime(int seconds) {
		initialTime = System.nanoTime() + seconds * 1000000;	
	}
	
	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public long getSecondLeft() {
		return secondLeft;
	}
	
}
