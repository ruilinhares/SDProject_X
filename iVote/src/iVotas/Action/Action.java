package iVotas.Action;

import com.opensymphony.xwork2.ActionSupport;
import iVotas.Model.iVotasBean;
import org.apache.struts2.interceptor.SessionAware;

import java.rmi.RemoteException;
import java.util.Map;

public class Action extends ActionSupport implements SessionAware {

    private static final long serialVersionUID = 4L;
    private Map<String, Object> session;

    public iVotasBean getiVotasBean() throws RemoteException {
        if(!session.containsKey("iVotasBean"))
            this.setiVotasBean(new iVotasBean());
        return (iVotasBean) session.get("iVotasBean");
    }

    public void setiVotasBean(iVotasBean bean) {
        this.session.put("iVotasBean", bean);
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
