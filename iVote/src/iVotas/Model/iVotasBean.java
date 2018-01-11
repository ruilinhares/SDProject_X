package iVotas.Model;

import RMI.RMIinterface;
import RMI.source.Classes.*;
import RMI.source.TCP.TCPServer;


import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Calendar;

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


    public boolean altera(String uc ,String nome,String morada,String telemovel, String val,String pass,String cc){
        try{
            return this.serverRMI.AlteraPessoa(uc ,nome,morada,telemovel,val,pass,cc);
        }catch (RemoteException e){
            e.printStackTrace();
        }
        return false;
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

    public ArrayList<Eleicao> lista() {
        ArrayList<Eleicao> lista;
        while (true) {
            try {
                lista = this.serverRMI.getListaEleicoes();
                break;
            } catch (RemoteException ignored) {
                this.ligarRMI();
            }
        }
        return lista;
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

    public ArrayList<String> getEleicoes() throws RemoteException{
        return this.serverRMI.getEleicoesDisponiveis(this.username);
    }

    public ArrayList<String> getCandidatos(String ele) throws RemoteException{
        return this.serverRMI.getListaCandidatos(ele);
    }

    public boolean votar(String ele, String lista) throws RemoteException{
        return serverRMI.votar(ele,lista,this.username);
    }



    public Departamento encontraDepartamento(String nome){
        ArrayList<Departamento> listaDep;
        while (true) {
            try {
                listaDep = this.serverRMI.getListaDepartamentos();
                break;
            } catch (RemoteException ignored) {
                this.ligarRMI();
            }
        }
        for (Departamento dep:listaDep) {
            if (dep.getNome().toLowerCase().equals(nome.toLowerCase()))
                return dep;
        }
        return null;
    }

    public boolean CriaNucleo(String titulo,String descricao,Calendar ini,Calendar fim,Departamento dep) throws RemoteException {
        Nucleo election = new Nucleo(titulo,descricao,ini,fim,dep);
        this.serverRMI.AddEleicao(election);
        return true;
    }

    public boolean CriaDG(String titulo,String descricao,Calendar ini,Calendar fim,ArrayList<ListaCandidata> EstudantesCandidatos,ArrayList<ListaCandidata> DocentesCandidatos, ArrayList<ListaCandidata>FuncionariosCandidatos) throws RemoteException {
        ArrayList<Pessoa> listaP;
        while (true) {
            try {
                listaP = this.serverRMI.getListaPessoas();
                break;
            } catch (RemoteException ignored) {
                this.ligarRMI();
            }
        }
        DirecaoGeral election = new DirecaoGeral(titulo,descricao,ini,fim,EstudantesCandidatos,DocentesCandidatos,FuncionariosCandidatos,listaP);
        this.serverRMI.AddEleicao(election);
        return true;
    }

    public ArrayList<Eleicao> getListaEle() throws RemoteException{
        return this.serverRMI.getListaEleicoes();
    }

    public Eleicao RemoveEle(String nome) throws RemoteException{
        Eleicao ele;
        ArrayList<Eleicao> lista;
        int index;
        while (true) {
            try {
                lista = this.serverRMI.getListaEleicoes();
                break;
            } catch (RemoteException ignored) {
                this.ligarRMI();
            }
        }
        for (int i=0;i<lista.size();i++){
            if (nome.toLowerCase().equals(lista.get(i).getTitulo())){
                index=i;
                ele=lista.get(i);
                this.serverRMI.RemoveEleicao(index);
                return ele;
            }

        }
        return null;
    }

    public boolean addEleicao(Eleicao ele){
        while (true) {
            try {
                this.serverRMI.AddEleicao(ele);
                return true;
            } catch (RemoteException ignored) {
                this.ligarRMI();
            }
        }

    }


    public ArrayList<Eleicao> escolheEle(String eleitor) throws RemoteException {
        Pessoa p = this.serverRMI.identificarEleitor(eleitor);
        ArrayList<Eleicao> eleicoes;
        try {
            eleicoes = this.serverRMI.identificarEleicoes(p, p.getDepartamento()); // eleicoes disponiveis para o leitor votar
            return eleicoes;
        } catch (RemoteException e) { // fail over
            this.ligarRMI();
        }
        return null;

    }

    public Pessoa eleitor(String n) throws RemoteException{
        return this.serverRMI.identificarEleitor(n);
    }

    public ArrayList<TCPServer> getMesas() throws RemoteException{
        return this.serverRMI.getMesasVotos();
    }

}


