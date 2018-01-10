package iVotas.Action;

import RMI.source.Classes.ListaCandidata;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CriaEleicaoAction extends Action{
    public String titulo=null,tipo=null,descricao=null,datai=null,dataf=null,dep=null;

    public String execute() throws RemoteException {
        Calendar inicio = Calendar.getInstance();
        SimpleDateFormat inif = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Calendar fim = Calendar.getInstance();
        SimpleDateFormat fimf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        System.out.println(descricao);
        try {
            inicio.setTime(inif.parse(datai));
            fim.setTime(fimf.parse(dataf));
        } catch (ParseException e) {
            e.printStackTrace();
            return ERROR;
        }
        if (tipo.toLowerCase().equals("nucleo") && dep != null) {
            if (this.getiVotasBean().encontraDepartamento(dep) != null) {
                if (this.getiVotasBean().CriaNucleo(titulo, descricao, inicio, fim, this.getiVotasBean().encontraDepartamento(dep)))
                return SUCCESS;
            }
        } else if (tipo.toLowerCase().equals("dg")) {
            ArrayList<ListaCandidata> EstudantesCandidatos = new ArrayList<>();
            ArrayList<ListaCandidata> FuncionariosCandidatos = new ArrayList<>();
            ArrayList<ListaCandidata> DocentesCandidatos = new ArrayList<>();
            if (this.getiVotasBean().CriaDG(titulo, descricao, inicio, fim, EstudantesCandidatos, DocentesCandidatos, FuncionariosCandidatos))
                return SUCCESS;
        }
        return ERROR;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDecricao() {
        return descricao;
    }

    public void setDecricao(String decricao) {
        this.descricao = decricao;
    }

    public String getDatai() {
        return datai;
    }

    public void setDatai(String datai) {
        this.datai = datai;
    }

    public String getDataf() {
        return dataf;
    }

    public void setDataf(String dataf) {
        this.dataf = dataf;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


}
