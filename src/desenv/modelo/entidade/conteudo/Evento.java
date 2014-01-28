package desenv.modelo.entidade.conteudo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import desenv.util.generico.enums.Boleano;
import desenv.util.generico.modelo.Modelo;

@Entity
@Table(name = "evento")
@Where(clause="deletado = 'NAO'")
public class Evento extends Modelo {

	private static final long serialVersionUID = 2229542078351496890L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String descricao;

	private Date dataInicio;

	private Date dataFim;

	private boolean diaTodo;
	
	private Boleano ativo;

	public Evento() {
	}

	
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
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}


	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	/**
	 * @return the dataInicio
	 */
	public Date getDataInicio() {
		return dataInicio;
	}


	/**
	 * @param dataInicio the dataInicio to set
	 */
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}


	/**
	 * @return the dataFim
	 */
	public Date getDataFim() {
		return dataFim;
	}


	/**
	 * @param dataFim the dataFim to set
	 */
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}


	/**
	 * @return the diaTodo
	 */
	public boolean isDiaTodo() {
		return diaTodo;
	}


	/**
	 * @param diaTodo the diaTodo to set
	 */
	public void setDiaTodo(boolean diaTodo) {
		this.diaTodo = diaTodo;
	}


	/**
	 * @return the ativo
	 */
	public Boleano getAtivo() {
		return ativo;
	}


	/**
	 * @param ativo the ativo to set
	 */
	public void setAtivo(Boleano ativo) {
		this.ativo = ativo;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof Evento)) {
				return false;
			}
			Evento other = (Evento) obj;
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
			
			return getDescricao();
		}

}
