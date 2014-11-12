package server;

import java.rmi.Naming;
public class AddServer {
public static void main(String args[]) {
try {
AddServerImpl addServerImpl = new AddServerImpl();
//    System.setSecurityManager(new RMISecurityManager());
    Naming.rebind("server.AddServer", addServerImpl);
}
catch(Exception e) {
System.out.println("Exception:  " + e);
}
}
}