package iVotas.Action;

import RMI.source.Classes.Eleicao;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AlteraEleicaoAction extends Action {
    public String nome=null,titulo=null,datai=null,dataf=null,descricao=null;

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

    public String getDatai() {
        return datai;
    }

    public void setDatai(String datai) {
        this.datai = datai;
    }

    public String getDataf() {
        return dataf;
    }

    public void setDataf(String dataf) {
        this.dataf = dataf;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String execute() throws RemoteException{
        Eleicao editada;
        if(nome!=null && !nome.equals("")) {
            try {
                for (Eleicao ele : this.getiVotasBean().getListaEle()) {
                    if (nome.toLowerCase().equals(ele.getTitulo())) {
                        if (!ele.verificaVotacao() && ele.vericaVotacaoPassou()) {
                            System.out.println("hey");
                            editada = this.getiVotasBean().RemoveEle(nome);
                            Calendar inicio = Calendar.getInstance();
                            SimpleDateFormat inif = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                            Calendar fim = Calendar.getInstance();
                            SimpleDateFormat fimf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                            try {
                                inicio.setTime(inif.parse(datai));
                                fim.setTime(fimf.parse(dataf));
                            } catch (ParseException e) {
                                e.printStackTrace();
                                return ERROR;
                            }
                            ele.setDescricao(descricao);
                            ele.setTitulo(titulo);
                            ele.setInicio(inicio);
                            ele.setFim(fim);
                            this.getiVotasBean().addEleicao(ele);
                            return SUCCESS;
                        }
                    }
                }
            }
            catch (RemoteException e){
                e.printStackTrace();
            }
        }
        return ERROR;
    }
}


