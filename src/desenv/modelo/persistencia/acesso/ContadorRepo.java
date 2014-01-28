package desenv.modelo.persistencia.acesso;

import javax.persistence.EntityManager;

import desenv.modelo.entidade.acesso.Contador;
import desenv.util.generico.persistencia.GenericRepository;

public class ContadorRepo extends GenericRepository<Contador> {

	String sql = "";

	public ContadorRepo(EntityManager manager, Class<Contador> entityClass) {
		super(manager, entityClass);

	}

	public ContadorRepo(EntityManager manager) {
		super(manager, Contador.class);

	}

	public ContadorRepo() {
		super(getManager(), Contador.class);
	}
	
	
}
