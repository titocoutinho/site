package desenv.controle.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import desenv.modelo.entidade.conteudo.Categoria;
import desenv.modelo.persistencia.conteudo.CategoriaRepo;
import desenv.util.generico.converter.PadraoConverter;
@FacesConverter(forClass = Categoria.class, value = "categoriaConverter")
public class CategoriaConverter extends PadraoConverter implements Converter {

	public CategoriaConverter() {
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String id) {
	
		Categoria objeto = new CategoriaRepo(getManager()).procura(Long.parseLong(id));
	
		return objeto;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object objeto) {
		try {
			
			return (( Categoria) objeto).getId().toString();
		} catch (NullPointerException e) {
			return "0";
		}
	}

}
