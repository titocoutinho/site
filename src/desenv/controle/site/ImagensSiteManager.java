package desenv.controle.site;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import desenv.modelo.entidade.conteudo.Galeria;
import desenv.modelo.persistencia.conteudo.GaleriaRepo;
import desenv.util.generico.controle.ModeloVisao;
@ManagedBean
public class ImagensSiteManager extends ModeloVisao<Galeria> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String galeria = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("galeria"); 

	
	@Override
	public Galeria getObjeto() {
		if (objeto == null)
			objeto = new Galeria();
		return objeto;
	}

	public ImagensSiteManager() {
		setGenericRepositorio(new GaleriaRepo());
	}

	@PostConstruct
	public void cargaInicial() {
		objeto = new Galeria();
		modoInicialPadrao();

	}
	
	public Galeria selecionado(){
		try{
			objeto = new GaleriaRepo().procura(Long.parseLong(galeria));
			return objeto;
		}catch (Exception e){
			e.fillInStackTrace();
			return null;
		}
	}
}
