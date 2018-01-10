package iVotas.Action;

import RMI.source.Classes.*;

import java.rmi.RemoteException;

public class RegistarAction extends Action{
    private static final long serialVersionUID = 4L;

    private String uc = null;
    private String nome = null;
    private String cc = null;
    private String validade = null;
    private String pass = null;
    private String telemovel = null;
    private String morada = null;
    private String dep = null;
    private String tipo = null;

    @Override
    public String execute() throws RemoteException {

        if (uc!=null && cc!=null && validade!=null && pass!=null && telemovel!=null && morada!=null && dep!=null && tipo!=null) {
            Departamento departamento = this.getiVotasBean().getDepartamento(dep);
            if (departamento != null) {
                System.out.println(tipo);
                Pessoa novapessoa;
                switch (tipo.toUpperCase()) {
                    case "ESTUDANTE":
                        novapessoa = new Estudante(nome, uc, telemovel, morada, pass, departamento, cc, validade);
                        break;
                    case "DOCENTE":
                        novapessoa = new Docente(nome, uc, telemovel, morada, pass, departamento, cc, validade);
                        break;
                    case "FUNCIONARIO":
                        novapessoa = new Funcionario(nome, uc, telemovel, morada, pass, departamento, cc, validade);
                        break;
                    default:
                        return ERROR;
                }
                if (this.getiVotasBean().registarPessoa(novapessoa))
                    return SUCCESS;
            } else
                return ERROR;
        }
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

    public void setDep(String dep) {
        this.dep = dep;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

