import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

	public class AddServerImpl extends UnicastRemoteObject implements AddServerIntf {
			
		public static Fliege F;
		public static Hashtable<String, Player> player_info;
		private Hashtable<String, Object> clientList;
	
		public AddServerImpl() throws RemoteException {
			
			F = new Fliege();
			clientList = new Hashtable<String, Object>();
			player_info = new Hashtable<String,Player>();			
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
				
		private synchronized void doCallbacks( ) throws java.rmi.RemoteException{
			 // make callback to each registered client
			 ArrayList<Player> arr = new ArrayList<Player>(player_info.values());
//			 System.out.println(arr.size());
//			 System.out.println(
//			   "**************************************\n"
//			 + "Callbacks initiated �-");
			 String str;
			 Set<String> set = clientList.keySet(); // get set-view of keys
			 // get iterator
			 Iterator<String> itr = set.iterator();

			 while(itr.hasNext()) {
				 str = itr.next();
//				 System.out.println("doing "+ str +"'s callback\n");

				 //convert the vector object to a callback object
				 callbackClientIntf nextClient = (callbackClientIntf)clientList.get(str);

				 // invoke the callback method

				 nextClient.updatePlayerInfo(arr);
			 }

//			 System.out.println("********************************\n" +
//			 "Server completed callbacks �-");
		} // doCallbacks

		public void setPositionOfFliege() {
			
			int rand_pos_of_Fliege_X, rand_pos_of_Fliege_Y;
			rand_pos_of_Fliege_X = randInt(0, 800);
			rand_pos_of_Fliege_Y = randInt(0, 600);
			F.setPositionFlyX(rand_pos_of_Fliege_X);
			F.setPositionFlyY(rand_pos_of_Fliege_Y);
		}
		
		public void fliegeHunted(String userName) throws RemoteException{
			
			if(!F.isFliege_hunted()) {
				Player tempInfo;
				int points=0;
				F.setFliege_hunted(true);				
				setPositionOfFliege();
				if((tempInfo = player_info.get(userName) )!= null){
					points= tempInfo.getPoints()+1;
					tempInfo.setPoints(points);//Get this player's Object ID Grant 1 point to him  and update the hash list again.
					player_info.put(userName, tempInfo);					
				}
				F.setFliege_hunted(false);
			}				
			 //Callback from server to all the clients to update score.
			 doCallbacks( );
			 //Callback from server to clients to give position of the Fliege.
			 
			 String str;
			 Set<String> set = clientList.keySet(); // get set-view of keys
			 // get iterator
			 Iterator<String> itr = set.iterator();
			 
			 while(itr.hasNext()) {
				 str = itr.next();
//				 System.out.println("doing "+ str +"'s callback\n");
				 //convert the vector object to a callback object
				 callbackClientIntf nextClient = (callbackClientIntf)clientList.get(str);
				 // invoke the callback method
				 nextClient.newPositionofFliege(F.getPositionFlyX(),F.getPositionFlyY());
//				 System.out.println("new position :  " + F.getPositionFlyX() + F.getPositionFlyY() );
			 }
            
		}
		
		public boolean login(String userName) throws RemoteException{
			boolean status = false;
			if(player_info.containsKey(userName) == false ){
				Player p_info = new Player();
				p_info.setPlayerName(userName);
				//p_info.setPoints(0); not required, implemented a default constructor to initialize the variable to 0.
			    player_info.put(userName,p_info);
			    //Now that Player information is updated in Server's DB, set the position of FLIEGE to all the clients.
			    if(player_info.size()==1 ){
			    	//Set the position of the Fliege for the first client here., subsequent clients logged in will only
			    	//need the current position of the Fliege.
			    	setPositionOfFliege(); 	
			    }

			     //If login is not for the 1st client then update the position to the client who just logged in.
			     //((callbackClientIntf) clientList.get(userName)).newPositionofFliege(F.getPositionFlyX(),F.getPositionFlyY());

			    //Update the player information to all the clients.
			    //doCallbacks();
			    status = true;
			    return status;
			}
			return status;
		    //CALL BACK the function here to update the player information to all.
		}

		public void initGUIforClient(String userName) throws RemoteException{
			//If login is not for the 1st client then update the position to the client who just logged in.
			((callbackClientIntf) clientList.get(userName)).newPositionofFliege(F.getPositionFlyX(),F.getPositionFlyY());

			//Update the player information to all the clients.
			doCallbacks();
		}
		public void logout(String userName) throws RemoteException{
			//Remove the player information from the MODEL class.
			player_info.remove(userName);
			//Remove the client object as it is no longer required.
			clientList.remove(userName);
			//Update the latest players information to all other clients.
			doCallbacks();
		}
		

		//Register all the RMI Client CALLBACKS.
		public void register(Object obj, String Username) throws RemoteException {
//			System.out.println("inside register");
			if (clientList.containsKey(Username) == false) {
				 clientList.put(Username, obj);
//				 System.out.println("Registered new client ");
				 } // end if			
		}		
	}