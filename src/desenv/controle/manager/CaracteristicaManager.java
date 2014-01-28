package desenv.controle.manager;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import desenv.modelo.entidade.demografia.Caracteristica;
import desenv.modelo.entidade.demografia.Dado;
import desenv.modelo.entidade.demografia.Municipio;
import desenv.modelo.persistencia.demografia.CaracteristicaRepo;
import desenv.modelo.persistencia.demografia.DadoRepo;
import desenv.modelo.persistencia.demografia.MunicipioRepo;
import desenv.util.generico.controle.ModeloVisao;
@ManagedBean
public class CaracteristicaManager extends ModeloVisao<Caracteristica> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Caracteristica getObjeto() {
		if (objeto == null)
			objeto = new Caracteristica();
		return objeto;
	}

	public CaracteristicaManager() {
		setGenericRepositorio(new CaracteristicaRepo());
	}

	@PostConstruct
	public void cargaInicial() {
		objeto = new Caracteristica();
		modoInicialPadrao();

	}
	
	@Override
	public void cadastrar(ActionEvent action) {
		Long id = getObjeto().getId();
		super.cadastrar(action);

		MunicipioRepo mr = new MunicipioRepo();
		DadoRepo dr = new DadoRepo();
		
		int contadorMunicipios = mr.getCount().intValue();
		int contadorDados = dr.listaPorCaracteristica(getObjeto().getId()).size();
		
		if(contadorDados == contadorMunicipios){
			return;
		}else{
			objeto = new CaracteristicaRepo().procura(id);
			
			List<Municipio> municipios = mr.getLista();
			for (Municipio municipio : municipios) {
				boolean contem = false;
				for (Dado dd : municipio.getDados()) {
					if(contem){
						break;
					}
					if(dd.getCaracteristica().getId().equals(id)){
						contem = true;
					}
				}
				if(!contem){
					Dado d1 = new Dado();
					d1.setCaracteristica(getObjeto());
					d1.setMunicipio(municipio);
					municipio.getDados().add(d1);
					dr.adiciona(d1);
					mr.atualiza(municipio);
				}
				
			}
		}
	}
	@Override
	public void adiciona() {
		Long id = getObjeto().getId();
		super.adiciona();
		
		MunicipioRepo mr = new MunicipioRepo();
		DadoRepo dr = new DadoRepo();
		
		int contadorMunicipios = mr.getCount().intValue();
		int contadorDados = dr.listaPorCaracteristica(getObjeto().getId()).size();
		
		if(contadorDados == contadorMunicipios){
			return;
		}else{
			objeto = new CaracteristicaRepo().procura(id);
			
			List<Municipio> municipios = mr.getLista();
			for (Municipio municipio : municipios) {
				boolean contem = false;
				for (Dado dd : municipio.getDados()) {
					if(contem){
						break;
					}
					if(dd.getCaracteristica().getId().equals(id)){
						contem = true;
					}
				}
				if(!contem){
					Dado d1 = new Dado();
					d1.setCaracteristica(getObjeto());
					d1.setMunicipio(municipio);
					municipio.getDados().add(d1);
					dr.adiciona(d1);
					mr.atualiza(municipio);
				}
				
			}
		}
	}
	
}
