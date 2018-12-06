package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CondominioDAO;
import br.edu.ifsul.dao.RecursoDAO;
import br.edu.ifsul.modelo.Recurso;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "controleRecurso")
@SessionScoped
public class ControleRecurso implements Serializable {

    private RecursoDAO<Recurso> dao;
    private Recurso objeto;
    private CondominioDAO daoCondominio;
    
    public ControleRecurso(){
        dao = new RecursoDAO<>();
        daoCondominio = new CondominioDAO();
    }
    
    public String listar(){
        return "/privado/recurso/listar?faces-redirect=true";
    }
    
    public String novo(){
        setObjeto(new Recurso());
        return "formulario?faces-redirect=true";
    }
    
    public String salvar(){
        boolean persistiu;
        if(getObjeto().getId() == null){
            persistiu = getDao().persist(getObjeto());
        } else {
            persistiu = getDao().merge(getObjeto());
        }
        if (persistiu){
            Util.mensagemInformacao(getDao().getMensagem());
            return "listar?faces-redirect=true";
        } else {
            Util.mensagemErro(getDao().getMensagem());
            return "formulario?faces-redirect=true";
        }
    }
    
    public String cancelar(){
        return "listar?faces-redirect=true";
    }
    
    public String editar(Integer id){
        try {
            setObjeto(getDao().localizar(id));
            return "formulario?faces-redirect=true";
        } catch (Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
            return "listar?faces-redirect=true";
        }
    }
    
    public void remover(Integer id){
        setObjeto(getDao().localizar(id));
        if (getDao().remover(getObjeto())){
            Util.mensagemInformacao(getDao().getMensagem());
        } else {
            Util.mensagemErro(getDao().getMensagem());
        }
    }

    public RecursoDAO<Recurso> getDao() {
        return dao;
    }

    public void setDao(RecursoDAO<Recurso> dao) {
        this.dao = dao;
    }

    public Recurso getObjeto() {
        return objeto;
    }

    public void setObjeto(Recurso objeto) {
        this.objeto = objeto;
    }

    public CondominioDAO getDaoCondominio() {
        return daoCondominio;
    }

    public void setDaoCondominio(CondominioDAO daoCondominio) {
        this.daoCondominio = daoCondominio;
    }

    
    
}
