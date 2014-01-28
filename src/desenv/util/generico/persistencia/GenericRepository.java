package desenv.util.generico.persistencia;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import desenv.util.banco.JpaUtil;
import desenv.util.generico.enums.Boleano;
import desenv.util.generico.modelo.IModelo;

/***************************
 * *
 * 
 * @author Victor Coutinho * *
 * @since 21 / 11 / 2012 * *
 ***************************/
public class GenericRepository<T extends IModelo> implements
		IGenericRepository<T> {

	protected EntityManager manager =  (EntityManager) JpaUtil.getManager();

	private Class<T> classGenerica;

	public GenericRepository(EntityManager manager, Class<T> entityClass) {
		this.manager = manager;
		this.classGenerica = entityClass;
	}

	public GenericRepository(EntityManager manager) {
		this.manager = manager;

	}

	@Override
	public void adiciona(T jogador) {
		manager.getTransaction().begin();
		jogador.setExcluido(Boleano.NAO);
		jogador.setId(null);
		this.manager.persist(jogador);
		manager.getTransaction().commit();
		

	}

	@Override
	public void remove(Long id) {
		manager.getTransaction().begin();
		T time = this.procura(id);
		time.setExcluido(Boleano.SIM);
		manager.merge(time);
		manager.getTransaction().commit();
	}

	@Override
	public T atualiza(T jogador) {
		manager.getTransaction().begin();
		jogador.setModificado(new Date());
		manager.merge(jogador);
		manager.getTransaction().commit();
		return jogador;

	}

	@Override
	public T procura(Long id) {
		T objeto = manager.find(classGenerica, id);
		
		return objeto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getLista() {
		StringBuilder sql = new StringBuilder();
												
		sql.append("select x from ").append(classGenerica.getSimpleName())
				.append(" x").append(" where x.excluido = 'NAO' ")
				.append(" order by x.id");
		Query query = this.manager.createQuery(sql.toString());
		return query.getResultList();
	}

	@Override
	public Long getCount() {
		Query query = this.manager.createQuery("select count(x) from "
				+ classGenerica.getSimpleName() + "  x");
		return (Long) query.getSingleResult();
	}
	@SuppressWarnings("unchecked")
	public T getUltimo(){
		Query query = this.manager.createQuery("select max(x) from "+ classGenerica.getSimpleName()+" x");
		return (T) query.getSingleResult();
				
	}

	@Override
	public T novaInstancia() {
		try {
			return this.classGenerica.newInstance();

		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}
		return null;
	}

	public static EntityManager getManager() {
		return  (EntityManager) JpaUtil.getManager();

	}
}
