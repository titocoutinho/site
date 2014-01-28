package desenv.controle.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import desenv.modelo.entidade.demografia.Municipio;
import desenv.modelo.persistencia.demografia.MunicipioRepo;
import desenv.util.generico.converter.PadraoConverter;
@FacesConverter(forClass = Municipio.class, value = "municipioConverter")
public class MunicipioConverter extends PadraoConverter implements Converter {

	public MunicipioConverter() {
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String id) {
		return new MunicipioRepo(getManager()).procura(Long.parseLong(id));
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object objeto) {
		try {
			return (( Municipio) objeto).getId().toString();
		} catch (NullPointerException e) {
			return "0";
		}
	}

}
