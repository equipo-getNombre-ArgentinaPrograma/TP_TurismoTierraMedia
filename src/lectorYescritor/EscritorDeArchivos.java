package lectorYescritor;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import objetosDeEntrada.Adquirible;
import objetosDeSalida.Itinerario;

public class EscritorDeArchivos {

	public static void setItinerarios(ArrayList<Itinerario> itinerarios) throws IOException {
		// Se recorre el array de itinerarios
		for (Itinerario it : itinerarios) {
			// Por cada itinerario se crea el archivo 'user.csv'
			PrintWriter salida = new PrintWriter(new FileWriter("./salida/" + it.getUser().getNombre() + ".csv"));
			salida.println("Usuario:;" + it.getUser().getNombre());
			// Se imprimen las sugerencias adquiridas
			for (Adquirible ad : it.getSugerenciasAdquiridas())
				salida.println(ad.toString());
			// Se imprimen los demas datos pedidos
			salida.println("Total a pagar:;" + it.getDineroGastado());
			salida.println("Horas Necesarias:;" + it.getTiempoNecesario());

			salida.close();
		}
	}
}
