package desenv.controle.site;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import desenv.controle.lazy.LazyArtigo;
import desenv.modelo.entidade.conteudo.Artigo;
import desenv.modelo.entidade.conteudo.Categoria;
import desenv.modelo.persistencia.conteudo.ArtigoRepo;
import desenv.util.generico.controle.ModeloVisao;
import desenv.util.web.Contexto;
@ManagedBean
public class CategoriaSiteManager  extends ModeloVisao<Artigo> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int QUANTIDADE_ARTIGOS_PRINCIPAL = 4;
	private LazyArtigo listaLazy = new LazyArtigo(QUANTIDADE_ARTIGOS_PRINCIPAL);
	private Artigo primeiroArtigo = new Artigo();
	
	String categoria = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("categoria");
	String materia = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("materia");
	String municipio = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("municipio");

	public CategoriaSiteManager() {
		
	}

	public Artigo artigoSelecionado() {
		try {
			String pagina = Contexto.getRequest().getServletPath();

			for (Categoria cat : Contexto.getMenu()) {
				if (pagina.substring(1).equalsIgnoreCase(cat.getUrlDefinida())) {
					return new ArtigoRepo().getUltimoArtigoPorCategoria(cat.getId());
				}

			}
			return new ArtigoRepo().buscaPorCategoriaEArtigo(Long.parseLong(categoria), Long.parseLong(materia));
		} catch (NumberFormatException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unused")
	private List<Artigo> getMateriasPorMunicipio(){
		List<Artigo> retorno = new ArrayList<Artigo>();
			try{
				retorno.addAll(new ArtigoRepo().pesquisaMateriaPorMunicipio(Long.parseLong(municipio)));
			} catch (Exception e){
				e.printStackTrace();
			}
		return retorno;
	}
	@SuppressWarnings("unused")
	private List<Artigo> getDestaquePorCategoria(){
		List<Artigo> retorno= new ArrayList<Artigo>();
		try{
			retorno.addAll(new ArtigoRepo().perquisaDestaqueCategoria(Long.parseLong(categoria)));
		}catch(Exception e){
			e.printStackTrace();
		}
			
		return retorno;
	}
	
	public List<Artigo> listaArtigoCategoria() {
		List<Artigo> artigos = new ArrayList<Artigo>();
		try {
			artigos.addAll(new ArtigoRepo().getBuscaPorCategoria(Long.parseLong(categoria))); 
			
		} catch (NumberFormatException e) {
			artigos.addAll(new ArtigoRepo().getUltimosRegistros(QUANTIDADE_ARTIGOS_PRINCIPAL));
		}
		return artigos;
	}
	
	
	
	public List<Artigo> getDestaquesArtigoCarrosel(){
		List<Artigo> carrosel = new ArrayList<Artigo>();
		try{
			carrosel.addAll(new ArtigoRepo().pesquisaCarrosel(Long.parseLong(categoria)));			
		}catch (Exception e){
			e.printStackTrace();
			return new ArtigoRepo().perquisaDestaque();
		}
System.out.println("carossel"+carrosel.size());
		return carrosel;
	}
	
	
	
	public LazyArtigo getListaLazy() {
		return listaLazy;
	}

	public void setListaLazy(LazyArtigo listaLazy) { 
		this.listaLazy = listaLazy;
	}

	public Artigo getPrimeiroArtigo() {
		try{
		primeiroArtigo = (Artigo) new ArtigoRepo().getBuscaPorPrimeiroArtigo(Long.parseLong(categoria)).get(0);
		}catch (Exception e){
			primeiroArtigo = new Artigo();
			primeiroArtigo.setTitulo("Em construção");
		}
		return primeiroArtigo;
	}

	
	
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getMateria() {
		return materia;
	}
	public void setMateria(String materia) {
		this.materia = materia;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	
	

}
