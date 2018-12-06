package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Recurso;
import java.io.Serializable;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
public class RecursoDAO<TIPO> extends DAOGenerico<Recurso> implements Serializable {
    
    public RecursoDAO(){
        super();
        classePersistente = Recurso.class;
        // inicializar a ordem padrão
        ordem = "id";
    }
   
}
