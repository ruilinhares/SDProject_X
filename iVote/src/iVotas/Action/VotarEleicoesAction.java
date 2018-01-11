package iVotas.Action;

import org.apache.struts2.interceptor.SessionAware;

import java.util.ArrayList;

public class VotarEleicoesAction extends Action implements SessionAware{

    private String listaeleicoes = "";

    @Override
    public String execute() throws Exception {
        ArrayList<String> auxeleicoes;
        auxeleicoes = this.getiVotasBean().getEleicoes();

        if (!auxeleicoes.isEmpty()){
            for (String ele : auxeleicoes)
                listaeleicoes += ele +"\n";
        }
        else
            listaeleicoes = "*Sem eleições para votar*";
        return SUCCESS;
    }

    public String getListaeleicoes() {
        return listaeleicoes;
    }

    public void setListaeleicoes(String listaeleicoes) {
        this.listaeleicoes = listaeleicoes;
    }
}