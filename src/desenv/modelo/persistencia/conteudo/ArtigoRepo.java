package desenv.modelo.persistencia.conteudo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import desenv.modelo.entidade.conteudo.Artigo;
import desenv.util.generico.persistencia.GenericRepository;

public class ArtigoRepo extends GenericRepository<Artigo> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String SELECT_PRIMEIRO_ARTIGO = "Select a From Artigo a order by a.id desc";
	private static final String SELECT_POR_CATEGORIA = "SELECT a FROM Artigo a WHERE a.categoria.id =:param1 order by a.id desc";
	private static final String SELECT_POR_CATEGORIA_CARROSEL = "SELECT a FROM Artigo a WHERE a.destaque = 'SIM' and a.categoria.id =:param1";
	private static final String SELECT_POR_ARTIGO_E_CATEGORIA = "SELECT a FROM Artigo a WHERE a.categoria.id =:param1 and a.id =:param2";
	private static final String SELECT_ULTIMOS_REGISTROS = "SELECT a FROM Artigo a order by a.id desc";
	private static final String SELECT_ULTIMO_REGISTRO_POR_CATEGORIA = "SELECT MAX(a) FROM Artigo a WHERE a.categoria.id =:param1 order by a.id desc";
	private static final String SELECT_DESTAQUES = "SELECT a FROM Artigo a WHERE a.destaque = 'SIM'";
	private static final String SELECT_DESTAQUES_CATEGORIA = "SELECT a FROM Artigo a WHERE a.destaque = 'SIM' and a.categoria.id =:param1 ORDER BY a.id desc";
	private static final String SELECT_MATERIA_MUNICIPIO = "select a from Artigo a where a.destaque ='NAO' and a.municipio.id =:param1 ORDER BY a.id desc";

	public ArtigoRepo(EntityManager manager, Class<Artigo> entityClass) {
		super(manager, entityClass);

	}

	public ArtigoRepo(EntityManager manager) {
		super(manager, Artigo.class);

	}

	public ArtigoRepo() {
		super(getManager(), Artigo.class);
	}

	public List<Artigo> getBuscaPorCategoria(Long categoria) {
		Query query = this.manager.createQuery(SELECT_POR_CATEGORIA);
		query.setParameter("param1", categoria);
		query.setFirstResult(1);

		return extracted(query);
	}

	@SuppressWarnings("unchecked")
	public List<Artigo> getPrimeiroArtigo() {
		Query query = this.manager.createQuery(SELECT_PRIMEIRO_ARTIGO);
		query.setFirstResult(0);
		query.setMaxResults(1);
		return query.getResultList();
	}

	public List<Artigo> getBuscaPorPrimeiroArtigo(Long categoria) {
		Query query = this.manager.createQuery(SELECT_POR_CATEGORIA);
		query.setParameter("param1", categoria);
		query.setMaxResults(1);
		return extracted(query);
	}

	public Artigo buscaPorCategoriaEArtigo(Long categoria, Long artigo) {
		try {
			Query query = this.manager.createQuery(SELECT_POR_ARTIGO_E_CATEGORIA);
			query.setParameter("param1", categoria);
			query.setParameter("param2", artigo);
			return (Artigo) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Artigo> getUltimosRegistros(Integer qtd) {
		Query query = this.manager.createQuery(SELECT_ULTIMOS_REGISTROS);
		query.setFirstResult(1);
		query.setMaxResults(qtd);
		return extracted(query);
	}

	public Artigo getUltimoArtigoPorCategoria(Long categoria) {
		Query query = this.manager.createQuery(SELECT_ULTIMO_REGISTRO_POR_CATEGORIA);
		query.setParameter("param1", categoria);
		return (Artigo) query.getSingleResult();
	}

	/**
	 * pesquisa por lazy
	 * 
	 * @param inicio
	 * @param maxPorPagina
	 * @return
	 */
	public List<Artigo> pesquisaComLazy(int inicio, int maxPorPagina) {
		Query query = this.manager.createQuery(SELECT_ULTIMOS_REGISTROS);
		query.setFirstResult(inicio);
		query.setMaxResults(maxPorPagina);
		return extracted(query);
	}

	/**
	 * retorna os destaques gereais
	 * 
	 * @return
	 */
	public List<Artigo> perquisaDestaque() {
		Query q = this.manager.createQuery(SELECT_DESTAQUES);
		q.setMaxResults(5);
		return extracted(q);
	}

	/**
	 * retorna os destaques por categoria
	 * 
	 * @param categoria
	 * @return
	 */
	public List<Artigo> perquisaDestaqueCategoria(Long categoria) {
		Query q = this.manager.createQuery(SELECT_DESTAQUES_CATEGORIA);
		q.setParameter("param1", categoria);
		q.setMaxResults(5);
		return extracted(q);
	}

	/**
	 * retorna as matérias por municipio
	 * 
	 * @param municipio
	 * @return
	 */
	public List<Artigo> pesquisaMateriaPorMunicipio(Long municipio) {
		Query q = this.manager.createQuery(SELECT_MATERIA_MUNICIPIO);
		q.setParameter("param1", municipio);
		return extracted(q);
	}

	public List<Artigo> pesquisaCarrosel(Long categoria) {
		Query q = this.manager.createQuery(SELECT_POR_CATEGORIA_CARROSEL);
		q.setParameter("param1", categoria);
		q.setMaxResults(5);
		return extracted(q);
	}

	@SuppressWarnings("unchecked")
	private List<Artigo> extracted(Query q) {
		System.out.println(((List<Artigo>) q.getResultList()).size()+"tamanho da lista");
		return ((List<Artigo>) q.getResultList());
	}

}
