
abstract class ClientViewEventTriggerAdaptor implements ClientViewListener {
	
	public void connectButtonActionWithName(String name) {}
	public void callFliegeHunted() {}
	
}

public class ClientController {

	static Player playerObj;
	static ClientView clientViewObj;
	
	//CallBack Methods for the client side
	
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
			
				didLoginSuccessfully();
			}
			
		});
	}

}