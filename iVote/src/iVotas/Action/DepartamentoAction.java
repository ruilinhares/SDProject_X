package iVotas.Action;

import java.rmi.RemoteException;

public class DepartamentoAction extends Action {

    private String depnomeC = null;
    private String depnomeA = null;

    public String execute() throws RemoteException{
        if(depnomeC!=null && !depnomeC.equals("")){
            System.out.println(depnomeC);
            if (this.getiVotasBean().criaDep(depnomeC))
                return SUCCESS;
        }
        if(depnomeA!=null && !depnomeA.equals("")){
            System.out.println(depnomeA);
            if (this.getiVotasBean().apagaDep(depnomeA))
                return SUCCESS;
        }
        return LOGIN;
    }

    public String executeApaga() throws RemoteException{
        if(depnomeA!=null && !depnomeA.equals("")){
            System.out.println(depnomeA);
            if (this.getiVotasBean().apagaDep(depnomeA))
                return SUCCESS;
        }
        return LOGIN;
    }

    public String getDepnomeC() {
        return depnomeC;
    }

    public void setDepnomeC(String depnomeC) {
        this.depnomeC = depnomeC;
    }

    public String getDepnomeA() {
        return depnomeA;
    }

    public void setDepnomeA(String depnomeA) {
        this.depnomeA = depnomeA;
    }
}