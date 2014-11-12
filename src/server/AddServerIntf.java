package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AddServerIntf extends Remote {
	
	//public static final int FAILURE = -1;
	//public static final int SUCCESS = 0;
	  
	//double add(double d1, double d2) throws RemoteException;
	public boolean login(String userName) throws RemoteException;
	public void logout(String userName) throws RemoteException;
	public void fliegeHunted(String userName) throws RemoteException;
	public void initGUIforClient(String userName) throws RemoteException;
	public void register(Object obj, String Username) throws RemoteException; //This is to register for the CALLBACK.
	//public server.Player update
	
}