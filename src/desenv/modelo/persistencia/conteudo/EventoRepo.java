package desenv.modelo.persistencia.conteudo;

import java.io.Serializable;

import javax.persistence.EntityManager;

import desenv.modelo.entidade.conteudo.Evento;
import desenv.util.generico.persistencia.GenericRepository;

public class EventoRepo extends GenericRepository<Evento> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public EventoRepo(EntityManager manager, Class<Evento> entityClass) {
		super(manager, entityClass);

	}

	public EventoRepo(EntityManager manager) {
		super(manager, Evento.class);

	}

	public EventoRepo() {
		super(getManager(), Evento.class);
	}
	

}
