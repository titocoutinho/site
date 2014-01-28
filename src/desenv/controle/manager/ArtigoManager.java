package desenv.controle.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import desenv.modelo.entidade.conteudo.Artigo;
import desenv.modelo.entidade.conteudo.Categoria;
import desenv.modelo.entidade.demografia.Municipio;
import desenv.modelo.persistencia.acesso.UsuarioRepo;
import desenv.modelo.persistencia.conteudo.ArtigoRepo;
import desenv.modelo.persistencia.conteudo.CategoriaRepo;
import desenv.modelo.persistencia.demografia.MunicipioRepo;
import desenv.util.generico.controle.ModeloVisao;
import desenv.util.web.Contexto;

@ManagedBean
@SessionScoped
public class ArtigoManager extends ModeloVisao<Artigo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Categoria> categorias = new ArrayList<Categoria>();
	private List<Municipio> municipios = new ArrayList<Municipio>();
	private UploadedFile file;

	@Override
	public Artigo getObjeto() {
		if (objeto == null)
			objeto = new Artigo();

		return objeto;
	}

	@Override
	public void setObjeto(Artigo objeto) {
		super.setObjeto(objeto);
	}

	public ArtigoManager() {
		setGenericRepositorio(new ArtigoRepo());
	}

	@PostConstruct
	public void cargaInicial() {
		objeto = new Artigo();
		modoInicialPadrao();
		categorias = new CategoriaRepo().getLista();
		municipios = new MunicipioRepo().getLista();
	}

	@Override
	public void cadastrar(ActionEvent action) {
		objeto.setUsuario(new UsuarioRepo().procura(getUsuarioSessao().getId()));

		getGenericRepositorio().adiciona(this.objeto);
		mensagemSucesso("Dados Inseridos com Sucesso!");
		// fotosProduto();
		objeto = getGenericRepositorio().novaInstancia();
		this.listaObjeto = null;

	}

	@Override
	public void alterar(ActionEvent action) {
		objeto.setUsuario(new UsuarioRepo().procura(getUsuarioSessao().getId()));
		getGenericRepositorio().atualiza(this.objeto);
		mensagemSucesso("Dados Atualizados com Sucesso!");
		fotosProduto();
		// super.alterar(action);
		objeto = getGenericRepositorio().novaInstancia();
		this.listaObjeto = null;
	}

	public void fotosProduto() {
		try {
			ServletContext sc = (ServletContext) Contexto.getExternalContext()
					.getContext();
			File folder = new File(sc.getRealPath("../artigo"));
			if (!folder.exists())
				folder.mkdirs();
			String nomeArquivo = getObjeto().getId() + ".jpg";
			String artigo = sc.getRealPath("../artigo") + File.separator
					+ nomeArquivo;
			criaArquivo(getObjeto().getPhoto(), artigo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fileUploadAction(FileUploadEvent event) throws IOException {
		System.out.println("chegou aqui");
		try {

			file = event.getFile();
			getObjeto().setPhoto(file.getContents());
			FacesMessage msg = new FacesMessage("O Arquivo ",
					file.getFileName() + " salvo no diretório.");
			FacesContext.getCurrentInstance().addMessage("msgUpdate", msg);
		} catch (Exception ex) {
			System.out.println("erro no upload");
			ex.printStackTrace();
		}

	}

	private void criaArquivo(byte[] bytes, String artigo) {
		FileOutputStream fos;

		try {
			fos = new FileOutputStream(artigo);
			fos.write(bytes);

			fos.flush();
			fos.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Municipio> getMunicipios() {
		return municipios;
	}

	public void setMunicipios(List<Municipio> municipios) {
		this.municipios = municipios;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
}
