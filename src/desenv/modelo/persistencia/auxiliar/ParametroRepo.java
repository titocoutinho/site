package desenv.modelo.persistencia.auxiliar;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import desenv.modelo.entidade.auxiliar.Parametro;
import desenv.util.generico.persistencia.GenericRepository;

public class ParametroRepo extends GenericRepository<Parametro> {
	
	private static final String SELECT_POR_PARAMETRO = "select p from Parametro p where nomeParametro =:param1";
	

	public ParametroRepo(EntityManager manager, Class<Parametro> entityClass) {
		super(manager, entityClass);

	}

	public ParametroRepo(EntityManager manager) {
		super(manager, Parametro.class);

	}

	public ParametroRepo() {
		super(getManager(), Parametro.class);
	}

	public Parametro getBuscaPorParametro(String parametro){
		Query query = this.manager.createQuery(SELECT_POR_PARAMETRO);
		query.setParameter("param1", parametro);
		return (Parametro) query.getSingleResult();
	}
	public String getValorPeloNome(String nomeParametro){
		Query q = this.manager.createQuery(SELECT_POR_PARAMETRO);
		q.setParameter("param1", nomeParametro);
		return ((Parametro) q.getSingleResult()).getValorParametro();
		
	}
	
}
