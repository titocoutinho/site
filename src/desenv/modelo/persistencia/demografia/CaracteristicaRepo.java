package desenv.modelo.persistencia.demografia;

import javax.persistence.EntityManager;

import desenv.modelo.entidade.demografia.Caracteristica;
import desenv.util.generico.persistencia.GenericRepository;

public class CaracteristicaRepo extends GenericRepository<Caracteristica> {

	public CaracteristicaRepo(EntityManager manager, Class<Caracteristica> entityClass) {
		super(manager, entityClass);

	}

	public CaracteristicaRepo(EntityManager manager) {
		super(manager, Caracteristica.class);

	}

	public CaracteristicaRepo() {
		super(getManager(), Caracteristica.class);
	}

}
