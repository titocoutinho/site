package desenv.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import desenv.modelo.persistencia.auxiliar.ParametroRepo;
import desenv.util.mail.Mail;
import desenv.util.mail.MailSender;

public class Email implements Runnable {

	private static final String SMTP =  new ParametroRepo().getValorPeloNome("SMTP"); //"smtp.gmail.com";//"173.194.76.108";
	private static final String SMTP_PORTA = new ParametroRepo().getValorPeloNome("SMTP_PORTA"); // "587";
	private static final String SMTP_AUTH = "true";
	private static final String SMTP_TLS = "true";
	private static final String CHARSET = "ISO-8859-1";
	private static final String TYPETEXT = Mail.TYPE_TEXT_HTML;
	private static final String SMTP_USUARIO = new ParametroRepo().getValorPeloNome("SMTP_USUARIO"); //"victorcoutinho1985@gmail.com";
	private static final String SMTP_SENHA = new ParametroRepo().getValorPeloNome("SMTP_SENHA"); //"coutinho.victor";
	
	
	
	
	private Map<String, String> destinatarios;
	private String titulo;
	private String corpo;
	private List<String> arquivos;
	
	
	public Email() {
		
	}

	public static void enviar(Map<String, String> destinatarios, String titulo, String corpo, List<String> arquivos) throws CommonException {

			Mail email = new Mail();
			email.setTypeTextMail(TYPETEXT);
			email.setSmtpHostMail(SMTP);
			email.setSmtpPortMail(SMTP_PORTA);
			email.setSmtpAuth(SMTP_AUTH);
			email.setSmtpStarttls(SMTP_TLS);
			email.setPassMail(SMTP_SENHA);
			email.setUserMail(SMTP_USUARIO);
			
			email.setCharsetMail(CHARSET);
			email.setToMailsUsers(destinatarios);
			email.setSubjectMail(titulo);
			email.setBodyMail(corpo);
			
			
			email.setFileMails(arquivos);
			try{
				new MailSender().senderMail(email);
			}catch(UnsupportedEncodingException e){
				e.printStackTrace();
			}catch (MessagingException e) {
				e.printStackTrace();
			}
	}
	
	public void run() {  
		try {
			enviar(destinatarios, titulo, corpo, arquivos);
		} catch (CommonException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			Map<String, String> destinatario = new HashMap<String, String>();
			destinatario.put("victorcoutinho1985@gmail.com", "email 1");
			destinatario.put("desenv.victor@gmail.com", "email 2");
			destinatario.put("coutinho.victor@hotmail.com", "email 3");
			
			Email.enviar(destinatario, "Teste", "VAMOS VER SE FUNCIONA", new ArrayList<String>());
		} catch (CommonException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the destinatarios
	 */
	public Map<String, String> getDestinatarios() {
		return destinatarios;
	}

	/**
	 * @param destinatarios the destinatarios to set
	 */
	public void setDestinatarios(Map<String, String> destinatarios) {
		this.destinatarios = destinatarios;
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the corpo
	 */
	public String getCorpo() {
		return corpo;
	}

	/**
	 * @param corpo the corpo to set
	 */
	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}

	/**
	 * @return the arquivos
	 */
	public List<String> getArquivos() {
		return arquivos;
	}

	/**
	 * @param arquivos the arquivos to set
	 */
	public void setArquivos(List<String> arquivos) {
		this.arquivos = arquivos;
	}
	
	
	
}
