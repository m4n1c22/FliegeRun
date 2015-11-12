package de.tud.tk.ex1;

import java.util.Random;

public class Server {

	Fliege F;

	public static int randInt(int min, int max) {

		// NOTE: Usually this should be a field rather than a method
		// variable so that it is not re-seeded every call.
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	public void setPositionOfFliege() {

		int rand_pos_of_Fliege_X, rand_pos_of_Fliege_Y;
		rand_pos_of_Fliege_X = randInt(0, 400);
		rand_pos_of_Fliege_Y = randInt(0, 300);
		F.setPositionFlyX(rand_pos_of_Fliege_X);
		F.setPositionFlyY(rand_pos_of_Fliege_Y);
	}

	public void fliegeHunted() {

		if (!F.isFliege_hunted()) {

			F.setFliege_hunted(true);
			setPositionOfFliege();
		}

		// Callback from server to remaining clients

	}

}
