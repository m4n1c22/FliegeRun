import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Hashtable;


abstract class ClientViewEventTriggerAdaptor implements ClientViewListener {
	
	public void connectButtonActionWithName(String name) {}
	public void callFliegeHunted() {}
	
}

public class ClientController implements callbackClientIntf{

	static Player playerObj;
	static ClientView clientViewObj;
	
	//CallBack Methods for the client side
	
	public ClientController() {
		
	}
	public static void didLoginSuccessfully() {
		
		clientViewObj.initialiseUIForPlayerWithPlayerName(playerObj.getPlayerName());
	}
	
	//Please pass the necessary parameters....
	public void updatePlayerList() {
		
		
	}
	
	public void didSetFliegePosition() {
		
		//call setPositionOfFliege function using the clientViewObj....
		
	}


	//ServerCalls

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
				
				System.out.println("FliegeHunted by"+playerObj.getPlayerName()+".....");
			}
			
			//Called from Connect Button Action...
			@Override
			public void connectButtonActionWithName(String name) {
				playerObj.setPlayerName(name);
				//server call for login...
			
				
			}
			
		});
		//End--->
		
		//Look up the server RMI
		
		String addServerURL = "rmi://" + args[0] + "/AddServer";
		 try {
			  AddServerIntf addServerIntf =
			  (AddServerIntf)Naming.lookup(addServerURL);
			  ClientController callbackObj = new ClientController();
				//register for CALL BACKS 
				 addServerIntf.register(callbackObj);			
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

	  public void newPositionofFliege(Fliege F){
		  
	  }
	  public void loginStatus(boolean status) throws RemoteException{
		  
	  }
	  
	  public void updatePlayerInfo(Hashtable<String,Player> player_info){
		  
	  }
}