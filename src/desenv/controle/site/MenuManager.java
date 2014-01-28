package desenv.controle.site;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import desenv.modelo.entidade.conteudo.Categoria;
import desenv.util.web.Contexto;

@RequestScoped
@ManagedBean
public class MenuManager implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Categoria> categorias;
	
	public String getUrlFotos(){
		return Contexto.getFotos();
	}
	
	
	public List<Categoria> getCategorias() {
		categorias = Contexto.getMenu();
		return categorias; 
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}


}
