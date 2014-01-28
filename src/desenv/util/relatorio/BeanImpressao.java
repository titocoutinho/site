package desenv.util.relatorio;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.util.JRLoader;
import desenv.util.generico.enums.Impressao;
import desenv.util.web.Contexto;


public class BeanImpressao implements Serializable{

	private static final long serialVersionUID = 1L;

		Logger log;
		
		private Impressao tipoEmpressao = Impressao.PDF;
		@SuppressWarnings("rawtypes")
		private Map parametros = new HashMap();
		private String nomeaArquivo;
		private String extensaoArquivo = "JASPER";
		@SuppressWarnings("rawtypes")
		private List listaObjetos;
		
		public Impressao getTipoEmpressao() {
			return tipoEmpressao;
		}
		
		public void setTipoEmpressao(Impressao tipoEmpressao) {
			this.tipoEmpressao = tipoEmpressao;
		}
		
		@SuppressWarnings("rawtypes")
		public Map getParametros() {
			return parametros;
		}
		
		public void setParametros(@SuppressWarnings("rawtypes") Map parametros) {
			this.parametros = parametros;
		}
		
		public String getNomeaArquivo() {
			return nomeaArquivo;
		}
		public void setNomeaArquivo(String nomeaArquivo) {
			this.nomeaArquivo = nomeaArquivo;
		}
		
		public void setExtensaoArquivo(String extensaoArquivo) {
			this.extensaoArquivo = extensaoArquivo;
		}
		
		@SuppressWarnings("rawtypes")
		public List getListaObjetos() {
			return listaObjetos;
		}
		public void setListaObjetos(@SuppressWarnings("rawtypes") List listaObjetos) {
			this.listaObjetos = listaObjetos;
		}
		
	
		public void execute(){
			try{
				 HttpServletResponse response = (HttpServletResponse) Contexto.getExternalContext().getResponse();
		            HttpServletRequest request = (HttpServletRequest) Contexto.getExternalContext().getRequest();

		            String path = request.getSession().getServletContext().getContextPath();
		            response.sendRedirect("javascript: void window.open('" + path + "/Relatorio" + "')");
			}catch(IOException ex){
				
				//log.getLogger(BeanImpressao.class.getName()).log(Level.SEVERE, null, ex);
				ex.printStackTrace();
			}
		}
		
		public String getExtensaoArquivo() {
			return extensaoArquivo;
		}
		
		
		@SuppressWarnings("unchecked")
		public InputStream getReportStream(){
			 
			 InputStream input = null;
			 
			 try {
			 ByteArrayOutputStream output = new ByteArrayOutputStream();
			 File file = new File(Contexto.getExternalContext().getRealPath("/WEB-INF/classes/report/jasper/"+ getNomeaArquivo() + ".jasper"));
			 JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file.getAbsoluteFile());
			 jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
			 JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(listaObjetos);
			 getParametros().put("DIRETORIO_IMAGEM", Contexto.getExternalContext().getRealPath("/resources/img//"));
			 JasperPrint print = JasperFillManager.fillReport(jasperReport, getParametros(), datasource);
			 JRExporter exporter = null;
			 
			 exporter = selecionarImpressao(exporter, print, output);
			 
			 
			 exporter.exportReport();
			 input = new ByteArrayInputStream(output.toByteArray());
			 } catch (JRException ex) {
			 Logger.getLogger(BeanImpressao.class.getName()).log(Level.SEVERE, null, ex);
			 }
			 
			 return input;
			 
			 }

		private JRExporter selecionarImpressao(JRExporter exporter, JasperPrint print, ByteArrayOutputStream output) {
			if(tipoEmpressao == Impressao.PDF)
			 exporter = new net.sf.jasperreports.engine.export.JRPdfExporter();
			 
			 if(tipoEmpressao == Impressao.HTML){
			 exporter = new net.sf.jasperreports.engine.export.JRHtmlExporter();
			 exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, false);
			 }
			 
			 if(tipoEmpressao == Impressao.XLS)
			 exporter = new net.sf.jasperreports.engine.export.JRXlsExporter();
			 
			 if(tipoEmpressao == Impressao.CVS)
			 exporter = new net.sf.jasperreports.engine.export.JRCsvExporter();
			 
			 if(tipoEmpressao == Impressao.TXT)
			 exporter = new net.sf.jasperreports.engine.export.JRTextExporter();
			 
			 if(tipoEmpressao == Impressao.RTF)
			 exporter = new net.sf.jasperreports.engine.export.JRRtfExporter();
			 
			 exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, output);
			 exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			return exporter;
		}
	
}
