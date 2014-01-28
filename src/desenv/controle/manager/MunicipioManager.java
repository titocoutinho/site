package desenv.controle.manager;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.RowEditEvent;

import desenv.modelo.entidade.demografia.Caracteristica;
import desenv.modelo.entidade.demografia.Dado;
import desenv.modelo.entidade.demografia.Municipio;
import desenv.modelo.persistencia.demografia.CaracteristicaRepo;
import desenv.modelo.persistencia.demografia.DadoRepo;
import desenv.modelo.persistencia.demografia.MunicipioRepo;
import desenv.util.generico.controle.ModeloVisao;

@ManagedBean
@ViewScoped
public class MunicipioManager extends ModeloVisao<Municipio> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Dado dadoSelectionado;

	

	public MunicipioManager() {
		setGenericRepositorio(new MunicipioRepo());
	}

	@PostConstruct
	public void cargaInicial() {
		objeto = new Municipio();
		modoInicialPadrao();

	}
	@Override
	public void cadastrar(ActionEvent action) {
		CaracteristicaRepo cr = new CaracteristicaRepo();
		DadoRepo dr = new DadoRepo();
		Integer contadorCaracteristicas = cr.getCount().intValue();

	
		Integer contadorDados = dr.listaPorMunicipio(objeto.getId()).size();
		
		if (contadorCaracteristicas == contadorDados) {
			objeto.setDados(dr.listaPorMunicipio(objeto.getId()));
		super.cadastrar(action);
		return;
		}
		if (contadorCaracteristicas > contadorDados) {

			List<Caracteristica> caracteristicas = cr.getLista();

			for (Caracteristica cac : caracteristicas) {
				if(!objeto.getDados().contains(cac)){
					Dado d1 = new Dado();
					d1.setCaracteristica(cac);
					d1.setMunicipio(objeto);
					new DadoRepo().adiciona(d1);
					objeto.getDados().add(d1);
				}
			}

		}
		super.cadastrar(action);
	}
	@Override
	public void adiciona() {

		CaracteristicaRepo cr = new CaracteristicaRepo();
		DadoRepo dr = new DadoRepo();
		Integer contadorCaracteristicas = cr.getCount().intValue();

	
		Integer contadorDados = dr.listaPorMunicipio(objeto.getId()).size();
		
		if (contadorCaracteristicas == contadorDados) {
			objeto.setDados(dr.listaPorMunicipio(objeto.getId()));
			
			super.adiciona();
			return;
		}
		if (contadorCaracteristicas > contadorDados) {

			List<Caracteristica> caracteristicas = cr.getLista();

			for (Caracteristica cac : caracteristicas) {
				if(!objeto.getDados().contains(cac)){
					Dado d1 = new Dado();
					d1.setCaracteristica(cac);
					d1.setMunicipio(objeto);
					new DadoRepo().adiciona(d1);
					objeto.getDados().add(d1);
				}
			}

		}
		super.adiciona();

	}
	
	public void addDado(Dado dado){
		new  DadoRepo().atualiza(dado);
	}
	@Override
	public void alterar(ActionEvent action) {
		super.alterar(action);
	}
	public Dado getDadoSelectionado() {
		return dadoSelectionado;
	}
	public void setDadoSelectionado(Dado dadoSelectionado) {
		this.dadoSelectionado = dadoSelectionado;
	}
	
	public void onEdit(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Edição efetuada", ((Dado) event.getObject()).getCaracteristica().getNomeCaracteristica());  
  
        addDado((Dado)event.getObject());
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
      
    public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Edição Cancelada", ((Dado) event.getObject()).getCaracteristica().getNomeCaracteristica());  
        
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
    @Override
	public Municipio getObjeto() {
		if (objeto == null)
			objeto = new Municipio();
		return objeto;
	}
    @Override
    public void setObjeto(Municipio objeto) {
    	super.setObjeto(objeto);
    }
	
}
