package desenv.util.generico.controle;



import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;

import desenv.util.generico.modelo.IModelo;
import desenv.util.generico.persistencia.IGenericRepository;

public class ModeloVisaoRelatorio <T extends IModelo> extends BeanModelo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected DataModel<T> listaModelo = new ArrayDataModel<T>();
	protected T objeto;
	protected IGenericRepository<T> genericRepositorio;
	@SuppressWarnings("unused")
	private Long id;
	@SuppressWarnings("unused")
	private String arquivoJasper;
	
	public ModeloVisaoRelatorio() {
	}
	
	public DataModel<T> getListaModelo() {
		return listaModelo;
	}
	public void setListaModelo(DataModel<T> listaModelo) {
		this.listaModelo = listaModelo;
	}
	
	public T getObjetoSelecionado(){
		return listaModelo.getRowData();
	}

}
