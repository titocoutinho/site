package desenv.util;

import java.io.Serializable;
import java.text.Normalizer;

public class TrataTexto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static String removeAccents(String str) {
	    str = Normalizer.normalize(str, Normalizer.Form.NFD);
	    str = str.replaceAll("[^\\p{ASCII}]", "");
	    return str;

	}

	
}
