package desenv.modelo.entidade.demografia;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import desenv.util.generico.modelo.Modelo;

@Entity
@Table(name = "caracteristica")
@Where(clause = "deletado = 'NAO'")
public class Caracteristica extends Modelo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2276451111196907594L;
	/**
	 * 
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomeCaracteristica;

	private boolean ativo;
	@OneToMany(mappedBy = "caracteristica")
	@Where(clause = "deletado = 'NAO'")
	private List<Dado> dados;

	public Caracteristica() {
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nomeCaracteristica
	 */
	public String getNomeCaracteristica() {
		return nomeCaracteristica;
	}

	/**
	 * @param nomeCaracteristica
	 *            the nomeCaracteristica to set
	 */
	public void setNomeCaracteristica(String nomeCaracteristica) {
		this.nomeCaracteristica = nomeCaracteristica;
	}

	/**
	 * @return the ativo
	 */
	public boolean isAtivo() {
		return ativo;
	}

	/**
	 * @param ativo
	 *            the ativo to set
	 */
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	/**
	 * @return the dados
	 */
	public List<Dado> getDados() {
		return dados;
	}

	/**
	 * @param dados
	 *            the dados to set
	 */
	public void setDados(List<Dado> dados) {
		this.dados = dados;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (ativo ? 1231 : 1237);
		result = prime * result + ((dados == null) ? 0 : dados.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime
				* result
				+ ((nomeCaracteristica == null) ? 0 : nomeCaracteristica
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Caracteristica other = (Caracteristica) obj;
		if (ativo != other.ativo)
			return false;
		if (dados == null) {
			if (other.dados != null)
				return false;
		} else if (!dados.equals(other.dados))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nomeCaracteristica == null) {
			if (other.nomeCaracteristica != null)
				return false;
		} else if (!nomeCaracteristica.equals(other.nomeCaracteristica))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getNomeCaracteristica();
	}

}
