package iVotas.Action;

import java.rmi.RemoteException;

public class LoginAction extends Action {

    private static final long serialVersionUID = 4L;
    private String username = null;
    private String password = null;

    @Override
    public String execute() throws RemoteException {
        if (this.username != null && this.password != null && !this.username.equals("") && !this.password.equals("")) {
            if (this.getiVotasBean().userLogin(this.username,this.password)) {
                this.getiVotasBean().setUsername(this.username);
                this.getiVotasBean().setPassword(this.password);
                this.setiVotasBean(getiVotasBean());
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
}
