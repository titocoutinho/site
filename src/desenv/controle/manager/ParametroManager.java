package desenv.controle.manager;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import desenv.modelo.entidade.auxiliar.Parametro;
import desenv.modelo.persistencia.auxiliar.ParametroRepo;
import desenv.util.generico.controle.ModeloVisao;

@ManagedBean
public class ParametroManager extends ModeloVisao<Parametro> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Parametro getObjeto() {
		if (objeto == null)
			objeto = new Parametro();
		return objeto;
	}

	public ParametroManager() {
		setGenericRepositorio(new ParametroRepo());
	}

	@PostConstruct
	public void cargaInicial() {
		objeto = new Parametro();
		modoInicialPadrao();
	}

}
