/**
 * @author RA00184036
 * @version 06/03/2018
 */

package logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataTempo implements IDataTempo{

	public String dataFormatada() {
		Date dt = new Date();

		SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy");

		return ft.format(dt);
	}
	public String horaFormatada() {
		Date dt = new Date();

		SimpleDateFormat ft = new SimpleDateFormat ("'['HH:mm:ss']: '");

		return ft.format(dt);
	}

}

