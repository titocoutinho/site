package desenv.modelo.persistencia.conteudo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import desenv.modelo.entidade.conteudo.Categoria;
import desenv.util.generico.persistencia.GenericRepository;

public class CategoriaRepo extends GenericRepository<Categoria> {

	public static final String CONSULTA_MENU = "select o from Categoria o order by o.prioridade asc";
	
	public CategoriaRepo(EntityManager manager, Class<Categoria> entityClass) {
		super(manager, entityClass);

	}

	public CategoriaRepo(EntityManager manager) {
		super(manager, Categoria.class);

	}

	public CategoriaRepo() {
		super(getManager(), Categoria.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Categoria> getMenu(){
		Query q = getManager().createQuery(CONSULTA_MENU);
		
		return q.getResultList(); 
	}

}
