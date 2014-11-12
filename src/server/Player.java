package server;

import java.io.Serializable;

//This is code for player side
public class Player implements Serializable{

	private int points;
	private String playerName;
	
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public int getPoints() {
	
		return points;
	}
	public void setPoints(int in_points) {
		
		this.points = in_points;
	}
}
