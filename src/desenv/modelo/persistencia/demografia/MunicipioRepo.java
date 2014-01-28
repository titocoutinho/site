package desenv.modelo.persistencia.demografia;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import desenv.modelo.entidade.demografia.Municipio;
import desenv.util.generico.persistencia.GenericRepository;

public class MunicipioRepo extends GenericRepository<Municipio> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String PESQUISA_POR_NOME = 
			"select m from Municipio m where m.nome=:param1";
	
	public MunicipioRepo(EntityManager manager, Class<Municipio> entityClass) {
		super(manager, entityClass);

	}

	public MunicipioRepo(EntityManager manager) {
		super(manager, Municipio.class);

	}

	public MunicipioRepo() {
		super(getManager(), Municipio.class);
	}
	
	public Municipio pesquisaPorNome(String nome){
		Query query = this.manager.createQuery(PESQUISA_POR_NOME);
		query.setParameter("param1", nome);
		return (Municipio) query.getSingleResult();
	}
	

}
