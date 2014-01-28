package desenv.modelo.entidade.conteudo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import desenv.modelo.entidade.acesso.Usuario;
import desenv.modelo.entidade.demografia.Municipio;
import desenv.util.generico.enums.Boleano;
import desenv.util.generico.modelo.Modelo;

@Entity
@Table(name = "artigo")
@Where(clause="deletado = 'NAO'")
public class Artigo extends Modelo {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	@Basic
	@Column(nullable = false)
	@Lob
	private String corpo;
	private String nota;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "destaque")
	private Boleano destaque = Boleano.NAO; // sim = 1, não = 2
	
	private boolean permiteComentario;

	@ManyToOne
	private Municipio municipio = new Municipio();

	@ManyToOne
	private Usuario usuario = new Usuario();

	@OneToOne
	private Galeria galeria;

	@ManyToOne
	private Categoria categoria = new Categoria();

	@OneToMany(mappedBy = "artigo", cascade = CascadeType.REMOVE)
	@Where(clause="deletado = 'NAO'")
	private List<Comentario> comentarios = new ArrayList<Comentario>();
	
	@Lob
	@Column(name = "photo")
	private byte[] photo;

	public Artigo() {

	}
	
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCorpo() {
		return corpo;
	}

	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Galeria getGaleria() {
		return galeria;
	}

	public void setGaleria(Galeria galeria) {
		this.galeria = galeria;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isPermiteComentario() {
		return permiteComentario;
	}

	public void setPermiteComentario(boolean permiteComentario) {
		this.permiteComentario = permiteComentario;
	}
	
	public Boleano getDestaque() {
		return destaque;
	}
	public void setDestaque(Boleano destaque) {
		this.destaque = destaque;
	}
	public String textoResumido(Integer tamanho){
		String retorno = "";
		try{
		retorno = this.corpo.replaceAll("<.*?>", "");
		if(tamanho > retorno.length()) {
			tamanho = retorno.length();
		}
		
		}
		catch (NullPointerException e){
			return corpo;
		}
		return retorno.substring(0, tamanho);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		 int hash = 5;
	        hash = (int) (41 * hash + getId().intValue());
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
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Artigo)) {
			return false;
		}
		Artigo other = (Artigo) obj;
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
	
	return getTitulo();
}
}
