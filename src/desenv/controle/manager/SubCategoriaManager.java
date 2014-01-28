package desenv.controle.manager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import desenv.modelo.entidade.conteudo.Categoria;
import desenv.modelo.entidade.conteudo.SubCategoria;
import desenv.modelo.persistencia.conteudo.CategoriaRepo;
import desenv.modelo.persistencia.conteudo.SubCategoriaRepo;
import desenv.util.generico.controle.ModeloVisao;

@ManagedBean
@ViewScoped
public class SubCategoriaManager extends ModeloVisao<SubCategoria> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Categoria> categorias = new ArrayList<Categoria>();
	
	@Override
	public SubCategoria getObjeto() {
		if (objeto == null)
			objeto = new SubCategoria();
		return objeto;
	}

	public SubCategoriaManager() {
		setGenericRepositorio(new SubCategoriaRepo());
	}

	@PostConstruct
	public void cargaInicial() {
		objeto = new SubCategoria();
		modoInicialPadrao();
		categorias = new CategoriaRepo().getLista();
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	
}
