package RMI;

import RMI.source.Classes.*;
import RMI.source.TCP.TCPServer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RMIinterface extends Remote {


    boolean logged(String user) throws RemoteException;
    boolean userLogin(String name, String password) throws RemoteException;
    void logout(String user) throws RemoteException;
    void addUser(String user, String pass) throws RemoteException;
    ArrayList<Departamento> getListaDepartamentos() throws RemoteException;
    ArrayList<Pessoa> getListaPessoas() throws RemoteException;
    void addDepartamento(Departamento dep) throws RemoteException;
    ArrayList<String> getAllUsers() throws RemoteException;
    boolean votar(String eleicao, String lista, String user) throws RemoteException;
    void RemoveDepartamento(int i) throws RemoteException;
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
    public void AddEleicao(Eleicao eleicao) throws RemoteException;
    void RemoveEleicao(int i) throws RemoteException;
    public Pessoa identificarEleitor(String numerouc) throws RemoteException;
    public ArrayList<Eleicao> identificarEleicoes(Pessoa eleitor, Departamento dep) throws RemoteException;

}
