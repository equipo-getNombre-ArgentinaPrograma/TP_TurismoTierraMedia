package lecturaYescritura;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class LectorDeArchivos {
	String nombreArchivo;
	ArrayList<String[]> salida = new ArrayList<String[]>();
	String campos[];
	String linea;
	FileReader fr = null;
	BufferedReader br = null;
	Scanner sc = null;

	public LectorDeArchivos(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public int abrir() throws IOException {
		System.out.println("Leyendo '" + this.nombreArchivo + "' ...");
		System.out.println("**************************");
		try {
			fr = new FileReader("./" + this.nombreArchivo);
		} catch (FileNotFoundException fnf) {
			fnf.printStackTrace();
		}
		// inicializo el scanner leo la primera linea del archivo
		// para saber cuantos datos debo leer
		sc = new Scanner(new File("./" + this.nombreArchivo));
		sc.useLocale(Locale.ENGLISH);
		// inicializo el reader y lo posiciono en la segunda linea
		// del archivo (donde empiezan los datos)
		br = new BufferedReader(fr);
		br.readLine();

		// devuelvo la cantidad de datos a leer
		return sc.nextInt();
	}

	public String[] leerLinea() throws IOException {
		linea = br.readLine();
		return campos = linea.split(",");
	}
}
