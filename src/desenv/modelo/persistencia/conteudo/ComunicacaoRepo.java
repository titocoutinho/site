package desenv.modelo.persistencia.conteudo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import desenv.modelo.entidade.conteudo.Comunicacao;
import desenv.util.generico.persistencia.GenericRepository;

public class ComunicacaoRepo extends GenericRepository<Comunicacao> {

	public ComunicacaoRepo(EntityManager manager, Class<Comunicacao> entityClass) {
		super(manager, entityClass);

	}

	public ComunicacaoRepo(EntityManager manager) {
		super(manager, Comunicacao.class);

	}

	public ComunicacaoRepo() {
		super(getManager(), Comunicacao.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Comunicacao> getLista() {
		StringBuilder sql = new StringBuilder();
		sql.append("select x from ").append(Comunicacao.class.getSimpleName())
				.append(" x").append(" where x.excluido = 'NAO' ")
				.append(" order by x.id desc");
		Query query = this.manager.createQuery(sql.toString());
		return query.getResultList();
	}

}
