import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;


abstract class ClientViewEventTriggerAdaptor implements ClientViewListener {
	
	public void connectButtonActionWithName(String name) {}
	public void callFliegeHunted() {}
	public void callLogoutAction() {}
}

public class ClientController extends  UnicastRemoteObject implements callbackClientIntf{

	static Player playerObj;
	static ClientView clientViewObj;
	static AddServerIntf addServerIntf;
	//CallBack Methods for the client side

	public ClientController() throws RemoteException {
	}
	
	public void newPositionofFliege(Fliege F){
	
		clientViewObj.setPositionOfFliege(F);
	}

	public void loginStatus(boolean status) throws RemoteException{
		  
		clientViewObj.initialiseUIForPlayer();
	}
	  
	public void updatePlayerInfo(ArrayList<Player> playerList){
		  
		String playerListString = new String("Hello" + playerObj.getPlayerName() + "\n");
		
		for (Player P : playerList)
		{
			playerListString.concat(P.getPlayerName() + "\t" + String.valueOf(P.getPoints())+"\n");
		}
		
		clientViewObj.showPlayerListInUI(playerListString);
	}

	
	//main Function
	public static void main(String[] args) {
	
		clientViewObj = new ClientView();
		playerObj = new Player();
		System.out.println("hello");
		clientViewObj.setLoginUI();
		//Start -->
		clientViewObj.addListener(new ClientViewEventTriggerAdaptor() {
		
			
			//Called from the mouseClickedEvent and mouseDraggedEvent Methods....
			@Override
			public void callFliegeHunted() {
				
				//server call for fliegeHunted()
				try {
					addServerIntf.fliegeHunted(playerObj.getPlayerName());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("FliegeHunted by"+playerObj.getPlayerName()+".....");
			}
			
			//Called from Connect Button Action...
			@Override
			public void connectButtonActionWithName(String name) {

				playerObj.setPlayerName(name);
				//server call for login...
			

				 try {
					 System.out.println("before this");
					 this.print();
					 addServerIntf.register(this, name);
					 System.out.println("after this");

					 addServerIntf.login(name);
					
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
			}
			@Override
			public void callLogoutAction() {
				
					 try {
					addServerIntf.logout(playerObj.getPlayerName());
					
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			public void print(){
				System.out.println("print called");
			}
			
			
		});
		//End--->
		
		//Look up the server RMI

		
		String addServerURL = "rmi://" + args[0] + "/AddServer";
		 try {
			  addServerIntf =
			  (AddServerIntf)Naming.lookup(addServerURL);
			  			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 

	}

}