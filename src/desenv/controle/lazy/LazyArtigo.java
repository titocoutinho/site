package desenv.controle.lazy;

import java.util.ArrayList;
import java.util.List;

import desenv.modelo.entidade.conteudo.Artigo;
import desenv.modelo.persistencia.conteudo.ArtigoRepo;

public class LazyArtigo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final int TOTAL_REGISTROS_POR_PAGINA = 5;

    private int totalDeRegistros;
    private List<Artigo> artigos;
    
    private int registroAtual;

    public LazyArtigo(int quantidadeArtigoPrincipal) {
    	
    	registroAtual = quantidadeArtigoPrincipal;
    	
    	this.artigos = new ArrayList<Artigo>();        
        this.totalDeRegistros = new ArtigoRepo().getCount().intValue();

        if (totalDeRegistros > 0) {
            carregaArtigos();
        }
        
    }

    public void carregaArtigos() {
        artigos.addAll(new ArtigoRepo().pesquisaComLazy(registroAtual, TOTAL_REGISTROS_POR_PAGINA)); 
    }
    
    public void proximaPagina() {
    	registroAtual = registroAtual + TOTAL_REGISTROS_POR_PAGINA;
    	carregaArtigos();
    }

    public int getTotalDeRegistros() {
        return totalDeRegistros;
    }

    public List<Artigo> getArtigos() {
        return artigos;
    }
    
    public boolean temMais() {
    	return (totalDeRegistros > registroAtual+TOTAL_REGISTROS_POR_PAGINA) ? true : false;
    }

}