package desenv.controle.acesso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import desenv.modelo.entidade.acesso.Contador;
import desenv.modelo.entidade.acesso.Usuario;
import desenv.modelo.persistencia.acesso.ContadorRepo;
import desenv.modelo.persistencia.acesso.UsuarioRepo;
import desenv.util.web.Contexto;

@ManagedBean
public class AutenticadorManager {

	private String login;
	private String senha;
	private String senhaRecuperada = "****************";
	private String recuperaCPF;
	private Date recuperaNascimento;
	private Usuario sessionUser = null;
	private Usuario usuarioCadastro = new Usuario();
	private UsuarioRepo base =  new UsuarioRepo();
	private List<SelectItem> estados = new ArrayList<SelectItem>();
	private List<SelectItem> escolaridades = new ArrayList<SelectItem>();
	private List<SelectItem> sexos = new ArrayList<SelectItem>();
	private List<SelectItem> vinculos = new ArrayList<SelectItem>();
	
	
	@PostConstruct
	public void cargaInicial() {
		usuarioCadastro = new Usuario();
	}

	/**
	 * 
	 * @return Usuario na sessão.
	 */
	public Usuario getUsuarioSessao() {
		try {
			return (Usuario) Contexto.getUsuario_Sessao();
		} catch (NullPointerException e) {
			return null;
		}
	}
	
	/**
	 * 
	 * @return sair
	 */
	public String sair() {
		Contexto.invalidaSessao("/admin/login/login.jsf");
		return null;
	}

	public void recuperarSenha(){
		senhaRecuperada = base.esqueciMinhaSenha(recuperaCPF, recuperaNascimento).getSenha();	
	}
	
	public String cadastrarSite() {
	
		try{
			
			if(usuarioCadastro.getSenha().equals(null) ||  usuarioCadastro.getSenha().isEmpty() ||  usuarioCadastro.getSenha() == "" || usuarioCadastro.getSenha().length() < 6){
				mensagemFalha("A senha deve possui no mínimo 6 caracteres");
				return null;
			}
			else{

					base.adiciona(usuarioCadastro);
					Contexto.getSessao().setAttribute("session_user", usuarioCadastro);
					StringBuilder msg = new StringBuilder();
					msg.append("Bem Vindo, ").append(usuarioCadastro.getEmail()).append(" !");
					mensagemSucesso(msg.toString());
					return "/admin/home.php";
				
			}
			
		}catch (NullPointerException e){
			mensagemFalha("A senha deve possui no mínimo 6 caracteres");
			return null;
		}
		
		//return null;
	}
	
	/**
	 * autenticacao
	 * 
	 * @return
	 */
	public String entrar() {
		StringBuilder msg = new StringBuilder();
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		if( (login == "" || login.equals(null)) || (senha== ""||senha.equals(null)) ){
			msg.append("Olá, \n");			
			if(login == "" || login.equals(null)){
				msg.append("o CPF precisa ser informada ");
			}
			if (senha== ""||senha.equals(null)){
				msg.append("\no senha precisa ser informada ");
			}
			mensagemFalha(msg.toString());
			return  "autenticacao-falha";
		}
		sessionUser = base.autenticar(login, senha);
		if(sessionUser != null){
			Contexto.getSessao().setAttribute("session_user", sessionUser);
			msg.append("Bem Vindo, \n").append(sessionUser.getEmail()).append("!");
			mensagemSucesso(msg.toString());
			
			
			
			Contador c = new  Contador();
			c.setArea("ADMINISTRATIVA");
			c.setUsuario(sessionUser.getNome()+"");
			
			new ContadorRepo().adiciona(c);

			
			
			return "autenticacao-bem-sucedida";
			
		}
		else {
			mensagemFalha("Usuário e senha não conferem!!");
			return "autenticacao-falha";
		}
	}

	/**
	 * 
	 * @param String msg
	 */
	public void mensagemSucesso(String msg) {
		FacesMessage mensagem = new FacesMessage(msg);
		mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
	}
	public void mensagemFalha(String msg){
		FacesMessage mensagem = new FacesMessage(msg);
		mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario getSessionUser() {
		return sessionUser;
	}

	public void setSessionUser(Usuario sessionUser) {
		this.sessionUser = sessionUser;
	}
	
	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}
	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}
	
	public List<SelectItem> getEstados() {
		return estados;
	}
	
	public List<SelectItem> getEscolaridades() {
		return escolaridades;
	}
	public List<SelectItem> getSexos() {
		return sexos;
	}
	public List<SelectItem> getVinculos() {
		return vinculos;
	}
	public String getRecuperaCPF() {
		return recuperaCPF;
	}

	public void setRecuperaCPF(String recuperaCPF) {
		this.recuperaCPF = recuperaCPF;
	}

	public Date getRecuperaNascimento() {
		return recuperaNascimento;
	}
	public void setRecuperaNascimento(Date recuperaNascimento) {
		this.recuperaNascimento = recuperaNascimento;
	}
	public String getSenhaRecuperada() {
		return senhaRecuperada;
	}
	public void setSenhaRecuperada(String senhaRecuperada) {
		this.senhaRecuperada = senhaRecuperada;
	}

}
