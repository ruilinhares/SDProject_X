package iVotas.Action;

public class FacebookAction extends Action {

    private static final long serialVersionUID = 1L;

    private String code;
    private String url;
    private String host;

    private boolean result;

    private String user;

    public String connectContaFacebook() {

        //this.result = this.getIbeiBean().connectToFacebookAccount(this.code);
        if(result) {
            return SUCCESS;
        } else {
            return ERROR;
        }

    }


}
