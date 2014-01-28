package desenv.controle.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import desenv.modelo.entidade.conteudo.Evento;
import desenv.modelo.persistencia.conteudo.EventoRepo;
import desenv.util.generico.controle.ModeloVisao;

@ManagedBean
@ViewScoped
public class EventoManager extends ModeloVisao<Evento> {

	private static final long serialVersionUID = 1L;

	private ScheduleModel eventModel;
	private List<Evento> eventos = new ArrayList<Evento>();

	@Override
	public Evento getObjeto() {
		if (objeto == null)
			objeto = new Evento();
		return objeto;
	}

	public EventoManager() {
		setGenericRepositorio(new EventoRepo());
	}

	@PostConstruct
	public void cargaInicial() {
		objeto = new Evento();
		modoInicialPadrao();
		 eventModel = new DefaultScheduleModel();
		eventos.addAll(new EventoRepo().getLista()) ;
		
		for (Evento evento : eventos) {
			DefaultScheduleEvent novoEvento = new DefaultScheduleEvent();
			novoEvento.setAllDay(evento.isDiaTodo());
			novoEvento.setEndDate(evento.getDataFim());
			novoEvento.setStartDate(evento.getDataInicio());
			novoEvento.setTitle(evento.getDescricao());
			novoEvento.setData(evento.getId());
			novoEvento.setEditable(true); 
			
			 eventModel.addEvent(novoEvento);
		}

	}
	
	public void salvar(){
        if(getObjeto().getId() == null || getObjeto().getId() == 0){ 
            if(getObjeto().getDataInicio().getTime() <= getObjeto().getDataFim().getTime()){
            	adiciona();             
            	cargaInicial();
            }else{
            }
        }else{
        	cargaInicial();
        }
	}

	public void quandoNovo(SelectEvent selectEvent) {
		ScheduleEvent event = new DefaultScheduleEvent("",(Date) selectEvent.getObject(), (Date) selectEvent.getObject());
		objeto = getGenericRepositorio().novaInstancia();
		getObjeto().setDataInicio(event.getStartDate());
		getObjeto().setDataFim(event.getEndDate());
	}
	 
	public void quandoSelecionado(SelectEvent selectEvent) {
		ScheduleEvent event = (ScheduleEvent) selectEvent.getObject();
		for (Evento agda : eventos) {
			if (agda.getId() == (Long) event.getData()) {
				setObjeto(agda);
				break;
			}
		}
	}  
	 
	public void quandoMovido(ScheduleEntryMoveEvent event) {
		for (Evento agda : eventos) {
			if (agda.getId() == (Long) event.getScheduleEvent().getData()) {
				setObjeto(agda);
				break;
			}
		}
	}
	 
	public void quandoRedimensionado(ScheduleEntryResizeEvent event) {
		for (Evento agda : eventos) {
			if (agda.getId() == (Long) event.getScheduleEvent().getData()) {
				setObjeto(agda);
				break;
			}
		}
	}
	
	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}
}
