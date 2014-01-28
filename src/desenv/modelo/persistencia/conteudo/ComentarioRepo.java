package desenv.modelo.persistencia.conteudo;

import javax.persistence.EntityManager;

import desenv.modelo.entidade.conteudo.Comentario;
import desenv.util.generico.persistencia.GenericRepository;

public class ComentarioRepo extends GenericRepository<Comentario> {

	public ComentarioRepo(EntityManager manager, Class<Comentario> entityClass) {
		super(manager, entityClass);

	}

	public ComentarioRepo(EntityManager manager) {
		super(manager, Comentario.class);

	}

	public ComentarioRepo() {
		super(getManager(), Comentario.class);
	}


}
