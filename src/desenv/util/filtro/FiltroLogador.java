package desenv.util.filtro;



import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import desenv.modelo.entidade.acesso.Usuario;
import desenv.util.web.Contexto;

/**
 * 
 * @author Victor Coutinho
 * 
 * @since 07/06/2011
 */

@WebFilter(servletNames = {"Faces Servlet"})
public class FiltroLogador implements Filter {

	private static final boolean debug = false;
	private FilterConfig filterConfig = null;
	
    /**
     * Default constructor. 
     */
    public FiltroLogador() {
   
    }
    public void log(String msg) {
		filterConfig.getServletContext().log(msg);
	}

	private void doBeforeProcessing(ServletRequest request, ServletResponse response) throws IOException, ServletException {
		if (debug) {
			log("seguranca: antes do processo");
		}
	}
    
	private void doAfterProcessing(ServletRequest request,
			ServletResponse response) throws IOException, ServletException {
		if (debug) {
			log("seguranca: depois do processo");
		}
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		if (debug) {
			log("seguranca: Filtro");
		}
		doBeforeProcessing(request, response);
		Throwable problem = null;
		try {
			HttpServletRequest hreq = (HttpServletRequest) request;
			String pagina = hreq.getServletPath();
			String path = hreq.getSession().getServletContext().getContextPath();
			Usuario usuario = (Usuario) hreq.getSession().getAttribute(Contexto.getUsuario());
			if (usuario == null) {
				if (pagina.startsWith("/admin") && !pagina.contains("login")) {
					if (usuario == null) {
						((HttpServletResponse) response).sendRedirect(path+ "/index.php?contador=1");
					} 
				}
			}/*if(pagina.startsWith("/login") || Contexto.getUsuario_Sessao()==null){
				((HttpServletResponse) response).sendRedirect(path+ "/gestao/index.php");
			} */
			
			else {
				if(pagina.startsWith("/admin/login")) {
					((HttpServletResponse) response).sendRedirect(path+ "/admin/home.php");
				}
				if(pagina.equalsIgnoreCase("/admin")) {
					((HttpServletResponse) response).sendRedirect(path+ "/admin/home.php");
				}
			}

			try {
				chain.doFilter(request, response);
			} catch (Exception e) {
			}
		} catch (Throwable t) {
			problem = t;
		}
		doAfterProcessing(request, response);
		if (problem != null) {
			if (problem instanceof ServletException) {
				throw (ServletException) problem;
			}
			if (problem instanceof IOException) {
				throw (IOException) problem;
			}
			sendProcessingError(problem, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		if (filterConfig != null) {
			if (debug) {
				log("seguranca: Inicialização do Filtro");
			}
		}
	}

	private void sendProcessingError(Throwable t, ServletResponse response) {
		String stackTrace = getStackTrace(t);
		if (stackTrace != null && !stackTrace.equals("")) {
			try {
				response.setContentType("text/html");
				PrintStream ps = new PrintStream(response.getOutputStream());
				PrintWriter pw = new PrintWriter(ps);
				pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n");
				pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
				pw.print(stackTrace);
				pw.print("</pre></body>\n</html>");
				pw.close();
				ps.close();
				response.getOutputStream().close();
			} catch (Exception ex) {
			}
		} else {
			try {
				PrintStream ps = new PrintStream(response.getOutputStream());
				t.printStackTrace(ps);
				ps.close();
				response.getOutputStream().close();
			} catch (Exception ex) {
			}
		}
	}
	public static String getStackTrace(Throwable t) {
		String stackTrace = null;
		try {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			t.printStackTrace(pw);
			pw.close();
			sw.close();
			stackTrace = sw.getBuffer().toString();
		} catch (Exception ex) {
		}
		return stackTrace;
	}
	
	/*private AcessoFacadeLocal lookupAcessoLocal(){
		try {
			Context c = new InitialContext();
			return (AcessoFacadeLocal) c.lookup("java:global/curupira/AcessoFacade");
		} catch (NamingException e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "excessoes apanhadas", e);
			throw new RuntimeException();
		}
}*/
	
}
