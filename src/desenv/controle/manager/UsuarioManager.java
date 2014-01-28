package desenv.controle.manager;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import desenv.modelo.entidade.acesso.Usuario;
import desenv.modelo.persistencia.acesso.UsuarioRepo;
import desenv.util.generico.controle.ModeloVisao;
import desenv.util.web.Contexto;

@ManagedBean
public class UsuarioManager extends ModeloVisao<Usuario> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String novaSenha;
	private String antigaSenha;

	@SuppressWarnings("unused")
	private Usuario usuarioAlterado = new Usuario();

	@Override
	public Usuario getObjeto() {
		if (objeto == null)
			objeto = new Usuario();
		
		return objeto;
	}

	public UsuarioManager() {
		setGenericRepositorio(new UsuarioRepo());
	}

	@PostConstruct
	public void cargaInicial() {
		objeto = new Usuario();
		
		modoInicialPadrao();
	}
	

	
	@Override
	public Usuario getUsuarioSessao() {
		Usuario userSession = new UsuarioRepo().procura(super.getUsuarioSessao().getId());
		return userSession;
	}
	
	public void mudarSenha(){
		Usuario mudaSenha = genericRepositorio.procura(getUsuarioSessao().getId());
		
		if(mudaSenha.getSenha().equals(antigaSenha)){
			if(novaSenha.equals(antigaSenha)){
				mensagemFalha("A nova senha não pode ser igual a antiga");
				return;
			}else if(novaSenha.length() <6){
				mensagemFalha("A nova senha precisa conter no mínimo 6 caracteres");
				return;
			}
			else{
				mudaSenha.setSenha(novaSenha);
				Contexto.getSessao().setAttribute("session_user", genericRepositorio.atualiza(mudaSenha));
				
				mensagemSucesso("Senha Atualizada com Sucesso!");
			}
		}else{
			mensagemFalha("Senha antiga não confere");
			return;
		}
	}

	@Override
	public void adiciona() {
		super.adiciona();
	}

	
	public String getNovaSenha() {
		return novaSenha;
	}
	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}
	
	public String getAntigaSenha() {
		return antigaSenha;
	}
	public void setAntigaSenha(String antigaSenha) {
		this.antigaSenha = antigaSenha;
	}




}
