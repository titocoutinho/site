package desenv.util.generico.modelo;

import java.io.Serializable;
import java.util.Date;

import desenv.util.generico.enums.Boleano;

/**
 * 
 * @author Victor Coutinho
 * 
 */
public interface IModelo extends Serializable {

	Long getId();

	void setId(Long id);

	public void setExcluido(Boleano excluido);

	public Integer getVersao();

	public void setVersao(Integer versao);

	public void setCriado(Date criado);

	public void setModificado(Date modificado);

}
