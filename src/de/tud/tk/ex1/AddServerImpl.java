package de.tud.tk.ex1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class AddServerImpl extends UnicastRemoteObject implements AddServerIntf {

	public static Fliege fliege;
	public static Hashtable<String, Player> playerInfo;
	private Hashtable<String, Object> clientList;

	public AddServerImpl() throws RemoteException {

		fliege = new Fliege();
		clientList = new Hashtable<String, Object>();
		playerInfo = new Hashtable<String, Player>();
	}

	public static int randInt(int min, int max) {

		// NOTE: Usually this should be a field rather than a method
		// variable so that it is not re-seeded every call.
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	private synchronized void doCallbacks() throws java.rmi.RemoteException {
		// make callback to each registered client
		ArrayList<Player> arr = new ArrayList<Player>(playerInfo.values());
		// System.out.println(arr.size());
		// System.out.println(
		// "**************************************\n"
		// + "Callbacks initiated �-");
		String str;
		Set<String> set = clientList.keySet(); // get set-view of keys
		// get iterator
		Iterator<String> itr = set.iterator();

		while (itr.hasNext()) {
			str = itr.next();
			// System.out.println("doing "+ str +"'s callback\n");

			// convert the vector object to a callback object
			ClientCallbackInterface nextClient = (ClientCallbackInterface) clientList.get(str);

			// invoke the callback method

			nextClient.updatePlayerInfo(arr);
		}

		// System.out.println("********************************\n" +
		// "Server completed callbacks �-");
	} // doCallbacks

	public void setPositionOfFliege() {

		int rand_pos_of_Fliege_X, rand_pos_of_Fliege_Y;
		rand_pos_of_Fliege_X = randInt(0, 800);
		rand_pos_of_Fliege_Y = randInt(0, 600);
		fliege.setPositionFlyX(rand_pos_of_Fliege_X);
		fliege.setPositionFlyY(rand_pos_of_Fliege_Y);
	}

	public void fliegeHunted(String userName) throws RemoteException {

		if (!fliege.isFliege_hunted()) {
			Player tempInfo;
			int points = 0;
			fliege.setFliege_hunted(true);
			setPositionOfFliege();
			if ((tempInfo = playerInfo.get(userName)) != null) {
				points = tempInfo.getPoints() + 1;
				tempInfo.setPoints(points);// Get this player's Object ID Grant
											// 1 point to him and update the
											// hash list again.
				playerInfo.put(userName, tempInfo);
			}
			fliege.setFliege_hunted(false);
		}
		// Callback from server to all the clients to update score.
		doCallbacks();
		// Callback from server to clients to give position of the Fliege.

		String str;
		Set<String> set = clientList.keySet(); // get set-view of keys
		// get iterator
		Iterator<String> itr = set.iterator();

		while (itr.hasNext()) {
			str = itr.next();
			// System.out.println("doing "+ str +"'s callback\n");
			// convert the vector object to a callback object
			ClientCallbackInterface nextClient = (ClientCallbackInterface) clientList.get(str);
			// invoke the callback method
			nextClient.newPositionOfFliege(fliege.getPositionFlyX(), fliege.getPositionFlyY());
			// System.out.println("new position : " + fliege.getPositionFlyX() +
			// fliege.getPositionFlyY() );
		}

	}

	public boolean login(String userName) throws RemoteException {
		boolean status = false;
		if (playerInfo.containsKey(userName) == false) {
			Player p_info = new Player();
			p_info.setPlayerName(userName);
			// p_info.setPoints(0); not required, implemented a default
			// constructor to initialize the variable to 0.
			playerInfo.put(userName, p_info);
			// Now that Player information is updated in Server's DB, set the
			// position of FLIEGE to all the clients.
			if (playerInfo.size() == 1) {
				// Set the position of the Fliege for the first client here.,
				// subsequent clients logged in will only
				// need the current position of the Fliege.
				setPositionOfFliege();
			}

			// If login is not for the 1st client then update the position to
			// the client who just logged in.
			// ((callbackClientIntf)
			// clientList.get(userName)).newPositionofFliege(fliege.getPositionFlyX(),fliege.getPositionFlyY());

			// Update the player information to all the clients.
			// doCallbacks();
			status = true;
			return status;
		}
		return status;
		// CALL BACK the function here to update the player information to all.
	}

	public void initGUIforClient(String userName) throws RemoteException {
		// If login is not for the 1st client then update the position to the
		// client who just logged in.
		((ClientCallbackInterface) clientList.get(userName)).newPositionOfFliege(fliege.getPositionFlyX(), fliege.getPositionFlyY());

		// Update the player information to all the clients.
		doCallbacks();
	}

	public void logout(String userName) throws RemoteException {
		// Remove the player information from the MODEL class.
		playerInfo.remove(userName);
		// Remove the client object as it is no longer required.
		clientList.remove(userName);
		// Update the latest players information to all other clients.
		doCallbacks();
	}

	// Register all the RMI Client CALLBACKS.
	public void register(Object obj, String Username) throws RemoteException {
		// System.out.println("inside register");
		if (clientList.containsKey(Username) == false) {
			clientList.put(Username, obj);
			// System.out.println("Registered new client ");
		} // end if
	}
}