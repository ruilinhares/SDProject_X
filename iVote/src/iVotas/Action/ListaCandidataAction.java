package iVotas.Action;

import java.rmi.RemoteException;

public class ListaCandidataAction extends Action {

    private String lista = null;
    private String eleicao = null;

    @Override
    public String execute() {
        if (lista!=null && eleicao!=null && !lista.equals("") && !eleicao.equals("")){
            try {
                if (this.getiVotasBean().addLista(eleicao,lista))
                    return SUCCESS;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return LOGIN;
    }

    public String getLista() {
        return lista;
    }

    public void setLista(String lista) {
        this.lista = lista;
    }

    public String getEleicao() {
        return eleicao;
    }

    public void setEleicao(String eleicao) {
        this.eleicao = eleicao;
    }
}
