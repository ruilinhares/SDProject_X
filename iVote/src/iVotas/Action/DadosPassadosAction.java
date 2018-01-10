package iVotas.Action;

import RMI.source.Classes.Eleicao;
import RMI.source.Classes.ListaCandidata;
import RMI.source.Classes.Voto;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DadosPassadosAction extends Action {
    public String local,nome,titulo;

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

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

    public int conta(String lista, ArrayList<Voto> votos){
        int i=0;
        for(Voto voto:votos){
            if(voto.getTipo().equals(lista))
                i++;
        }
        return i;
    }

    public String execute(){
        if(nome!=null && !nome.equals("")){
            try {
                for(Eleicao ele:this.getiVotasBean().getListaEle()) {
                    if(nome.toLowerCase().equals(ele.getTitulo())){
                        String resultados="\nRESULTADOS:";
                        if(ele.getListasD()!=null){
                            resultados+="\nDOCENTES:";
                            int i,total=ele.getVotosD().size();
                            for (ListaCandidata l:ele.getListasD()) {
                                i =conta(l.getNome(),ele.getVotosD());
                                if(total!=0)
                                    resultados+="\n"+l.getNome()+" "+i+" "+i/total+"%";
                            }
                        }
                        if(ele.getListasE()!=null){
                            resultados+="\nESTUDANTES:";
                            int i,total=ele.getVotosE().size();
                            for (ListaCandidata l:ele.getListasE()) {
                                i =conta(l.getNome(),ele.getVotosE());
                                if(total!=0)
                                    resultados+="\n"+l.getNome()+" "+i+" "+i/total+"%";
                            }
                        }
                        if(ele.getListasF()!=null){
                            resultados+="\nFUNCIONARIOS:";
                            int i,total=ele.getVotosF().size();
                            for (ListaCandidata l:ele.getListasF()) {
                                i =conta(l.getNome(),ele.getVotosF());
                                if(total!=0)
                                    resultados+="\n"+l.getNome()+" "+i+" "+i/total+"%";
                            }
                        }
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                        String ini = sdf.format(ele.getInicio().getTime());
                        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                        String fim = sdf.format(ele.getFim().getTime());
                        local="TITULO: "+ele.getTitulo()+"\nDESCRICAO: "+ele.getDescricao()+"\nDATA DE INICIO: "+ini
                                +"\nDATA DE FINAL: "+fim;
                        if(!ele.Dep().equals(""))
                            local+="\nDEPARTAMENTO: "+ele.Dep();
                        local+=resultados;
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
