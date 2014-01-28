package desenv.util.generico.persistencia;

import java.util.List;

public interface IGenericRepository<T> {

	public abstract void adiciona(T jogador);

	public abstract void remove(Long id);

	public abstract T atualiza(T jogador);


	public abstract T procura(Long id);

	public abstract List<T> getLista();

	public abstract Long getCount();

	public abstract T novaInstancia();

}