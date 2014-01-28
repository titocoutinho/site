package desenv.modelo.persistencia.conteudo;

import javax.persistence.EntityManager;

import desenv.modelo.entidade.conteudo.Imagem;
import desenv.util.generico.persistencia.GenericRepository;

public class ImagemRepo extends GenericRepository<Imagem> {

	public ImagemRepo(EntityManager manager, Class<Imagem> entityClass) {
		super(manager, entityClass);

	}

	public ImagemRepo(EntityManager manager) {
		super(manager, Imagem.class);

	}

	public ImagemRepo() {
		super(getManager(), Imagem.class);
	}

}
