package desenv.controle.manager;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import desenv.modelo.entidade.conteudo.Comentario;
import desenv.modelo.persistencia.conteudo.ComentarioRepo;
import desenv.util.generico.controle.ModeloVisao;
@ManagedBean
public class ComentarioManager extends ModeloVisao<Comentario> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Comentario getObjeto() {
		if (objeto == null)
			objeto = new Comentario();
		return objeto;
	}

	public ComentarioManager() {
		setGenericRepositorio(new ComentarioRepo());
	}

	@PostConstruct
	public void cargaInicial() {
		objeto = new Comentario();
		modoInicialPadrao();

	}
}
