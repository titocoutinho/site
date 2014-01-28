package desenv.modelo.entidade.demografia;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import desenv.util.generico.modelo.Modelo;

@Entity
@Table(name = "prefeito")
@Where(clause="deletado = 'NAO'")
public class Prefeito extends Modelo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8567371708801660337L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomePrefeito;
	private Date inicioMandato;
	private Date fimMandato;
	private boolean atual;

	public Prefeito() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomePrefeito() {
		return nomePrefeito;
	}

	public void setNomePrefeito(String nomePrefeito) {
		this.nomePrefeito = nomePrefeito;
	}

	public Date getInicioMandato() {
		return inicioMandato;
	}

	public void setInicioMandato(Date inicioMandato) {
		this.inicioMandato = inicioMandato;
	}

	public Date getFimMandato() {
		return fimMandato;
	}

	public void setFimMandato(Date fimMandato) {
		this.fimMandato = fimMandato;
	}

	public boolean isAtual() {
		return atual;
	}

	public void setAtual(boolean atual) {
		this.atual = atual;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (atual ? 1231 : 1237);
		result = prime * result
				+ ((fimMandato == null) ? 0 : fimMandato.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((inicioMandato == null) ? 0 : inicioMandato.hashCode());
		result = prime * result
				+ ((nomePrefeito == null) ? 0 : nomePrefeito.hashCode());
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
		Prefeito other = (Prefeito) obj;
		if (atual != other.atual)
			return false;
		if (fimMandato == null) {
			if (other.fimMandato != null)
				return false;
		} else if (!fimMandato.equals(other.fimMandato))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inicioMandato == null) {
			if (other.inicioMandato != null)
				return false;
		} else if (!inicioMandato.equals(other.inicioMandato))
			return false;
		if (nomePrefeito == null) {
			if (other.nomePrefeito != null)
				return false;
		} else if (!nomePrefeito.equals(other.nomePrefeito))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
	
		return getNomePrefeito();
	}
}
