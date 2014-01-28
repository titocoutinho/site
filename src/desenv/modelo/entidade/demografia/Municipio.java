package desenv.modelo.entidade.demografia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import desenv.modelo.entidade.conteudo.Artigo;
import desenv.util.TrataTexto;
import desenv.util.generico.modelo.Modelo;

@Entity
@Table(name = "municipio")
@Where(clause = "deletado = 'NAO'")
public class Municipio extends Modelo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2229542078351496890L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String sigla;
	@OneToMany(mappedBy = "municipio", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@OrderBy(value = "prioridade")
	private List<Dado> dados = new ArrayList<Dado>();
	@OneToMany(mappedBy = "municipio")
	@Where(clause = "deletado = 'NAO'")
	private List<Artigo> artigos;

	public Municipio() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public String getNomeSemAcento() {
		return TrataTexto.removeAccents(getNome());
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public List<Dado> getDados() {
		return dados;
	}

	public void setDados(List<Dado> dados) {
		this.dados = dados;
	}

	public List<Artigo> getArtigos() {
		return artigos;
	}

	public void setArtigos(List<Artigo> artigos) {
		this.artigos = artigos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */

	@Override
	public int hashCode() {
		int hash = 5;
		hash = (int) (41 * hash + getId().intValue());
		return hash;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		if (!(obj instanceof Municipio)) {
			return false;
		}
		Municipio other = (Municipio) obj;
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

		return getNome();
	}
}
