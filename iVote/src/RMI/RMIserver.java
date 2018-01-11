package RMI;
import java.io.*;
import RMI.source.Classes.*;
import RMI.source.TCP.*;

import java.net.*;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class RMIserver extends UnicastRemoteObject implements AdminRMIimplements, TCPserverRMIimplements, RMIinterface {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private ArrayList<Eleicao> listaEleicoes;
    private ArrayList<Departamento> listaDepartamentos;
    private ArrayList<Pessoa> listaPessoas;
    private ArrayList<TCPServer> mesasVotos;
    private Map<String,String> users;
    private ArrayList<String> onlineUsers;

    private RMIserver() throws RemoteException {
        super();
        this.users = new HashMap<String, String>();
        this.onlineUsers = new ArrayList<>();
        this.listaEleicoes = new ArrayList<>();
        this.listaDepartamentos = new ArrayList<>();
        this.listaPessoas = new ArrayList<>();
        this.mesasVotos = new ArrayList<>();
        
        start();
        for (Pessoa p:listaPessoas){
            System.out.println(p.getNumeroUC());
            System.out.println(p.getNumeroCC());
            System.out.println(p.getPassword());
            this.users.put(p.getNumeroUC(),p.getPassword());
        }
        for (Departamento p:listaDepartamentos){
            System.out.println(p.getNome());
        }
        Calendar inicio = Calendar.getInstance();
        SimpleDateFormat inif = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        try {
            inicio.setTime(inif.parse("10-01-2018 18:20"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //listaEleicoes.get(0).setInicio(inicio);
        for (Eleicao e:listaEleicoes){
            e.Print();

        }



    }
    /*
    public boolean connectToFacebookAccount(String user, String code) throws RemoteException {
        FacebookConnection facebook = new FacebookConnection();
        String token = facebook.getUserLongTimeToken(code);
        String faceID = facebook.getUserID(token);
        boolean ret = false;


        return ret;
    }*/
//#############################################################################
    public boolean userLogin(String user, String password) throws RemoteException {
        System.out.println("Looking up " + user + "...");
        Boolean res = false;
        res = users.containsKey(user) && users.get(user).equals(password);
        if (res)
            onlineUsers.add(user);
        return res;
    }

    public boolean logged(String user) throws RemoteException {
        return !onlineUsers.contains(user);
    }

    public void logout(String user) throws RemoteException {
        this.onlineUsers.remove(user);
    }

    public void addUser(String user, String pass) throws RemoteException {
        this.users.put(user, pass);
    }

    public ArrayList<String> getAllUsers() throws RemoteException {
        System.out.println("Looking up all users...");
        return new ArrayList<String>(users.keySet());
    }

    public boolean addListaCandidata(String titulo, String lista) throws  RemoteException{
        for (Eleicao aux : listaEleicoes){
            if (aux.getTitulo().toUpperCase().equals(titulo.toUpperCase())) {
                aux.addListaCandidata(new ListaCandidata(lista));
                store();
                return true;
            }
        }
        return false;
    }
    
     public ArrayList<String> getEleicoesDisponiveis(String user){
        ArrayList<String> lista = new ArrayList<>();
        for (Eleicao ele : listaEleicoes)
            if (ele.verificaVotacao())
                for (Pessoa pessoa : ele.getListaEleitores())
                    if (user.equals(pessoa.getNumeroUC()))
                        lista.add(ele.getTitulo());
        return lista;
    }

    public ArrayList<String> getListaCandidatos(String eleicao){
        for (Eleicao ele : listaEleicoes)
            if (eleicao.equals(ele.getTitulo()))
                return ele.getCandidatos();

        return null;
    }

    public boolean votar(String eleicao, String lista, String user){
        for (Eleicao ele : listaEleicoes)
            if (eleicao.equals(ele.getTitulo())){
                for (Pessoa p : ele.getListaEleitores()){
                    if (user.equals(p.getNumeroUC())){
                        Voto voto = new Voto(p, new ListaCandidata(lista),null);
                        //ele.removeEleitor(voto);
                        ele.addVoto(voto);
                        store();
                        return true;
                    }
                }
            }
        return false;
    }

    
//#############################################################################
    synchronized public ArrayList<Eleicao> getListaEleicoes()  {
        return listaEleicoes;
    }

    synchronized public ArrayList<Departamento> getListaDepartamentos() {
        return listaDepartamentos;
    }

    synchronized public ArrayList<Pessoa> getListaPessoas() {
        return listaPessoas;
    }

    synchronized public ArrayList<TCPServer> getMesasVotos(){
        return mesasVotos;
    }

    synchronized public  void addDepartamento(Departamento dep){
        this.listaDepartamentos.add(dep);
        store();

    }


    synchronized public void sayHello() throws RemoteException {
        System.out.println("print do lado do servidor...!.");
    }

    // GET ELEICAO
    public Eleicao getEleicao(String eleicao) throws RemoteException{
        for (Eleicao aux : listaEleicoes)
            if (eleicao.equals(aux.getTitulo()))
                return aux;
        return null;
    }

    // GET Departamento
    public Departamento getDepartamento(String dep) throws RemoteException{
        for (Departamento aux : listaDepartamentos)
            if (dep.equals(aux.getNome()))
                return aux;
        return null;
    }

    synchronized public void RemoveDepartamento(int i){
        this.listaDepartamentos.remove(i);
        store();
    }

    synchronized public void AddDepartamento(Departamento dep){
        this.listaDepartamentos.add(dep);

    }
    // Ponto 1 - REGISTAR PESSOA
    synchronized public Boolean registarPessoa (Pessoa pessoa) throws RemoteException{

        for (Pessoa aux: this.listaPessoas)
            if (pessoa.getNumeroUC().equals(aux.getNumeroUC())
                    || pessoa.getNumeroCC().equals(aux.getNumeroUC()))
                return false;

        for (Departamento dep : listaDepartamentos)
            if ((pessoa.getDepartamento().getNome().toUpperCase()).equals(dep.getNome().toUpperCase())) {
                pessoa.inserirPessoaNaLista(listaPessoas, dep);
                store();
                return true;
            }

        return false;
    }

    // Ponto 3 - CRIAR ELEICOES
    @Override
    synchronized public void criarEleicao(Eleicao eleicao) throws RemoteException{
        listaEleicoes.add(eleicao);
        store();
    }

    @Override
    synchronized public void AddEleicao(Eleicao eleicao) throws RemoteException {
        this.listaEleicoes.add(eleicao);
        store();
    }

     @Override
    synchronized public void RemoveEleicao(int i){
        this.listaEleicoes.remove(i);
        store();
    }

    // Ponto 5 - GERIR MESAS DE VOTOS
    @Override
    synchronized public void RemoveMesa(int i){
        this.mesasVotos.remove(i);
        store();
    }

    //-----TCPserver-client-------------------------------------

    // ABRIR MESA DE VOTO
    @Override
    synchronized public Departamento abrirMesaVoto(String dep){
        for (TCPServer aux : mesasVotos)
            if ((dep.toUpperCase()).equals(aux.getDepartamento().getNome().toUpperCase()) && !aux.getEstadoMesa()) {
                aux.setEstadoMesa(true);
                store();
                return aux.getDepartamento();
            }
        return null;
    }

    // ABRIR MESA DE VOTO
    @Override
    synchronized public void fecharMesaVoto(String dep){
        for (TCPServer aux : mesasVotos)
            if (dep.equals(aux.getDepartamento().getNome()) && aux.getEstadoMesa()) {
                aux.setEstadoMesa(false);
                store();
                return;
            }
    }

    // Ponto 6 IDENTIFICAR UM ELEITOR
    @Override
    synchronized public Pessoa identificarEleitor(String numerouc) throws RemoteException{

        for (Pessoa pessoa: this.listaPessoas)
            if (pessoa.getNumeroUC().equals(numerouc))
                return pessoa;
        return null;
    }

    // devolver lista de Eleicoes que o eleitor pode votar
    @Override
    synchronized public ArrayList<Eleicao> identificarEleicoes(Pessoa eleitor, Departamento dep){
        ArrayList<Eleicao> eleicoes = new ArrayList<>();
        for (TCPServer mesaux : this.mesasVotos)
            if ((mesaux.getDepartamento().getNome().toUpperCase()).equals(dep.getNome().toUpperCase()))
                for (Eleicao aux: mesaux.getListaEleicoes())
                    for (Eleicao ele : listaEleicoes)
                        if (ele.eleicaoEquals(aux))
                            if (ele.verificaVotacao())
                                for (Pessoa pessoa: ele.getListaEleitores())
                                    if (pessoa.getNumeroCC().equals(eleitor.getNumeroCC()))
                                        eleicoes.add(ele);
        return eleicoes;
    }

    //adiciona uma mesa criada a lista de mesas de voto
    @Override
    synchronized public void AddMesa(TCPServer mesa){
        mesasVotos.add(mesa);
        store();
    }

    synchronized public boolean AlteraPessoa(String uc ,String nome,String morada,String telemovel, String val,String pass,String cc){
        for (Pessoa p:listaPessoas){
            if (p.getNumeroUC().equals(uc)){
                  if (nome!=null)
                      p.setNome(nome);
                  if (morada!=null)
                      p.setMorada(morada);
                  if (telemovel!=null)
                      p.setTelemovel(telemovel);
                  if (val!=null)
                      p.setValidade(val);
                  if (pass!=null){
                      p.setPassword(pass);
                      this.users=new HashMap<String, String>();
                      this.users.put(p.getNumeroUC(),p.getPassword());
                  }
                  if (cc!=null)
                      p.setNumeroCC(cc);
                  store();
                  return true;
          }

        }
        return false;
    }

    @Override
    synchronized public Eleicao escolherEleicao(Pessoa eleitor, Departamento dep, int i){
        int count = 0;
        for (TCPServer mesaux : this.mesasVotos)
            if (mesaux.getDepartamento().getNome().equals(dep.getNome()))
                for (Eleicao aux: mesaux.getListaEleicoes())
                    if (aux.verificaVotacao())
                        for (Pessoa pessoa: aux.getListaEleitores())
                            if (pessoa.getNumeroCC().equals(eleitor.getNumeroCC()) && (++count)==i)
                                return aux;
        return null;
    }

    // devolver a lista de Candidatos correspondente ao eleitor
    @Override
    synchronized public ArrayList<ListaCandidata> getListaCandidatas(Eleicao eleicao, Pessoa eleitor) throws RemoteException{
        for (Pessoa pessoa: this.listaPessoas)
            if (pessoa.getNumeroCC().equals(eleitor.getNumeroCC()))
                for (Eleicao auxEleicao : this.listaEleicoes)
                    if (auxEleicao.eleicaoEquals(eleicao))
                        return auxEleicao.getListaCandidatos(pessoa);

        return null;
    }

    // Ponto 8 (VALIDAR O VOTO DO ELEITOR)
    @Override
    synchronized public void votacaoEleitor(Eleicao eleicao, Voto voto) throws RemoteException {
        for (Pessoa pessoa: this.listaPessoas)
            if (pessoa.getNumeroCC().equals(voto.getEleitor().getNumeroCC()))
            for (Eleicao auxEleicao : this.listaEleicoes)
                if (auxEleicao.eleicaoEquals(eleicao)) {
                    auxEleicao.removeEleitor(voto);
                    auxEleicao.addVoto(voto);

                }
    store();
    }

//---UDP----------------------------------------------------

    private void udpServerON(){
            new Thread(new UDPServer()).start();
            }

//---Base-DadosAction---------------------------------------------

    public void start() {
        ObjectInputStream objectinputstream1 = null;
        ObjectInputStream objectinputstream2 = null;
        ObjectInputStream objectinputstream3 = null;
        ObjectInputStream objectinputstream4 = null;
        try {

            FileInputStream streamIn = new FileInputStream("pessoas.ser");
            objectinputstream1 = new ObjectInputStream(streamIn);
            ArrayList<Pessoa> people = (ArrayList<Pessoa>) objectinputstream1.readObject();
            this.listaPessoas=people;
            streamIn = new FileInputStream("departamentos.ser");
            objectinputstream1 = new ObjectInputStream(streamIn);
            ArrayList<Departamento> deps = (ArrayList<Departamento>) objectinputstream1.readObject();
            this.listaDepartamentos=deps;
            streamIn = new FileInputStream("eleicoes.ser");
            objectinputstream1 = new ObjectInputStream(streamIn);
            ArrayList<Eleicao> elections = (ArrayList<Eleicao>) objectinputstream1.readObject();
            this.listaEleicoes=elections;
            streamIn = new FileInputStream("mesas.ser");
            objectinputstream4 = new ObjectInputStream(streamIn);
            ArrayList<TCPServer> mesas = (ArrayList<TCPServer>) objectinputstream4.readObject();
            this.mesasVotos= mesas;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (objectinputstream1 != null && objectinputstream2 != null && objectinputstream3 != null && objectinputstream4 == null) {
                try {
                    objectinputstream1.close();
                    objectinputstream2.close();
                    objectinputstream3.close();
                    objectinputstream4.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void store(){
        ObjectOutputStream oos1=null,oos2=null,oos3=null,oos4=null;
        FileOutputStream fout1,fout2,fout3,fout4;
        try{
            fout1 = new FileOutputStream("pessoas.ser");
            oos1 = new ObjectOutputStream(fout1);
            oos1.writeObject(listaPessoas);
            fout2 = new FileOutputStream("departamentos.ser");
            oos2 = new ObjectOutputStream(fout2);
            oos2.writeObject(listaDepartamentos);
            fout3 = new FileOutputStream("eleicoes.ser");
            oos3 = new ObjectOutputStream(fout3);
            oos3.writeObject(listaEleicoes);
            fout4 = new FileOutputStream("mesas.ser");
            oos4 = new ObjectOutputStream(fout4);
            oos4.writeObject(mesasVotos);
        }catch (IOException e) {
            System.out.println("IOEXCEPTION");
        }finally {
            if(oos1  != null && oos2!=null && oos3!=null && oos4!=null){
                try {
                oos1.close();
                oos2.close();
                oos3.close();
                oos4.close();
                } catch (IOException e) {
                e.printStackTrace();
                }
            }
        }
    }

// =========================================================
    public static void main(String args[]) throws RemoteException {

        RMIserver rmiServer = null;
        try {
            Registry rmiRegistry = LocateRegistry.createRegistry(6789);
            rmiServer = new RMIserver();
            for (TCPServer m:rmiServer.mesasVotos)
                m.setEstadoMesa(false);
            rmiRegistry.rebind("HelloRMI", rmiServer); // RMI primario iniciado
            rmiServer.udpServerON();
            System.out.println("RMI Primário Server ready.");

        } catch (RemoteException re) { //AccessException
            System.out.println("Remote Exception no RMI Server: " + re.getMessage()+"\nRMI Backup...");
            // Criar ligação com RMI primário
            try {
                //  -------------UDP-Client--------------------
                DatagramSocket clientSocket = null;
                try{
                    clientSocket = new DatagramSocket();
                    while (true) {
                        InetAddress IPAddress = InetAddress.getByName("localhost");
                        byte[] sendData;
                        byte[] receiveData = new byte[1024];
                        sendData = "ok?".getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 6000);
                        clientSocket.send(sendPacket); // set timeout de resposta
                        clientSocket.setSoTimeout(5000); // receber resposta no timeout
                        try {
                            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                            clientSocket.receive(receivePacket);
                            Thread.sleep(5000); // tempo de espera da resposta
                            System.out.println("RMI primario:" + new String(receivePacket.getData(), 0, receivePacket.getLength()));
                        } catch (SocketTimeoutException e) { // socket timeout exception                            // RMI Server primário foi a baixo
                            // RMI Backup assume o controlo
                            clientSocket.close();
                            System.out.println("Timeout ultrapassado! " + e.getMessage());
                            try {
                                rmiServer = new RMIserver();
                                Registry rmiRegistry = LocateRegistry.createRegistry(6789); // RMI Backup iniciado
                                rmiRegistry.rebind("HelloRMI", rmiServer);
                                rmiServer.udpServerON();
                                System.out.println("RMI Backup Server ready.");
                            } catch (RemoteException re2) {
                            System.out.println(re2.getMessage());
                            }
                        }
                    } //while
                }catch (SocketException e){System.out.println("Socket: " + e.getMessage());
                }catch (IOException e){System.out.println("IO: " + e.getMessage());
                }finally {if(clientSocket != null) clientSocket.close();}
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

// UDP-SERVER====================================================================

class UDPServer implements Runnable
{
    UDPServer() {}

    public void run()
    {
        DatagramSocket serverSocket = null;
        try{
            serverSocket = new DatagramSocket(6000);
            byte[] receiveData = new byte[1024];
            byte[] sendData;
            while(true)
            {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String sentence = new String( receivePacket.getData());
                sendData = "im ok".getBytes();
                DatagramPacket sendPacket =
                        new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
                serverSocket.send(sendPacket);
            } //while
        }catch (SocketException e){
            System.out.println("Socket: " + e.getMessage());

        }catch (IOException e) {System.out.println("IO: " + e.getMessage());
        }finally {if(serverSocket != null) serverSocket.close();}
    }
}
