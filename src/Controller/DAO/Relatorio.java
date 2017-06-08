package Controller.DAO;

import java.io.InputStream;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class Relatorio {
	
	public void gerarRelatorio() throws JRException{
		InputStream path = Relatorio.class.getResourceAsStream("/TrabOS/reports/OS.jrxml"); 
		JasperReport report = JasperCompileManager.compileReport(path);
		JasperPrint print = JasperFillManager.fillReport(report, null);
		JasperViewer.viewReport(print, false); 
	}
	
}
