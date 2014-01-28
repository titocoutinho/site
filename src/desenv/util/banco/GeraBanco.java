/**
 * 
 */
package desenv.util.banco;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/***************************
 * 						   *
 * @author Victor Coutinho *
 * 						   *
 * @since 21 / 11 / 2012   *
 *						   *
 ***************************/
public class GeraBanco {
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("site_PU");
		factory.close();
	}
}
