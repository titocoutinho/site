/**
 * 
 */
package desenv.util.generico.controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

/***************************
 * 						   *
 * @author Victor Coutinho *
 * 						   *
 * @param <T>
 * @since 21 / 11 / 2012   *
 *						   *
 ***************************/
public class BeanModelo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private EntityManager manager;

	private List<SelectItem> turnos = new ArrayList<SelectItem>();
	private List<SelectItem> empresas = new ArrayList<SelectItem>();
	private List<SelectItem> faixaEtaria = new ArrayList<SelectItem>();
	private List<SelectItem> ativos = new ArrayList<SelectItem>();
	private List<SelectItem> estados = new ArrayList<SelectItem>();
	private List<SelectItem> escolaridades = new ArrayList<SelectItem>();
	private List<SelectItem> sexos = new ArrayList<SelectItem>();
	private List<SelectItem> vinculos = new ArrayList<SelectItem>();
	
	public static final String PESQUISAR_STATE = "pesquisar";
	public static final String ADICIONAR_STATE = "adicionar";
	public static final String EDITAR_STATE = "editar";
	
	
	
	
	public BeanModelo() {
		manager = getManager();
	}

	
	 protected void carregarListas() {
		
			
		}


	protected EntityManager getManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		return (EntityManager) request.getAttribute("EntityManager");
	}
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}
	

	public List<SelectItem> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<SelectItem> turnos) {
		this.turnos = turnos;
	}

	public List<SelectItem> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<SelectItem> empresas) {
		this.empresas = empresas;
	}

	public List<SelectItem> getFaixaEtaria() {
		return faixaEtaria;
	}

	public void setFaixaEtaria(List<SelectItem> faixaEtaria) {
		this.faixaEtaria = faixaEtaria;
	}

	public List<SelectItem> getAtivos() {
		return ativos;
	}

	public void setAtivos(List<SelectItem> ativos) {
		this.ativos = ativos;
	}
	public List<SelectItem> getEstados() {
		return estados;
	}

	public void setEstados(List<SelectItem> estados) {
		this.estados = estados;
	}

	public List<SelectItem> getEscolaridades() {
		return escolaridades;
	}

	public void setEscolaridades(List<SelectItem> escolaridades) {
		this.escolaridades = escolaridades;
	}

	public List<SelectItem> getSexos() {
		return sexos;
	}

	public void setSexos(List<SelectItem> sexos) {
		this.sexos = sexos;
	}

	public List<SelectItem> getVinculos() {
		return vinculos;
	}

	public void setVinculos(List<SelectItem> vinculos) {
		this.vinculos = vinculos;
	}
	
	
}
