package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AluguelDAO;
import br.edu.ifsul.dao.LocatarioDAO;
import br.edu.ifsul.dao.MensalidadesDAO;
import br.edu.ifsul.dao.UnidadeCondominialDAO;
import br.edu.ifsul.modelo.Aluguel;
import br.edu.ifsul.modelo.Locatario;
import br.edu.ifsul.modelo.Mensalidades;
import br.edu.ifsul.modelo.UnidadeCondominial;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;



@ManagedBean(name = "controleAluguel")
@ViewScoped
public class ControleAluguel implements Serializable {

    private AluguelDAO<Aluguel> dao;
    private Aluguel objeto;
    private LocatarioDAO<Locatario> daoLocatario;
    private Locatario locatario;
    private UnidadeCondominialDAO<UnidadeCondominial> daoUnidadeCondominial;
    private UnidadeCondominial unidadeCondominial;
    private MensalidadesDAO<Mensalidades> daoMensalidades;
    private Mensalidades mensalidades;
    private Boolean novoMensalidade;
    
    public ControleAluguel(){
        dao = new AluguelDAO<>();
        daoMensalidades = new MensalidadesDAO<>();
        daoLocatario = new LocatarioDAO<>();
        daoUnidadeCondominial = new UnidadeCondominialDAO<>();
    }
    
    public String listar(){
        return "/privado/aluguel/listar?faces-redirect=true";
    }
    
    public void novo(){
        setObjeto(new Aluguel());
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
    
    public void novoMensalidades(){
        setMensalidades(new Mensalidades());
        setNovoMensalidade((Boolean) true);
    }
    
    public void editarMensalidades(int index){
        setMensalidades(getObjeto().getMensalidades().get(index));
        setNovoMensalidade((Boolean) false);
    }
    
    public void salvarMensalidades(){
        if (getNovoMensalidade()){
            getObjeto().adicionarMensalidades(getMensalidades());
        }
        Util.mensagemInformacao("Mensalidade atualizada com sucesso!");
    }
    
    public void removerMensalidades(int index){
        getObjeto().removerMensalidades(index);
        Util.mensagemInformacao("Mensalidade removida com sucesso!");
    }

    public AluguelDAO<Aluguel> getDao() {
        return dao;
    }

    public void setDao(AluguelDAO<Aluguel> dao) {
        this.dao = dao;
    }

    public Aluguel getObjeto() {
        return objeto;
    }

    public void setObjeto(Aluguel objeto) {
        this.objeto = objeto;
    }

    public LocatarioDAO<Locatario> getDaoLocatario() {
        return daoLocatario;
    }

    public void setDaoLocatario(LocatarioDAO<Locatario> daoLocatario) {
        this.daoLocatario = daoLocatario;
    }

    public UnidadeCondominialDAO<UnidadeCondominial> getDaoUnidadeCondominial() {
        return daoUnidadeCondominial;
    }

    public void setDaoUnidadeCondominial(UnidadeCondominialDAO<UnidadeCondominial> daoUnidadeCondominial) {
        this.daoUnidadeCondominial = daoUnidadeCondominial;
    }

    public Mensalidades getMensalidades() {
        return mensalidades;
    }

    public void setMensalidades(Mensalidades mensalidades) {
        this.mensalidades = mensalidades;
    }

    public Boolean getNovoMensalidade() {
        return novoMensalidade;
    }

    public void setNovoMensalidade(Boolean novoMensalidade) {
        this.novoMensalidade = novoMensalidade;
    }

    /**
     * @return the locatario
     */
    public Locatario getLocatario() {
        return locatario;
    }

    /**
     * @param locatario the locatario to set
     */
    public void setLocatario(Locatario locatario) {
        this.locatario = locatario;
    }

    /**
     * @return the unidadeCondominial
     */
    public UnidadeCondominial getUnidadeCondominial() {
        return unidadeCondominial;
    }

    /**
     * @param unidadeCondominial the unidadeCondominial to set
     */
    public void setUnidadeCondominial(UnidadeCondominial unidadeCondominial) {
        this.unidadeCondominial = unidadeCondominial;
    }

    /**
     * @return the daoMensalidades
     */
    public MensalidadesDAO<Mensalidades> getDaoMensalidades() {
        return daoMensalidades;
    }

    /**
     * @param daoMensalidades the daoMensalidades to set
     */
    public void setDaoMensalidades(MensalidadesDAO<Mensalidades> daoMensalidades) {
        this.daoMensalidades = daoMensalidades;
    }

    
}
