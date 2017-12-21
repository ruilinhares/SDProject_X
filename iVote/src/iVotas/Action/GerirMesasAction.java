package iVotas.Action;

public class GerirMesasAction extends Action {
    private String mesaCria = null;
    private String mesaApaga = null;

    @Override
    public String execute() throws Exception {
        if (mesaCria!=null && !mesaCria.equals("")){
            if (this.getiVotasBean().criaMesa(mesaCria))
                return SUCCESS;
        }
        else if (mesaApaga!=null && !mesaApaga.equals("")){
            if (this.getiVotasBean().apagaMesa(mesaApaga))
                return SUCCESS;
        }
        return LOGIN;
    }

    public String getMesaCria() {
        return mesaCria;
    }

    public void setMesaCria(String mesaCria) {
        this.mesaCria = mesaCria;
    }

    public String getMesaApaga() {
        return mesaApaga;
    }

    public void setMesaApaga(String mesaApaga) {
        this.mesaApaga = mesaApaga;
    }
}
