/* Copyright (c) 1999 BEA Systems, Inc.   All Rights Reserved */

import java.rmi.*;
import java.util.Hashtable;

/**
 *  CallbackClientIntf interface contains following methods 
 *  IsGoodObject(Obj, Obj): compare 2 objects,
 *  IsRightValue(long, long): compare 2 longs,
 */
public interface callbackClientIntf extends Remote 
{   
  public static final String NAME = "CallbackClientIntf";
	
  public void newPositionofFliege(Fliege F) throws RemoteException;
  public void loginStatus(boolean status) throws RemoteException;
  public void updatePlayerInfo(Hashtable<String,Player> player_info) throws RemoteException;

} // end CallbackClientIntf