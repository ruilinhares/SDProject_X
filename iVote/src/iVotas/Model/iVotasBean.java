package iVotas.Model;

import RMI.RMIinterface;
import RMI.src.Classes.Departamento;
import RMI.src.Classes.Eleicao;
import RMI.src.Classes.Pessoa;
import RMI.src.TCP.TCPServer;

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


    public Departamento getDepartamento(String dep) throws RemoteException {
        return serverRMI.getDepartamento(dep);
    }

    public boolean registarPessoa(Pessoa pessoa) throws RemoteException {
        boolean res = serverRMI.registarPessoa(pessoa);
        if (res)
            serverRMI.addUser(pessoa.getNome(),pessoa.getPassword());
        return res;
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

    public boolean criaDep(String nomeDep){
        ArrayList<Departamento> departamentos = null;
        try {
            departamentos = this.serverRMI.getListaDepartamentos();
            for (Departamento auxDep : departamentos)
                if ((nomeDep.toUpperCase()).equals(auxDep.getNome().toUpperCase())){
                    System.out.println("Departamento ja existente!");
                    return false;
                }
            Departamento dep = new Departamento(nomeDep,new ArrayList<>());

            this.serverRMI.addDepartamento(dep);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean apagaDep(String nomeDep){
        ArrayList<Departamento> departamentos = null;
        try {
            departamentos = this.serverRMI.getListaDepartamentos();
            int i = 0;
            for (Departamento auxDep : departamentos) {
                if ((nomeDep.toUpperCase()).equals(auxDep.getNome().toUpperCase())) {
                    this.serverRMI.RemoveDepartamento(i);
                    return true;
                }
                i++;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addLista(String titulo, String lista){
        try {
            return serverRMI.addListaCandidata(titulo,lista);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    public StringBuilder listaLocaisVoto(String uc){
        StringBuilder res = new StringBuilder();
        try {
            ArrayList<Eleicao> eleicoes = this.serverRMI.getListaEleicoes();

            for (Eleicao aux : eleicoes){
                res.append(aux.localVoto(uc));
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return res;
    }

    public boolean criaMesa(String dep){
        try {
            ArrayList<TCPServer> mesas = this.serverRMI.getMesasVotos();

            for (TCPServer mesa : mesas){
                if((mesa.getDepartamento().getNome().toUpperCase()).equals(dep.toUpperCase())){
                    System.out.println("Este departamento já tem uma mesa");
                    return false;
                }
            }
            Departamento depAux = serverRMI.getDepartamento(dep);
            if (depAux!=null) {
                TCPServer novamesa=new TCPServer(depAux);
                serverRMI.AddMesa(novamesa);
                return true;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean apagaMesa(String dep){
        try {
            ArrayList<TCPServer> mesas = this.serverRMI.getMesasVotos();
            int i = 0;
            for (TCPServer mesa : mesas){
                if((mesa.getDepartamento().getNome().toUpperCase()).equals(dep.toUpperCase())){
                    serverRMI.RemoveMesa(i);
                    return true;
                }
                i++;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    //---Votar------------------------------

    public boolean userLogin(String user, String pass) throws RemoteException {
        return serverRMI.userLogin(user,pass);
    }

    public boolean logged(String user) throws RemoteException {
        return serverRMI.logged(user);
    }

    public ArrayList<String> getAllUsers() throws RemoteException {
        return serverRMI.getAllUsers(); // are you going to throw all exceptions?
    }

    public void logout(String user){
        try {
            this.serverRMI.logout(user);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
