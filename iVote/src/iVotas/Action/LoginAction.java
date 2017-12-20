package iVotas.Action;

import com.opensymphony.xwork2.ActionSupport;
import iVotas.Model.iVotasBean;
import org.apache.struts2.interceptor.SessionAware;

import java.rmi.RemoteException;
import java.util.Map;

public class LoginAction extends ActionSupport implements SessionAware {

    private static final long serialVersionUID = 4L;
    private Map<String, Object> session;
    private String username = null;
    private String password = null;

    @Override
    public String execute() throws RemoteException {
        System.out.println(username+"|"+password);
        if (this.username != null && this.password != null && !this.username.equals("") && !this.password.equals("")) {
            System.out.println(this.getiVotasBean().logged(this.username));
            if (this.getiVotasBean().logged(this.username)) {
                this.getiVotasBean().setUsername(this.username);
                this.getiVotasBean().setPassword(this.password);
                this.session.put("username", this.username);
                this.session.put("loggedin", true);
                return SUCCESS;
            } else if(this.username.equals("admin") && this.password.equals("admin")){
                return "administrador";
            }
        }
        return LOGIN;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

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
