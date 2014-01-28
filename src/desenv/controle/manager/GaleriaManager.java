package desenv.controle.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;

import desenv.modelo.entidade.conteudo.Galeria;
import desenv.modelo.entidade.conteudo.Imagem;
import desenv.modelo.persistencia.conteudo.GaleriaRepo;
import desenv.modelo.persistencia.conteudo.ImagemRepo;
import desenv.util.ThumbnailFactory;
import desenv.util.generico.controle.ModeloVisao;
import desenv.util.web.Contexto;

@ManagedBean
@ViewScoped
public class GaleriaManager extends ModeloVisao<Galeria> implements
		Serializable {

	private static final long serialVersionUID = 1L;
	private ThumbnailFactory thumb = new ThumbnailFactory();

	public GaleriaManager() {
		setGenericRepositorio(new GaleriaRepo());
	}

	@PostConstruct
	public void cargaInicial() {
		objeto = new Galeria();
		modoInicialPadrao();
	}

	@Override
	public void adiciona() {
		System.out.println(getObjeto().toString());
		String diretorio = Contexto.getExternalContext().getRealPath("/")
				+ objeto.getDiretorio().replace("/", "\\");
		System.out.println(diretorio);
		try {
			thumb.run(diretorio);
			System.out.println("entrou aqui");
		} catch (NullPointerException e) {
			e.printStackTrace();

		} finally {
			super.adiciona();
		}
	}

	@Override
	public void cadastrar(ActionEvent action) {
		try {
			thumb.run(Contexto.getFotos());
			System.out.println("entrou aqui");
		} catch (NullPointerException e) {
			e.printStackTrace();

		} finally {
			super.cadastrar(action);
		}
		
	}
	
	public void fileUploadAction(FileUploadEvent event) throws IOException {
		try {
			Galeria g = new GaleriaRepo().procura(objeto.getId());
			FacesContext aFacesContext = FacesContext.getCurrentInstance();
			ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext();
			Imagem imagem = new Imagem();
			imagem.setGaleria(new GaleriaRepo().procura(objeto.getId()));
			imagem.setTitulo(event.getFile().getFileName());
			g.getImagens().add(imagem);
			new GaleriaRepo().atualiza(g);
			String realPath = context.getRealPath("/");
			String diretorioGaleria = realPath + Contexto.getFotos()
					+ getObjeto().getTitulo().replace(" ", "") + "/";
			objeto.setDiretorio(Contexto.getFotos()
					+ getObjeto().getTitulo().replace(" ", "") + "/");
			File file = new File(diretorioGaleria);
			if (!file.exists()) {
				file.mkdirs();
			}
			byte[] arquivo = event.getFile().getContents();
			String caminho = diretorioGaleria + event.getFile().getFileName();
			FileOutputStream fos = new FileOutputStream(caminho);
			fos.write(arquivo);
			fos.close();
		} catch (Exception ex) {
			ex.fillInStackTrace();
		}

	}
	
	 public void listaFotosProduto() {
		 List<Imagem> fotos = new ArrayList<Imagem>();
	        try {
	            ServletContext sContext = (ServletContext) FacesContext
	                    .getCurrentInstance().getExternalContext().getContext();
	            Galeria g = new GaleriaRepo().procura(objeto.getId());
	            fotos = g.getImagens();
	 
	            File folder = new File(sContext.getRealPath("/temp"));
	            if (!folder.exists())
	                folder.mkdirs();
	 
	            for (Imagem f : fotos) {
	                String nomeArquivo = f.getId() + ".jpg";
	                String arquivo = sContext.getRealPath("/temp") + File.separator
	                        + nomeArquivo;
	 
	                criaArquivo(null, arquivo);
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	 
	    }
	
	
	 private void criaArquivo(byte[] bytes, String arquivo) {
	        FileOutputStream fos;
	 
	        try {
	            fos = new FileOutputStream(arquivo);
	            fos.write(bytes);
	 
	            fos.flush();
	            fos.close();
	        } catch (FileNotFoundException ex) {
	            ex.printStackTrace();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	    }

	public void addDado(Imagem img) {
		new ImagemRepo().atualiza(img);
	}

	public void onEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edição efetuada",
				((Imagem) event.getObject()).getNome());
		addDado((Imagem) event.getObject());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edição Cancelada",
				((Imagem) event.getObject()).getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	@Override
	public Galeria getObjeto() {
		if (objeto == null)
			objeto = new Galeria();
		return objeto;
	}

	@Override
	public void setObjeto(Galeria objeto) {

		super.setObjeto(objeto);
	}
}
