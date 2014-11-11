import java.rmi.*;

public interface AddServerIntf extends Remote {
	//double add(double d1, double d2) throws RemoteException;
	public int login(String userName) throws RemoteException;
	public int logout(String userName) throws RemoteException;
	public void fliegeHunted(String userName) throws RemoteException;
	//public Player update
	
}