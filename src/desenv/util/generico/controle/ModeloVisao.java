package desenv.util.generico.controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import desenv.modelo.entidade.acesso.Usuario;
import desenv.util.generico.enums.Boleano;
import desenv.util.generico.modelo.IModelo;
import desenv.util.generico.persistencia.GenericRepository;
import desenv.util.generico.persistencia.IGenericRepository;
import desenv.util.web.ContadorSessionListener;
import desenv.util.web.Contexto;

public class ModeloVisao<T extends IModelo> extends BeanModelo implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	protected List<T> listaObjeto;
	protected List<T> listaObjetoImpressao;
	protected T objeto;
	protected IGenericRepository<T> genericRepositorio;
	private Long id;
	private String arquivoJasper;
	private Integer qtdUsuarioConectados = -1;

	public ModeloVisao() {

	}

	public Integer getQtdUsuarioConectados() {
		qtdUsuarioConectados = ContadorSessionListener.getSessionCount();
		return qtdUsuarioConectados;
	}

	public void modoInicialPadrao() {

		this.listaObjeto = null;
		carregarListas();
	}

	public void adiciona() {

		Long identificador = null;
		identificador = objeto.getId();
		if (identificador == null) {
			getGenericRepositorio().adiciona(this.objeto);
			mensagemSucesso("Dados Inseridos com Sucesso!");
		} else {
			if (identificador.intValue() > 0) {
				getGenericRepositorio().atualiza(this.objeto);
				mensagemSucesso("Dados Atualizados com Sucesso!");
			}
			if (identificador.intValue() == 0) {
				getGenericRepositorio().adiciona(this.objeto);
				mensagemSucesso("Dados Inseridos com Sucesso!");
			}
		}
		objeto = getGenericRepositorio().novaInstancia();
		this.listaObjeto = null;
	}
	public void cadastrar(ActionEvent action){
		getGenericRepositorio().adiciona(this.objeto);
		mensagemSucesso("Dados Inseridos com Sucesso!");
		objeto = getGenericRepositorio().novaInstancia();
		this.listaObjeto = null;
	}
	public void alterar(ActionEvent action){
		getGenericRepositorio().atualiza(this.objeto);
		mensagemSucesso("Dados Atualizados com Sucesso!");
		listaObjeto = null;
		objeto = getGenericRepositorio().novaInstancia();
	}
	public void excluir(ActionEvent action){
		getGenericRepositorio().remove(objeto.getId());
		this.objeto = getGenericRepositorio().novaInstancia();
		listaObjeto = null;
	}
	
	public void novo() {
		objeto = getGenericRepositorio().novaInstancia();
	}

	public List<T> getListaObjeto() {

		if (this.listaObjeto == null) {
			this.listaObjeto = getGenericRepositorio().getLista();
		}
		return listaObjeto;
	}

	public IGenericRepository<T> getGenericRepositorio() {
		if (genericRepositorio == null) {
			genericRepositorio = new GenericRepository<T>(getManager());
		}
		return genericRepositorio;
	}

	public void setGenericRepositorio(IGenericRepository<T> genericRepositorio) {
		this.genericRepositorio = genericRepositorio;
	}

	public Map<String, String> getParametros() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
	}

	public T getObjeto() {
		return objeto;
	}

	public void setObjeto(T objeto) {
		this.objeto = objeto;
	}

	public void setListaObjeto(List<T> listaObjeto) {
		this.listaObjeto = listaObjeto;
	}

	public List<SelectItem> getBoleanos() {
		List<SelectItem> siMaritialStatus = new ArrayList<SelectItem>();
		for (Boleano ms : Boleano.values()) {
			siMaritialStatus.add(new SelectItem(ms.name(), ms.toString()));
		}
		return siMaritialStatus;
	}

	public void mensagemSucesso(String msg) {

		FacesMessage mensagem = new FacesMessage(msg);
		mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
	}

	public void mensagemFalha(String msg) {

		FacesMessage mensagem = new FacesMessage(msg);
		mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
	}

	public Usuario getUsuarioSessao() {

		try {
			return (Usuario) Contexto.getUsuario_Sessao();
		} catch (NullPointerException e) {
			return null;
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getArquivoJasper() {
		return arquivoJasper;
	}

	public void setArquivoJasper(String arquivoJasper) {
		this.arquivoJasper = arquivoJasper;
	}

}
