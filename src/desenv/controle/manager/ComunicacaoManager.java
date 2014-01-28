package desenv.controle.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import desenv.modelo.entidade.conteudo.Comunicacao;
import desenv.modelo.persistencia.conteudo.ComunicacaoRepo;
import desenv.util.CommonException;
import desenv.util.Email;
import desenv.util.generico.controle.ModeloVisao;
import desenv.util.web.Contexto;

@ManagedBean
@ViewScoped
public class ComunicacaoManager extends ModeloVisao<Comunicacao> implements
		Serializable {

	private static final long serialVersionUID = 1L;
	private boolean estadoEnviar = true;

	@Override
	public Comunicacao getObjeto() {
		if (objeto == null)
			objeto = new Comunicacao();
		return objeto;
	}

	@Override
	public void adiciona() {
		Map<String, String> destinatario = new HashMap<String, String>();
		StringBuilder off = new StringBuilder();
		estadoEnviar = false;
		Email e = new Email();

		off.append("Prezado, <br/><br/><br/> Você recebeu um email de: ")
				.append(getObjeto().getNomeEmissor())
				.append("<br/><br/><br/> favor responder para o email: ")
				.append(objeto.getEmailEmissor())
				.append("<br/><br/><br/>segue abaixo o conteúdo: ")
				.append("<br/><br/><br/>").append(getObjeto().getCorpo())
				.append("<br/><br/><br/>")
				.append(" Atenciosamente, <br/> Site Amut");
		e.setCorpo(off.toString());

		destinatario.put(Contexto.getEmail(), Contexto.getDestinatario());

		e.setDestinatarios(destinatario);

		e.setTitulo(getObjeto().getTitulo());

		e.setArquivos(new ArrayList<String>());

		Thread thread = new Thread(e, "EmailThread");
		thread.start();

		super.adiciona();

	}

	@Override
	public void cadastrar(ActionEvent action) {
		estadoEnviar = false;
		getGenericRepositorio().adiciona(this.objeto);
		mensagemSucesso("Dados Inseridos com Sucesso!");

		try {
			Map<String, String> destinatario = new HashMap<String, String>();
			destinatario.put(Contexto.getEmail(), Contexto.getDestinatario());
			System.out.println(Contexto.getEmail() + ""
					+ Contexto.getDestinatario() + "" + getObjeto().getTitulo()
					+ "" + getObjeto().getCorpo());
			StringBuilder off = new StringBuilder();
			off.append("Prezado, <br/> Você recebeu um email de: ")
					.append(getObjeto().getEmailReceptor())
					.append("<br/> favor responder para o email:")
					.append(objeto.getNomeEmissor())
					.append("segue abaixo o conteúdo:").append("<br/>")
					.append(getObjeto().getCorpo()).append("<br/>")
					.append("Atenciosamente, <br/> Site Amut");

			Email.enviar(destinatario, getObjeto().getTitulo(), off.toString(),
					new ArrayList<String>());
		} catch (CommonException e) {
			e.printStackTrace();
		}
		
		objeto = getGenericRepositorio().novaInstancia();
		this.listaObjeto = null;

	}
	
	@Override
	public void alterar(ActionEvent action) {
		
		getGenericRepositorio().atualiza(this.objeto);
		mensagemSucesso("Dados Atualizados com Sucesso!");
		try {
			Map<String, String> destinatario = new HashMap<String, String>();
			destinatario.put(getObjeto().getEmailEmissor(), getObjeto().getNomeEmissor());
			
			StringBuilder off = new StringBuilder();
			off.append(getObjeto().getResposta());

			Email.enviar(destinatario, "RE:"+getObjeto().getTitulo(), off.toString(),
					new ArrayList<String>());
		} catch (CommonException e) {
			e.printStackTrace();
		}
		
		objeto = getGenericRepositorio().novaInstancia();
		this.listaObjeto = null;
	}

	public ComunicacaoManager() {
		setGenericRepositorio(new ComunicacaoRepo());
	}

	public void voltar() {
		estadoEnviar = true;
	}

	@PostConstruct
	public void cargaInicial() {
		objeto = new Comunicacao();
		modoInicialPadrao();

	}

	public boolean isEstadoEnviar() {
		return estadoEnviar;
	}

	public void setEstadoEnviar(boolean estadoEnviar) {
		this.estadoEnviar = estadoEnviar;
	}

}
