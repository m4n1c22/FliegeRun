package de.tud.tk.ex1;

import java.io.Serializable;

public class Fliege implements Serializable {

	private int position_fly_x, position_fly_y;
	private boolean fliege_hunted;

	public boolean isFliege_hunted() {
		return fliege_hunted;
	}

	public void setFliege_hunted(boolean fliege_hunted) {
		this.fliege_hunted = fliege_hunted;
	}

	public int getPositionFlyX() {

		return position_fly_x;
	}

	public int getPositionFlyY() {
		return position_fly_y;
	}

	public void setPositionFlyX(int in_position_fly_x) {

		this.position_fly_x = in_position_fly_x;

	}

	public void setPositionFlyY(int in_position_fly_y) {

		this.position_fly_y = in_position_fly_y;
	}

}
