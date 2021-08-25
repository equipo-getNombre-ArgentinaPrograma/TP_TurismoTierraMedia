package tierraMedia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class LectorDeArchivos {
	public static ArrayList<String[]> leerArchivo(String archivo) throws IOException {
		ArrayList<String[]> salida = new ArrayList<String[]>();
		String campos[];
		String linea;
		FileReader fr = null;
		BufferedReader br = null;
		Scanner sc = null;

		try {
			fr = new FileReader("./" + archivo);
		} catch (FileNotFoundException fnf) {
			fnf.printStackTrace();
		}
		br = new BufferedReader(fr);
		sc = new Scanner(new File("./" + archivo));
		sc.useLocale(Locale.ENGLISH);
		int cantidad = sc.nextInt();
		sc.close();
		br.readLine();
		for (int i = 0; i < cantidad; i++) {
			linea = br.readLine();
			campos = linea.split(":");
			salida.add(campos);
		}

		return salida;
	}
}
