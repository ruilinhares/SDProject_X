package iVotas.Model;

import RMI.RMIinterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class iVotasBean extends UnicastRemoteObject {

    private static final long serialVersionUID = 1L;

    private RMIinterface serverRMI;
    private String username;
    private String password;

    public iVotasBean() throws RemoteException {
        this.ligarRMI();
    }

    private void ligarRMI(){
        boolean ver = false;
        while(!ver){
            try {
                // TODO: Make lookup dynamic
                this.serverRMI = (RMIinterface) LocateRegistry.getRegistry(6789).lookup("HelloRMI");
                ver = true;
            } catch (RemoteException | NotBoundException e) {
                System.out.println("Primary is now down.");
            }
        }
    }

    public boolean userLogin() throws RemoteException {
        return serverRMI.userLogin(this.username,this.password);
    }

    public boolean logged(String user) throws RemoteException {
        return serverRMI.logged(user);
    }

    public ArrayList<String> getAllUsers() throws RemoteException {
        return serverRMI.getAllUsers(); // are you going to throw all exceptions?
    }

    public RMIinterface getServerRMI() {
        return serverRMI;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean login(String user, String pass){
        while(true){
            try{
                return this.serverRMI.userLogin(user, pass);
            }catch(RemoteException re){
                this.ligarRMI();
            }
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
