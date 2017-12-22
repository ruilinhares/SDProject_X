package iVotas.Action;

public class LogoutAction extends Action {
    private static final long serialVersionUID = 1L;

    public String execute() throws Exception{
        this.getiVotasBean().logout((String)this.session.get("username"));
        this.session.clear();
        return SUCCESS;
    }
}
