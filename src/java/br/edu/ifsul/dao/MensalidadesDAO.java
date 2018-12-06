package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Mensalidades;
import java.io.Serializable;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
public class MensalidadesDAO<TIPO> extends DAOGenerico<Mensalidades> implements Serializable {
    
    public MensalidadesDAO(){
        super();
        classePersistente = Mensalidades.class;
        // inicializar a ordem padrão
        ordem = "id";
    }
   
}
