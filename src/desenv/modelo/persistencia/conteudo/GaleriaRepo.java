package desenv.modelo.persistencia.conteudo;

import java.io.Serializable;

import javax.persistence.EntityManager;

import desenv.modelo.entidade.conteudo.Galeria;
import desenv.util.generico.persistencia.GenericRepository;

public class GaleriaRepo extends GenericRepository<Galeria> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7573683093745951572L;

	public GaleriaRepo(EntityManager manager, Class<Galeria> entityClass) {
		super(manager, entityClass);

	}

	public GaleriaRepo(EntityManager manager) {
		super(manager, Galeria.class);

	}

	public GaleriaRepo() {
		super(getManager(), Galeria.class);
	}

}
