package iVotas.Action;

import org.apache.struts2.interceptor.SessionAware;

import java.util.ArrayList;
import java.util.List;

public class VotarAction extends Action implements SessionAware {

    private String listaeleicoes = "";
    private String listacandidatos = "";


    private String eleicao = null;
    private String lista = null;


    public String comboCandidatos() throws Exception {
        ArrayList<String> auxlistas = new ArrayList<>();
        if (eleicao!=null){
            System.out.println(eleicao);
            auxlistas = this.getiVotasBean().getCandidatos(eleicao);
            for (String li : auxlistas)
                listacandidatos += li+"\n";
        }
        return "none";
    }

    @Override
    public String execute() throws Exception {
        if (eleicao!=null && lista!=null){
            System.out.println(eleicao);
            System.out.println(lista);
            if (this.getiVotasBean().votar(eleicao,lista))
                return SUCCESS;
        }
        return ERROR;
    }

    public String getEleicao() {
        return eleicao;
    }

    public void setEleicao(String eleicao) {
        this.eleicao = eleicao;
    }

    public String getLista() {
        return lista;
    }

    public void setLista(String lista) {
        this.lista = lista;
    }

    public String getListaeleicoes() {
        return listaeleicoes;
    }

    public void setListaeleicoes(String listaeleicoes) {
        this.listaeleicoes = listaeleicoes;
    }

    public String getListacandidatos() {
        return listacandidatos;
    }

    public void setListacandidatos(String listacandidatos) {
        this.listacandidatos = listacandidatos;
    }
}
