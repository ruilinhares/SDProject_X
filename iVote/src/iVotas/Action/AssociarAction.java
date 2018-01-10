package iVotas.Action;

import RMI.source.Classes.Eleicao;
import RMI.source.TCP.TCPServer;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class AssociarAction extends Action {
    public String nome,departamento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String execute() throws RemoteException{
        ArrayList<Eleicao> eleicoes=this.getiVotasBean().getListaEle();
        for(Eleicao ele:eleicoes){
            if(nome.toLowerCase().equals(ele.getTitulo().toLowerCase())){
                for(TCPServer mesa:this.getiVotasBean().getMesas()){
                    if (departamento.toLowerCase().equals(mesa.getDepartamento().getNome().toLowerCase())){
                        if(!mesa.getListaEleicoes().contains(mesa)){
                            mesa.addEleicao(ele);
                            return SUCCESS;
                        }
                    }
                }
            }
        }
        return ERROR;
    }
}
