package desenv.modelo.entidade.conteudo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import desenv.util.generico.modelo.Modelo;
@Entity
@Table(name="imagem")
@Where(clause="deletado = 'NAO'")
public class Imagem extends Modelo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1494522749300243064L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String nome;
	@ManyToOne(cascade=CascadeType.ALL)
	private Galeria galeria;
	@ManyToOne
	private Artigo artigo;

	public Imagem() {
		
	}

	
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	
	public String getTitulo() {
		return titulo;
	}
	public String getThumb(){
		return titulo.replace(".png", "").replace(".jpg", "").replace(".gif", "").concat(".thumb.png");
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public Galeria getGaleria() {
		return galeria;
	}

	public void setGaleria(Galeria galeria) {
		this.galeria = galeria;
	}


	public Artigo getArtigo() {
		return artigo;
	}

	public void setArtigo(Artigo artigo) {
		this.artigo = artigo;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	
	@Override
	public int hashCode() {
		 int hash = 5;
	        hash = (int) (41 * hash + getId().intValue());
	        return hash;
	}


	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Imagem)) {
			return false;
		}
		Imagem other = (Imagem) obj;
		if (artigo == null) {
			if (other.artigo != null) {
				return false;
			}
		} else if (!artigo.equals(other.artigo)) {
			return false;
		}
		if (galeria == null) {
			if (other.galeria != null) {
				return false;
			}
		} else if (!galeria.equals(other.galeria)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (titulo == null) {
			if (other.titulo != null) {
				return false;
			}
		} else if (!titulo.equals(other.titulo)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
	
		return getTitulo();
	}
	
}
