package desenv.modelo.persistencia.conteudo;

import java.io.Serializable;

import javax.persistence.EntityManager;

import desenv.modelo.entidade.conteudo.SubCategoria;
import desenv.util.generico.persistencia.GenericRepository;

public class SubCategoriaRepo extends GenericRepository<SubCategoria> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SubCategoriaRepo(EntityManager manager, Class<SubCategoria> entityClass) {
		super(manager, entityClass);

	}

	public SubCategoriaRepo(EntityManager manager) {
		super(manager, SubCategoria.class);

	}

	public SubCategoriaRepo() {
		super(getManager(), SubCategoria.class);
	}

	

}
