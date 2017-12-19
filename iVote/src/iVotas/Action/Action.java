package iVotas.Action;

import com.opensymphony.xwork2.ActionSupport;
import iVotas.Model.iVotasBean;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Map;

public class Action extends ActionSupport implements Serializable {

    private static final long serialVersionUID = 1L;
    private Map<String, Object> session;

    public iVotasBean getiVotasBean() {
        boolean ver = false;
        if (!session.containsKey("iVotasBean")) {
            while(!ver){
                try {
                    this.setiVotasBean(new iVotasBean());
                    ver = true;
                } catch (RemoteException re){
                    System.out.println("Could not create a new iBeiBean");
                }
            }
        }
        return (iVotasBean) session.get("iVotasBean");
    }

    public void setSession(Map<String, Object> session){
        this.session = session;
    }

    public void setiVotasBean(iVotasBean iV){
        this.session.put("iVotasBean",iV);
    }
}
