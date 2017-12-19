package iVotas.Action;

import java.util.ArrayList;
import java.util.Map;

public class LoginAction extends Action {

    private static final long serialVersionUID = 1L;
    private Map<String, Object> session;

    private String username = null;
    private String password = null;


    public String execute() {
        if (this.username != null && this.password != null && !this.username.equals("") && !this.password.equals("")) {
            if (this.getiVotasBean().login(this.username, this.password)) {
                this.getiVotasBean().setUsername(this.username);
                this.setiVotasBean(getiVotasBean());
                //this.session.put("username", this.username);
                //this.session.put("loggedin", true);
                return SUCCESS;
            } else if(this.username.equals("admin") && this.password.equals("admin")){
                return "administration";
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

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
