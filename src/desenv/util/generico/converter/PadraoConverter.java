package desenv.util.generico.converter;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import desenv.util.generico.persistencia.IGenericRepository;

/**
 * 
 * @author Victor Coutinho
 * 
 */
public class PadraoConverter {

	private  static final String aplicacao = "site";
	
	/**
	 * <h2>lookupLocal</h2>
	 * <p>Metodo para injetar um ejb local presente dentro da aplicacao, esse lookup generico pode ser utilizado
	 * principalmente no </p>
	 * <p>O m�todo retorna um objeto do tipo IAbstractFacade e precisa fazer um cast para o EJB Necessario</p>
	 * 
	 * @return {@link IAbstractFacade}
	 * 
	 * 
	 */
	protected IGenericRepository<?> lookupLocal() {
		@SuppressWarnings("rawtypes")
		Class fc = this.getClass().getAnnotation(FacesConverter.class).forClass();
		StringBuilder entidade = new StringBuilder();
		entidade.append("java:global/")
				.append(aplicacao)
				.append("/")
				.append(fc.getSimpleName())
				.append("EJB");
		try {
			Context c = new InitialContext();
			return (IGenericRepository<?>) c.lookup(entidade.toString());
		} catch (NamingException e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE,"excessoes apanhadas", e);
			throw new RuntimeException();
		}
	}
	
	protected EntityManager getManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		return (EntityManager) request.getAttribute("EntityManager");
	}

}
