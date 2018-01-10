package iVotas.Action;

import RMI.source.Classes.Eleicao;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;

public class DadosAction extends Action{
    public String local,nome,titulo;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String execute(){
        if(nome!=null && !nome.equals("")){
            try {
                for(Eleicao ele:this.getiVotasBean().getListaEle()) {
                    if(nome.toLowerCase().equals(ele.getTitulo())){
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                        String ini = sdf.format(ele.getInicio().getTime());
                        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                        String fim = sdf.format(ele.getFim().getTime());
                        local="TITULO: "+ele.getTitulo()+"\nDESCRICAO: "+ele.getDescricao()+"\nDATA DE INICIO: "+ini
                                +"\nDATA DE FINAL: "+fim;
                        if(!ele.Dep().equals(""))
                            local+="\nDEPARTAMENTO: "+ele.Dep();
                        return SUCCESS;
                    }
                }

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        return ERROR;
    }
}
