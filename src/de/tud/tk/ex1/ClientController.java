package de.tud.tk.ex1;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


abstract class ClientViewEventTriggerAdaptor implements ClientViewListener {
	
	public void connectButtonActionWithName(String name) {}
	public void callFliegeHunted() {}
	public void callLogoutAction() {}
}

public class ClientController extends  UnicastRemoteObject implements CallbackClientIntf{

	static Player playerObj;
	static ClientView clientViewObj;
	static AddServerIntf addServerIntf;
	//static Object clientObject;
	//CallBack Methods for the client side
	int z = 0;

	public ClientController() throws RemoteException {
		//clientObject =  new ClientController();
//		clientObject = this;
	}

	//main Function
	public static void main(String[] args) throws RemoteException {

		clientViewObj = new ClientView();
		playerObj = new Player();
//		System.out.println("hello");
		ClientView.setLoginUI();
		final ClientController clientObject;

		clientObject = new ClientController();

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

				System.out.println("FliegeHunted by : " + playerObj.getPlayerName() + ".....");
			}

			//Called from Connect Button Action...
			@Override
			public void connectButtonActionWithName(String name) {

				boolean status;
				playerObj.setPlayerName(name);
				//server call for login...


				try {
					// System.out.println(clientObject);
					addServerIntf.register(clientObject, name);
//					 System.out.println("after this");

					status = addServerIntf.login(name);

					if (status) {
						ClientView.initialiseUIForPlayer();
						addServerIntf.initGUIforClient(name);
					}

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


		});
		//End--->

		//Look up the server RMI


		String addServerURL = "rmi://" + args[0] + "/AddServer";
		try {
			addServerIntf =
					(AddServerIntf) Naming.lookup(addServerURL);

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

	public Object getClientObject(){
		return this;
	}

	public void newPositionofFliege(int x,int y){
//		System.out.println("client newpositionFielge");
//		System.out.println(x);
//		System.out.println(y);
		ClientView.setPositionOfFliege(x, y);
//		System.out.println("newPositionofFliege is called " + ++z);
	}

	public void loginStatus(boolean status) throws RemoteException{

		ClientView.initialiseUIForPlayer();
	}

	public void updatePlayerInfo(ArrayList<Player> playerList){

		String playerListString = new String("Hello  " + playerObj.getPlayerName() + "\n\n");
		playerListString += "Player   \tScore" + "\n";
//		System.out.println(playerList.size());
		for (Player P : playerList)
		{
//			System.out.println(P.getPlayerName());
//			System.out.println(P.getPoints());
//			playerListString.concat(P.getPlayerName() + "\t" + String.valueOf(P.getPoints())+"\n");
			playerListString +=P.getPlayerName() + "\t" + String.valueOf(P.getPoints())+"\n";
//			System.out.println(playerListString);
//			System.out.println("called this for " + i++);
		}
//		System.out.println(playerListString);
		clientViewObj.showPlayerListInUI(playerListString);
	}

}