package desenv.util.generico.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import desenv.util.generico.enums.Boleano;

@MappedSuperclass
public class Modelo implements Serializable, IModelo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Column(name = "versao")
	Integer versao =0;

	
	@Column(name = "data_criacao", updatable = false, nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	Date criado = new Date();

	@Column(name = "data_atuaizacao", insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	Date modificado ;//= new Date();
	
	@Enumerated(EnumType.STRING)
	@Column(name = "deletado")
	private Boleano excluido = Boleano.NAO; // sim = 1, não = 2

	

	public Integer getVersao() {
		return versao;
	}

	public void setVersao(Integer versao) {
		this.versao = versao;
	}

	public Date getCriado() {
		return criado;
	}

	public void setCriado(Date criado) {
		this.criado = criado;
	}

	public Date getModificado() {
		return modificado;
	}

	public void setModificado(Date modificado) {
		this.modificado = modificado;
	}

	public Boleano getExcluido() {
		return excluido;
	}

	public void setExcluido(Boleano excluido) {
		this.excluido = excluido;
	}

	@Override
	public Long getId() {
		
		return null;
	}

	@Override
	public void setId(Long id) {
		
		
	}




	
	
	/*@Override
	public int hashCode() {
		 int hash = 5;
	        hash = (int) (41 * hash + getId().intValue());
	        return hash;
	}*/
	
}
