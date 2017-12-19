package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Set;

public interface RMIinterface extends Remote {

    boolean logged(String user) throws RemoteException;
    boolean userLogin(String name, String password) throws RemoteException;
    ArrayList<String> getAllUsers() throws RemoteException;
}
