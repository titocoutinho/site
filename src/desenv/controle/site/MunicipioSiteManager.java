package desenv.controle.site;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import desenv.modelo.entidade.demografia.Municipio;
import desenv.modelo.persistencia.demografia.MunicipioRepo;
import desenv.util.generico.controle.ModeloVisao;

@ManagedBean
@ViewScoped
public class MunicipioSiteManager extends ModeloVisao<Municipio> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String informacao;

	String municipio = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("municipio");
	String subcategoria = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("subcategoria");

	@Override
	public Municipio getObjeto() {
		if (objeto == null)
			objeto = new Municipio();
		return objeto;
	}

	public MunicipioSiteManager() {
		setGenericRepositorio(new MunicipioRepo());
	}

	@PostConstruct
	public void cargaInicial() {
		objeto = new Municipio();
		modoInicialPadrao();
	}

	public Municipio selecionado() {
		try {
			objeto = new MunicipioRepo().pesquisaPorNome(municipio);
			new MunicipioRepo().pesquisaPorNome(municipio);
			return objeto;
		} catch (Exception e) {
			e.getStackTrace();
			return new Municipio();
		} finally {
		}
	}
	
	public void carregaInformacao(String info){
		System.out.println(info);
		setInformacao(info);
		
		informacao = info;
	}

	public String getInformacao() {
		return informacao;
	}

	public void setInformacao(String informacao) {
		this.informacao = informacao;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(String subcategoria) {
		this.subcategoria = subcategoria;
	}

}
