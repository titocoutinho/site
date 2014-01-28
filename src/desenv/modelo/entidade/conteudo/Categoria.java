package desenv.modelo.entidade.conteudo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import desenv.util.TrataTexto;
import desenv.util.generico.modelo.Modelo;

@Entity
@Table(name = "categoria")
@Where(clause="deletado = 'NAO'")
public class Categoria extends Modelo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4441655088735578626L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private boolean ativo;
	private Integer prioridade;
	private String urlDefinida;
	
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.REMOVE)
	@Where(clause="deletado = 'NAO'")
	private List<SubCategoria> subcategorias;

	public Categoria() {

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
	
	public String getNomeSemAcento(){
		return TrataTexto.removeAccents(getNome());
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public List<SubCategoria> getSubcategorias() {
		return subcategorias;
	}

	public void setSubcategorias(List<SubCategoria> subcategorias) {
		this.subcategorias = subcategorias;
	}

	public String getUrlDefinida() {
		return urlDefinida;
	}

	public void setUrlDefinida(String urlDefinida) {
		this.urlDefinida = urlDefinida;
	}

	public Integer getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		 int hash = 5;
	        hash = (int) (41 * hash + id.intValue());
	        return hash;
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
		if (!(obj instanceof Categoria)) {
			return false;
		}
		Categoria other = (Categoria) obj;
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
