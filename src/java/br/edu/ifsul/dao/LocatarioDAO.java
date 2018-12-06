package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Locatario;
import java.io.Serializable;


public class LocatarioDAO<TIPO> extends DAOGenerico<Locatario> implements Serializable {
    
    public LocatarioDAO(){
        super();
        classePersistente = Locatario.class;
        // inicializar a ordem padrão
        ordem = "nome";
    }
   
}
