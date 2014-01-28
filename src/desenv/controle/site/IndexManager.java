package desenv.controle.site;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import desenv.controle.lazy.LazyArtigo;
import desenv.modelo.entidade.acesso.Contador;
import desenv.modelo.entidade.conteudo.Artigo;
import desenv.modelo.persistencia.acesso.ContadorRepo;
import desenv.modelo.persistencia.conteudo.ArtigoRepo;
import desenv.util.generico.controle.ModeloVisao;

@ManagedBean
public class IndexManager extends ModeloVisao<Artigo> implements Serializable {

	private static final int QUANTIDADE_ARTIGOS_PRINCIPAL = 9;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LazyArtigo lazy = new LazyArtigo(QUANTIDADE_ARTIGOS_PRINCIPAL);
	String categoria = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("categoria");
	String materia = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("materia");
	String contador = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("contador");

	private Artigo primeiroArtigo = new Artigo();

	

	public IndexManager() {
		setGenericRepositorio(new ArtigoRepo());
	}

	

	public List<Artigo> ultimasNoticias() {
		return new ArtigoRepo().getUltimosRegistros(9);
	}

	public List<Artigo> listaArtigoCategoria() {
		List<Artigo> artigos = new ArrayList<Artigo>();

		try {
			artigos.addAll(new ArtigoRepo().getBuscaPorCategoria(Long
					.parseLong(categoria)));
			
		} catch (NumberFormatException e) {
			artigos.addAll(new ArtigoRepo()
					.getUltimosRegistros(QUANTIDADE_ARTIGOS_PRINCIPAL));
		}

		return artigos;
	}

	public List<Artigo> getDestaquesArtigoCarrosel() {
		try {

			return new ArtigoRepo().perquisaDestaque();
		} catch (Exception e) {
			return null;
		}

	}

	@PostConstruct
	public void cargaInicial() {
		objeto = new Artigo();
		modoInicialPadrao();
		try{
		if(contador.equals("1")){
		}}
		catch (NullPointerException e){
			Contador c = new Contador();
			c.setArea("SITE");
			c.setUsuario("ANONIMO");
			new ContadorRepo().adiciona(c);
		}
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
	public String getContador() {
		return contador;
	}
	public void setContador(String contador) {
		this.contador = contador;
	}

	public LazyArtigo getLazy() {
		return lazy;
	}

	public void setLazy(LazyArtigo lazy) {
		this.lazy = lazy;
	}

	public Artigo getPrimeiroArtigo() {
		try {
			primeiroArtigo = new ArtigoRepo().getPrimeiroArtigo().get(0);
		} catch (Exception e) {
			primeiroArtigo = new Artigo();
			primeiroArtigo.setTitulo("Em construção");
		}
		return primeiroArtigo;
	}

	@Override
	public Artigo getObjeto() {
		if (objeto == null)
			objeto = new Artigo();
		return objeto;
	}
}
