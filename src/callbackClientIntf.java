import java.io.Serializable;
import java.rmi.*;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *  CallbackClientIntf interface contains following methods 
 *  IsGoodObject(Obj, Obj): compare 2 objects,
 *  IsRightValue(long, long): compare 2 longs,
 */
public interface callbackClientIntf extends Remote
{
    public static final String NAME = "CallbackClientIntf";

    public void newPositionofFliege(int x, int y) throws RemoteException;
    public void loginStatus(boolean status) throws RemoteException;
    public void updatePlayerInfo(ArrayList<Player> playerList) throws RemoteException;

} // end CallbackClientIntf