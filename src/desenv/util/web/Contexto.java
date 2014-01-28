package desenv.util.web;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import desenv.modelo.entidade.acesso.Usuario;
import desenv.modelo.entidade.conteudo.Categoria;
import desenv.modelo.persistencia.auxiliar.ParametroRepo;
import desenv.modelo.persistencia.conteudo.CategoriaRepo;
import desenv.util.banco.JpaUtil;

/**
 * @author Victor Coutinho
 * 
 */
public class Contexto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String USUARIO = "session_user";
	/** Creates a new instance of Contexto */
	@SuppressWarnings("rawtypes")
	private static List mensagemErro = new ArrayList();

	public Contexto() {
	}
	
	public static String getUsuario() {
		return USUARIO;
	}
	
	
	
	
	

	public static HttpSession getSessao() {
		return (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
	}
	public static void invalidaSessao(String pagina){
		HttpServletRequest request = (HttpServletRequest) getExternalContext().getRequest();
		Contexto.getSessao().setAttribute("session_user", null);
		HttpSession sessao = (HttpSession) request.getSession();
		sessao.invalidate();
		try{
			getExternalContext().redirect(request.getContextPath()+"/"+pagina);
		}catch(IOException e){
			System.out.println("Erro ao tentar redirecionar no logoff efetuado "+e.toString());
		}
	
	}
	public static Usuario getUsuario_Sessao(){
		Usuario userSession = ((Usuario) getSessao().getAttribute("session_user"));
		return userSession;
	}
	
	public static HttpServletRequest getRequest(){
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	public static HttpServletResponse getResponse() {
		return (HttpServletResponse) FacesContext.getCurrentInstance()
				.getExternalContext().getResponse();
	}

	public static ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	public static String getPath(String caminho) {
		ServletContext request = (ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext();
		return request.getRealPath(caminho);

	}
	
	
	public static List<Categoria> getMenu(){
		List<Categoria> menu = new CategoriaRepo().getMenu();
		
	/*	if(getSessao().getAttribute("menu") == null){
			 menu.addAll(new CategoriaRepo().getLista());
			 getSessao().setAttribute("menu", menu);
		}else{
			
			menu.addAll(((List<Categoria>) (getSessao().getAttribute("menu"))));
		}*/
		return menu;
		
	}
	
	public static String getFotos(){
		String retorno = "";
		ParametroRepo pr = new ParametroRepo();
		if(getSessao().getAttribute("fotos") == null)
		{
			getSessao().setAttribute("fotos", pr.getValorPeloNome("fotos"));
			retorno = pr.getValorPeloNome("fotos");
		}else{
			retorno = (String) getSessao().getAttribute("fotos");
		}
		return retorno;
	}
	
	public static String getEmail(){
		String retorno = "";
		ParametroRepo pr = new ParametroRepo();
		if(getSessao().getAttribute("email_envio") == null)
		{
			getSessao().setAttribute("email_envio", pr.getValorPeloNome("email_envio"));
			retorno = pr.getValorPeloNome("email_envio");
		}else{
			retorno = (String) getSessao().getAttribute("email_envio");
		}
		return retorno;
	}
	
	public static String getDestinatario(){
		String retorno = "";
		ParametroRepo pr = new ParametroRepo();
		if(getSessao().getAttribute("nome_destinatario") == null)
		{
			getSessao().setAttribute("nome_destinatario", pr.getValorPeloNome("nome_destinatario"));
			retorno = pr.getValorPeloNome("nome_destinatario");
		}else{
			retorno = (String) getSessao().getAttribute("nome_destinatario");
		}
		return retorno;
	}
	
	public static EntityManager getManager() {
		return (EntityManager) JpaUtil.getManager();
	}
	

	@SuppressWarnings("rawtypes")
	public static List getMensagemErro() {
		return mensagemErro;
	}

	@SuppressWarnings("unchecked")
	public static void addMensagemErro(String MensagemErro) {
		mensagemErro.add(MensagemErro);
	}

	public static void clearMensagemErro() {
		mensagemErro.clear();
	}

	public static String getDatetoString(Date data) {
		if (data == null) {
			return null;
		}

		String sTemp = DateFormat.getDateInstance().format(data);
		// dd/mm/yyyy
		String dataFinal = sTemp.substring(6);
		dataFinal = dataFinal + "/";
		dataFinal = dataFinal + sTemp.substring(3, 5);
		dataFinal = dataFinal + "/";
		dataFinal = dataFinal + sTemp.substring(0, 2);

		return dataFinal;

	}

	public static String getDateStringtoString(String data) {
		if (data.equals("")) {
			return null;
		}

		String sTemp = data;

		String dataFinal = sTemp.substring(6);
		dataFinal = dataFinal + "/";
		dataFinal = dataFinal + sTemp.substring(3, 5);
		dataFinal = dataFinal + "/";
		dataFinal = dataFinal + sTemp.substring(0, 2);

		return dataFinal;

	}
}