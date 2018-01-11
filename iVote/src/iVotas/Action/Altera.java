package iVotas.Action;

import RMI.source.Classes.*;
import org.apache.struts2.interceptor.SessionAware;

import java.rmi.RemoteException;

public class Altera extends Action {
    private static final long serialVersionUID = 4L;

    private String uc = null;
    private String nome = null;
    private String cc = null;
    private String validade = null;
    private String pass = null;
    private String telemovel = null;
    private String morada = null;


    @Override
    public String execute() throws RemoteException {
        if (this.getiVotasBean().altera(uc,nome,morada,telemovel,validade,pass,cc))
            return SUCCESS;
        return ERROR;
    }

    public void setUc(String uc) {
        this.uc = uc;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setTelemovel(String telemovel) {
        this.telemovel = telemovel;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }


}

