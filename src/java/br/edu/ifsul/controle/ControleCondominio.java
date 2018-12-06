package br.edu.ifsul.controle;


import br.edu.ifsul.dao.CondominioDAO;
import br.edu.ifsul.dao.ProprietarioDAO;
import br.edu.ifsul.dao.RecursoDAO;
import br.edu.ifsul.dao.UnidadeCondominialDAO;
import br.edu.ifsul.modelo.Condominio;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.modelo.Recurso;
import br.edu.ifsul.modelo.UnidadeCondominial;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;



@ManagedBean(name = "controleCondominio")
@ViewScoped
public class ControleCondominio implements Serializable {

    private CondominioDAO<Condominio> dao;
    private Condominio objeto;
    private RecursoDAO<Recurso> daoRecurso;
    private ProprietarioDAO<Pessoa> daoProprietario;
    private UnidadeCondominialDAO<UnidadeCondominial> daoUC;
    private UnidadeCondominial unidadeCondominial;
    private Boolean novoUnidade;

    
    public ControleCondominio(){
        dao = new CondominioDAO<>();
        daoUC = new UnidadeCondominialDAO<>();
        daoRecurso = new RecursoDAO<>();
        daoProprietario = new ProprietarioDAO<>();
    }
    
    public void imprimeRelatorioCondominio(Integer id){
        setObjeto(getDao().localizar(id));
        List<Condominio> lista = new ArrayList<>();
        lista.add(getObjeto());
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioCondominio", parametros, lista);
    }
    
    
    public void imprimeRelatorioTodosCondominios(){
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioCondominio", parametros, getDao().getListaTodos());
    }
    
    public String listar(){
        return "/privado/condominio/listar?faces-redirect=true";
    }
    
    public void novo(){
        setObjeto(new Condominio());       
    }
    
    public void salvar(){
        boolean persistiu;
        if(getObjeto().getId() == null){
            persistiu = getDao().persist(getObjeto());
        } else {
            persistiu = getDao().merge(getObjeto());
        }
        if (persistiu){
            Util.mensagemInformacao(getDao().getMensagem());            
        } else {
            Util.mensagemErro(getDao().getMensagem());            
        }
    }    
    
    public void editar(Integer id){
        try {
            setObjeto(getDao().localizar(id));            
        } catch (Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));            
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

    public void novoUnidade(){
        setUnidadeCondominial(new UnidadeCondominial());
        setNovoUnidade((Boolean) true);
    }
    
    public void editarUnidade(int index){
        setUnidadeCondominial(getObjeto().getUnidadeCondominiais().get(index));
        setNovoUnidade((Boolean) false);
    }
    
    public void salvarUnidade(){
        if (getNovoUnidade()){
            getObjeto().adicionarUnidade(getUnidadeCondominial());
        }
        Util.mensagemInformacao("Unidade Condominial atualizado com sucesso!");
    }
    
    public void removerUnidade(int index){
        getObjeto().removerUnidade(index);
        Util.mensagemInformacao("Unidade Condominial removido com sucesso!");
    }

    public CondominioDAO<Condominio> getDao() {
        return dao;
    }

    public void setDao(CondominioDAO<Condominio> dao) {
        this.dao = dao;
    }

    public Condominio getObjeto() {
        return objeto;
    }

    public void setObjeto(Condominio objeto) {
        this.objeto = objeto;
    }

    public RecursoDAO<Recurso> getDaoRecurso() {
        return daoRecurso;
    }

    public void setDaoRecurso(RecursoDAO<Recurso> daoRecurso) {
        this.daoRecurso = daoRecurso;
    }

    public UnidadeCondominialDAO<UnidadeCondominial> getDaoUC() {
        return daoUC;
    }

    public void setDaoUC(UnidadeCondominialDAO<UnidadeCondominial> daoUC) {
        this.daoUC = daoUC;
    }

    public UnidadeCondominial getUnidadeCondominial() {
        return unidadeCondominial;
    }

    public void setUnidadeCondominial(UnidadeCondominial unidadeCondominial) {
        this.unidadeCondominial = unidadeCondominial;
    }

    public Boolean getNovoUnidade() {
        return novoUnidade;
    }

    public void setNovoUnidade(Boolean novoUnidade) {
        this.novoUnidade = novoUnidade;
    }

    public ProprietarioDAO<Pessoa> getDaoProprietario() {
        return daoProprietario;
    }

    public void setDaoProprietario(ProprietarioDAO<Pessoa> daoProprietario) {
        this.daoProprietario = daoProprietario;
    }
    
    

}
