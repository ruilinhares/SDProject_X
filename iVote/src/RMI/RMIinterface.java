package RMI;

import RMI.Classes.*;
import RMI.TCP.TCPServer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RMIinterface extends Remote {

    boolean logged(String user) throws RemoteException;
    boolean userLogin(String name, String password) throws RemoteException;
    void logout(String user) throws RemoteException;
    void addUser(String user, String pass) throws RemoteException;
    ArrayList<Departamento> getListaDepartamentos() throws RemoteException;
    void addDepartamento(Departamento dep) throws RemoteException;
    ArrayList<String> getAllUsers() throws RemoteException;
    boolean votar(String eleicao, String lista, String user) throws RemoteException;
    void RemoveDepartamento(int i) throws  RemoteException;
    ArrayList<Eleicao> getListaEleicoes() throws RemoteException;
    Departamento getDepartamento(String dep) throws RemoteException;
    Boolean registarPessoa (Pessoa pessoa) throws RemoteException;
    boolean addListaCandidata(String titulo, String lista) throws  RemoteException;
    ArrayList<TCPServer> getMesasVotos() throws RemoteException;
    void AddMesa(TCPServer mesa) throws RemoteException;
    void RemoveMesa(int i) throws RemoteException;
    ArrayList<String> getEleicoesDisponiveis(String user) throws RemoteException;
    ArrayList<String> getListaCandidatos(String eleicao) throws RemoteException;
    void sayHello() throws RemoteException;
}
