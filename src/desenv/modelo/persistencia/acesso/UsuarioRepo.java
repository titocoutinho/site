package desenv.modelo.persistencia.acesso;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import desenv.modelo.entidade.acesso.Usuario;
import desenv.util.generico.enums.Boleano;
import desenv.util.generico.persistencia.GenericRepository;

public class UsuarioRepo extends GenericRepository<Usuario> {

	String sql = "";

	public UsuarioRepo(EntityManager manager, Class<Usuario> entityClass) {
		super(manager, entityClass);

	}

	public UsuarioRepo(EntityManager manager) {
		super(manager, Usuario.class);

	}

	public UsuarioRepo() {
		super(getManager(), Usuario.class);
	}
	
	@Override
	public void adiciona(Usuario usuario) {
	usuario.setAdministrador(Boleano.NAO);
	usuario.setAtivo(Boleano.SIM);
		super.adiciona(usuario);
	}

	@Override
	public Usuario procura(Long id) {
		Usuario busca = manager.find(Usuario.class, id);
		return busca;
	}
	
	
/**
 * 
 * @param usuario
 * @return
 */
	/*public boolean usuarioExiste(Usuario usuario) {
		sql = "select u.id from Usuario u where u.cpf =:usuario order by u.id";
		Query query = this.manager.createQuery(sql);
		query.setParameter("usuario", usuario.getCpf());
		boolean test = query.getResultList().size() > 0; 
		return test;
	}*/
/**
 * 
 * @param usuario
 * @param senha
 * @return
 */
	public Usuario autenticar(String usuario, String senha) {
		sql = "SELECT p FROM Usuario p WHERE p.email = :usuario AND p.senha = :senha and p.excluido =:excluido  and p.ativo = 0";
		Query query = this.manager.createQuery(sql);
		
		query.setParameter("usuario", usuario);
		query.setParameter("senha", senha);
		query.setParameter("excluido", Boleano.NAO);
		
		try {
			return (Usuario) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
/**
 * 
 * @param cpf
 * @return
 */
	public Usuario pesquisaCPF(String cpf) {
		sql = "SELECT u FROM Usuario u WHERE u.cpf = :cpf";
		Query query = this.manager.createQuery(sql);
		query.setParameter("cpf", cpf);
		try {
			return (Usuario) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 
	 * @param cpf
	 * @param nascimento
	 * @return
	 */
	public Usuario esqueciMinhaSenha(String cpf, Date nascimento){
		sql = "SELECT u FROM Usuario u WHERE u.email = :cpf and u.nascimento = :nascimento ";
		Query query = this.manager.createQuery(sql).setParameter("nascimento", nascimento);
		query.setParameter("cpf", cpf);
		try {
			return (Usuario) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
	
/**
 * 
 * @param email
 * @return
 */
	public Usuario pesquisaEmail(String email) {
		sql = "SELECT u FROM Usuario u WHERE u.email = :email";
		Query query = this.manager.createQuery(sql);
		query.setParameter("email", email);
		try {
			return (Usuario) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public Usuario novaInstancia() {
		return super.novaInstancia();
	}

}
