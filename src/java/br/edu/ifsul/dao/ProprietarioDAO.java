package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Pessoa;
import java.io.Serializable;


public class ProprietarioDAO<TIPO> extends DAOGenerico<Pessoa> implements Serializable {
    
    public ProprietarioDAO(){
        super();
        classePersistente = Pessoa.class;
        // inicializar a ordem padrão
        ordem = "nome";
    }
   
}
