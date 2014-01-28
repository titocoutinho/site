package desenv.controle.manager;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import desenv.modelo.entidade.conteudo.Categoria;
import desenv.modelo.persistencia.conteudo.CategoriaRepo;
import desenv.util.generico.controle.ModeloVisao;

@ManagedBean
public class CategoriaManager extends ModeloVisao<Categoria> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Categoria getObjeto() {
		if (objeto == null)
			objeto = new Categoria();
		return objeto;
	}

	public CategoriaManager() {
		setGenericRepositorio(new CategoriaRepo());
	}

	@PostConstruct
	public void cargaInicial() {
		objeto = new Categoria();
		modoInicialPadrao();

	}
}
