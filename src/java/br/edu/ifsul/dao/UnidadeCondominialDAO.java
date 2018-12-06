package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.UnidadeCondominial;
import java.io.Serializable;


public class UnidadeCondominialDAO<TIPO> extends DAOGenerico<UnidadeCondominial> implements Serializable {
    
    public UnidadeCondominialDAO(){
        super();
        classePersistente = UnidadeCondominial.class;
        // inicializar a ordem padrão
        ordem = "id";
    }
   
}
