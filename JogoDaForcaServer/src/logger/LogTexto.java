/**
 * @author RA00184036
 * @version 06/03/2018
 */

package logger;

import java.io.*;

public class LogTexto implements ILog {
	private IDataTempo dt = new DataTempo();
	private String arquivo = dt.dataFormatada()+"-log.txt";
	
	public void escreverLog(String info) {
		Formatador format = new Formatador();
		File file = null;
		file = new File(arquivo);
		PrintWriter pw = null;
		String str = format.formatarString(info);
		try {
			pw = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
		} catch(IOException ex) {
			return;
		} catch(Exception ex) {
			return;
		}
		try {
			pw.print(str);
			pw.println(); 
		} catch(Exception ex) {
			return;
		} finally {
			try {
				pw.close();
			} catch(Exception ex) {			
			}		
		}

	}
	
	public void escreverLogCod(String info, int cod) {
		Formatador format = new Formatador();
		File file = null;
		file = new File(arquivo);
		PrintWriter pw = null;
		String str = format.formatarStringCod(info, cod);
		try {
			pw = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
		} catch(IOException ex) {
			return;
		} catch(Exception ex) {
			return;
		}
		try {
			pw.print(str);
			pw.println(); 
		} catch(Exception ex) {
			return;
		} finally {
			try {
				pw.close();
			} catch(Exception ex) {			
			}		
		}

	}

}
