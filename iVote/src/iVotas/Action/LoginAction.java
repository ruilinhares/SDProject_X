package iVotas.Action;

import com.opensymphony.xwork2.ActionSupport;
import iVotas.Model.iVotasBean;
import org.apache.struts2.interceptor.SessionAware;

import java.rmi.RemoteException;
import java.util.Map;

public class LoginAction extends Action implements SessionAware{

    private static final long serialVersionUID = 4L;
    private String username = null;
    private String password = null;

    @Override
    public String execute() throws RemoteException {
        if (this.username != null && this.password != null && !this.username.equals("") && !this.password.equals("")) {
            this.getiVotasBean().setUsername(this.username);
            this.getiVotasBean().setPassword(this.password);
            if (this.getiVotasBean().userLogin()) {
                this.session.put("username", this.username);
                this.session.put("loggedin", true);
                return SUCCESS;
            } else if(this.username.equals("admin") && this.password.equals("admin")){
                this.session.put("username", this.username);
                this.session.put("loggedin", true);
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

    public String logout() throws Exception{
        this.getiVotasBean().logout((String)this.session.get("username"));
        this.session.clear();
        return SUCCESS;
    }
}
