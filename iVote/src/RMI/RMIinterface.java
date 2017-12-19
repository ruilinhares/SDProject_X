package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIinterface extends Remote {

    boolean userLogin(String name, String password) throws RemoteException;
}
