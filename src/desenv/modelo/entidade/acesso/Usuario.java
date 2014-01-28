package desenv.modelo.entidade.acesso;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Where;

import desenv.modelo.entidade.conteudo.Artigo;
import desenv.util.generico.enums.Boleano;
import desenv.util.generico.modelo.Modelo;

@Entity
@Table(name = "usuario")
@Where(clause="deletado = 'NAO'")
public class Usuario extends Modelo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String senha;
	@Temporal(TemporalType.DATE)
	private Date nascimento;

	@Lob
	@Column(nullable = true, columnDefinition = "mediumblob")
	private byte[] image;
	@Enumerated(EnumType.ORDINAL)
	private Boleano ativo;
	@Enumerated(EnumType.ORDINAL)
	private Boleano administrador;

	@OneToMany(mappedBy = "usuario")
	@Where(clause="deletado = 'NAO'")
	private List<Artigo> artigos;

	@ManyToMany
	@JoinTable(name = "usuario_permissao", joinColumns = { @JoinColumn(name = "usuario_usuarioid") }, inverseJoinColumns = { @JoinColumn(name = "permissao_permissaoid") })
	private List<Permissao> permissoes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boleano getAtivo() {
		return ativo;
	}

	public void setAtivo(Boleano ativo) {
		this.ativo = ativo;
	}

	public Boleano getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Boleano administrador) {
		this.administrador = administrador;
	}
	
	public Date getNascimento() {
		return nascimento;
	}
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Artigo> getArtigos() {
		return artigos;
	}

	public void setArtigos(List<Artigo> artigos) {
		this.artigos = artigos;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
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
		if (!(obj instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) obj;
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
