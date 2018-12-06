package br.edu.ifsul.converters;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.UnidadeCondominial;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "converterUnidadeCondominial")
public class ConverterUnidadeCondominial implements Serializable, Converter {

    // converte da tela para o controle
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")){
            return null;
        }
        return EntityManagerUtil.getEntityManager().find(UnidadeCondominial.class, 
                Integer.parseInt(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object u) {
        if (u == null) {
            return null;
        }
        UnidadeCondominial obj = (UnidadeCondominial) u;
        return obj.getId().toString();
    }

}
