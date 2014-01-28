package desenv.controle.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import desenv.modelo.entidade.conteudo.Galeria;
import desenv.modelo.persistencia.conteudo.GaleriaRepo;
import desenv.util.generico.converter.PadraoConverter;
@FacesConverter(forClass = Galeria.class, value = "galeriaConverter")
public class GaleriaConverter extends PadraoConverter implements Converter {

	public GaleriaConverter() {
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String id) {
	
		Galeria objeto = new GaleriaRepo(getManager()).procura(Long.parseLong(id));
	
		return objeto;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object objeto) {
		try {
			
			return (( Galeria) objeto).getId().toString();
		} catch (NullPointerException e) {
			return "0";
		}
	}

}
