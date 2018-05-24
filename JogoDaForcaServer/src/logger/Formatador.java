package logger;

public class Formatador {
	IDataTempo dt = new DataTempo();

	public String formatarStringCod(String in, int cod) {
		String info = dt.horaFormatada() + in + " - código: " + cod;
		return info;
	}
	
	public String formatarString(String in) {
		String info = dt.horaFormatada() + in;
		return info;
	}
}
