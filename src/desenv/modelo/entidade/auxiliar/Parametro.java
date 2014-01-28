package desenv.modelo.entidade.auxiliar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import desenv.util.generico.modelo.Modelo;

@Entity
@Table(name="parametro")
@Where(clause="deletado = 'NAO'")
public class Parametro extends Modelo {

	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="nome_parametro")
	private String nomeParametro;
	@Column(name="valor_parametro")
	private String valorParametro;
	@Column(name="grupo_parametro")
	private String grupoParametro;
	

	public static void main(String[] args) {

	}


	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * @return the nomeParametro
	 */
	public String getNomeParametro() {
		return nomeParametro;
	}


	/**
	 * @param nomeParametro the nomeParametro to set
	 */
	public void setNomeParametro(String nomeParametro) {
		this.nomeParametro = nomeParametro;
	}


	/**
	 * @return the valorParametro
	 */
	public String getValorParametro() {
		return valorParametro;
	}


	/**
	 * @param valorParametro the valorParametro to set
	 */
	public void setValorParametro(String valorParametro) {
		this.valorParametro = valorParametro;
	}


	/**
	 * @return the grupoParametro
	 */
	public String getGrupoParametro() {
		return grupoParametro;
	}


	/**
	 * @param grupoParametro the grupoParametro to set
	 */
	public void setGrupoParametro(String grupoParametro) {
		this.grupoParametro = grupoParametro;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Parametro)) {
			return false;
		}
		Parametro other = (Parametro) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
	
		return getNomeParametro();
	}
	

}
