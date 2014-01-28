/**
 * 
 */
package desenv.util.filtro;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import desenv.util.banco.JpaUtil;

/***************************
 * 						   *
 * @author Victor Coutinho *
 * 						   *
 * @since 21 / 11 / 2012   *
 *						   *
 ***************************/
@WebFilter(servletNames = {"Faces Servlet"})
public class JPAFilter implements Filter {
	private EntityManagerFactory factory;
	

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.factory = JpaUtil.getManager().getEntityManagerFactory();
	}

	@Override
	public void destroy() {
		this.factory.close();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		EntityManager manager = JpaUtil.getManager();
		request.setAttribute("EntityManager", manager);
		manager.getTransaction().begin();
		chain.doFilter(request, response);
		try {
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
	}
}
