package desenv.modelo.persistencia.demografia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import desenv.modelo.entidade.demografia.Caracteristica;
import desenv.modelo.entidade.demografia.Dado;
import desenv.util.generico.persistencia.GenericRepository;

public class DadoRepo extends GenericRepository<Dado> {

	private static final String PESQUISA_POR_MUNICIPIO = "select d from Dado d where d.municipio.id=:param1";
	private static final String PESQUISA_POR_CARACTERISTICA = "select d from Dado d where d.caracteristica.id=:param1";
	
	public DadoRepo(EntityManager manager, Class<Dado> entityClass) {
		super(manager, entityClass);

	}

	public DadoRepo(EntityManager manager) {
		super(manager, Dado.class);

	}

	public DadoRepo() {
		super(getManager(), Dado.class);
	
	}

	@SuppressWarnings("unchecked")
	public List<Dado> listaPorMunicipio(Long id){
		Query query = this.manager.createQuery(PESQUISA_POR_MUNICIPIO);
		query.setParameter("param1", id);
		
		return query.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<Caracteristica> listaPorCaracteristica(Long id){
		Query query = this.manager.createQuery(PESQUISA_POR_CARACTERISTICA);
		query.setParameter("param1", id);
		
		return query.getResultList();
	}
	
	
}
