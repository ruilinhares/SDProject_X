package RMI;

import RMI.src.Classes.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RMIinterface extends Remote {

    boolean logged(String user) throws RemoteException;
    boolean userLogin(String name, String password) throws RemoteException;
    void addUser(String user, String pass) throws RemoteException;
    ArrayList<Departamento> getListaDepartamentos() throws RemoteException;
    void addDepartamento(Departamento dep) throws RemoteException;
    void RemoveDepartamento(int i) throws  RemoteException;
    ArrayList<String> getAllUsers() throws RemoteException;
    Departamento getDepartamento(String dep) throws RemoteException;
    Boolean registarPessoa (Pessoa pessoa) throws RemoteException;
    void sayHello() throws RemoteException;
}
