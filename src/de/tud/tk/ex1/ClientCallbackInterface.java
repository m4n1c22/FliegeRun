package de.tud.tk.ex1;
/* Copyright (c) 1999 BEA Systems, Inc.   All Rights Reserved */

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * ClientCallbackInterface interface contains following methods IsGoodObject(Obj,
 * Obj): compare 2 objects, IsRightValue(long, long): compare 2 longs,
 */
public interface ClientCallbackInterface extends Remote {
	public static final String NAME = "ClientCallbackInterface";

	public void newPositionOfFliege(int x, int y) throws RemoteException;

	public void loginStatus(boolean status) throws RemoteException;

	public void updatePlayerInfo(ArrayList<Player> playerList) throws RemoteException;

} // end ClientCallbackInterface