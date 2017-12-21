package iVotas.Action;

import java.rmi.RemoteException;

public class VotolocalAction extends Action {

    private String uc;
    private StringBuilder local = new StringBuilder();

    @Override
    public String execute(){
        if (this.uc!=null && !this.uc.equals("")){
            try {
                local = this.getiVotasBean().listaLocaisVoto(uc);
                local.append("treta");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return SUCCESS;
        }
        this.local.append("*ERRO*");
        return SUCCESS;
    }

    public String getUc() {
        return uc;
    }

    public void setUc(String uc) {
        this.uc = uc;
    }

    public StringBuilder getLocal() {
        return local;
    }

    public void setLocal(StringBuilder local) {
        this.local = local;
    }
}
