package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Aluguel;
import java.io.Serializable;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
public class AluguelDAO<TIPO> extends DAOGenerico<Aluguel> implements Serializable {
    
    public AluguelDAO(){
        super();
        classePersistente = Aluguel.class;
        // inicializar a ordem padrão
        ordem = "id";
    }
   
}
