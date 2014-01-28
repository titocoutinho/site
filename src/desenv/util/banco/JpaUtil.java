package desenv.util.banco;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import desenv.modelo.persistencia.auxiliar.ThreadBase;

public class JpaUtil {

	private static EntityManagerFactory manager ; 
	private static ThreadBase base ;
	private JpaUtil() {
		
	}
	
	static{
		manager = Persistence.createEntityManagerFactory("site_PU");
		base = new ThreadBase();
	}

	public static EntityManager getManager() {
		
		try{
		if(manager == null){
			manager = Persistence.createEntityManagerFactory("site_PU");
			base.run();
		}
		return manager.createEntityManager();
		} catch (Exception e){
			return Persistence.createEntityManagerFactory("site_PU").createEntityManager();
		}
		
	}

}

/**
if (emf == null) {  
emf = Persistence.createEntityManagerFactory("default");  
}  
return emf.createEntityManager();  
*/