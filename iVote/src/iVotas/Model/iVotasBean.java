package iVotas.Model;

import RMI.RMIinterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class iVotasBean extends UnicastRemoteObject {

    private static final long serialVersionUID = 1L;

    private RMIinterface serverRMI;
    private String username;

    public iVotasBean() throws RemoteException {
        this.ligarRMI();
        this.username = null;
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

    /*
    public ArrayList<String> getNotificacoes() {
        while(true) {
            try{
                return this.serverRMI.getNotificacoes(this.username);
            } catch(RemoteException re) {
                this.ligarRMI();
            }
        }
    }*/

    /*
    public boolean connectToFacebook(String code) {
        while(true) {
            try {
                return this.serverRMI.connectToFacebook(this.username, code);
            } catch(RemoteException re) {
                this.ligarRMI();
            }
        }
    }*/
}
