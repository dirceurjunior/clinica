package br.com.magmadoctor.conversores;

import br.com.magmadoctor.modelo.especialidade.Especialidade;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "simpleEntityConverter")
public class SimpleEntityConverter implements Converter {

    public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
        if (value != null) {
            return this.getAttributesFrom(component).get(value);
        }
        return null;
    }

    public String getAsString(FacesContext ctx, UIComponent component, Object value) {

        if (value != null
                && !"".equals(value)) {

            Especialidade entity = (Especialidade) value;

            // adiciona item como atributo do componente  
            this.addAttribute(component, entity);

            Integer codigo = entity.getId();
            if (codigo != null) {
                return String.valueOf(codigo);
            }
        }

        return (String) value;
    }

    protected void addAttribute(UIComponent component, Especialidade o) {
        String key = o.getId().toString(); // codigo da empresa como chave neste caso  
        this.getAttributesFrom(component).put(key, o);
    }

    protected Map<String, Object> getAttributesFrom(UIComponent component) {
        return component.getAttributes();
    }

}
