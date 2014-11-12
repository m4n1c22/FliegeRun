import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

	public class AddServerImpl extends UnicastRemoteObject implements AddServerIntf {
			
		public static Fliege F;
		private Hashtable<String, Object> clientList;
		public static Hashtable<String, Player> player_info;
	
		public AddServerImpl() throws RemoteException {
			
			F = new Fliege();
			player_info = new Hashtable<String,Player>();			
		}
		
		private synchronized void doCallbacks( ) throws java.rmi.RemoteException{
			 // make callback to each registered client
			 System.out.println(
			   "**************************************\n"
			 + "Callbacks initiated —-");
			 
			 String str;
			 Set<String> set = clientList.keySet(); // get set-view of keys
			 // get iterator
			 Iterator<String> itr = set.iterator();
			 
			 while(itr.hasNext()) {
				 str = (String) itr.next();     
				 System.out.println("doing "+ str +"'s callback\n");
	
				 //convert the vector object to a callback object
				 callbackClientIntf nextClient = (callbackClientIntf)clientList.get(str);
				 
				 // invoke the callback method
				 nextClient.updatePlayerInfo(player_info);
				 nextClient.newPositionofFliege(F);
			
			 }
			 
			  
			 

			 System.out.println("********************************\n" +
			 "Server completed callbacks —-");
		} // doCallbacks
				
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
			rand_pos_of_Fliege_X = randInt(0,400);
			rand_pos_of_Fliege_Y =randInt(0,300);
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
				//informPosOfFliegetoClient();				
			}	
			
			//Callback from server to remaining clients to update score.
			//Callback from server to clients to give position of the Fliege.
			doCallbacks( );
		}
		
		public boolean login(String userName) throws RemoteException{
			boolean status = false;
			if(player_info.containsKey(userName) == false ){
				Player p_info = new Player();
				p_info.setPlayerName(userName);
				//p_info.setPoints(0); not required, implemented a default constructor to initialize the variable to 0.
			    player_info.put(userName,p_info);
			    //Now that Player information is updated in Server's DB, set the position of FLIEGE to all the clients.
			    //setPositionOfFliege(); --> This may not be required here.
			    status = true;
			    return status;
			    
			}
			return status;
		    //CALL BACK the function here to update the player information to all.
		}
	 
		public void logout(String userName) throws RemoteException{
			
			player_info.remove(userName);
			
		}
		
		public Fliege informPosOfFliegetoClient(){
			return(F);			
		}

		//Register all the RMI Client CALLBACKS.
		public void register(Object obj, String Username) throws RemoteException {
		
			if (clientList.containsKey(Username) == false) {
				 clientList.put(Username, obj)
				 System.out.println("Registered new client ");
				 } // end if			
		}		
	}