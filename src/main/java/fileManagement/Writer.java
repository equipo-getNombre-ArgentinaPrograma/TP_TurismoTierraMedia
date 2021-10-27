package fileManagement;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import inObject.Acquirable;
import outObject.Itinerary;

public class Writer {

	public static void setItinerary(ArrayList<Itinerary> itineraries) throws IOException {
		// Se recorre el array de itinerarios
		for (Itinerary it : itineraries) {
			// Por cada itinerario se crea el archivo 'user.txt'
			PrintWriter salida = new PrintWriter(new FileWriter("./salida/" + it.getUser().getName() + ".txt"));
			salida.println("User;" + it.getUser().getName());
			// Se imprimen las sugerencias adquiridas
			for (Acquirable ad : it.getAcquiredSuggestions())
				salida.println(ad.toString());
			// Se imprimen los demas datos pedidos
			salida.println("Total a pagar;" + it.getSpentCoins());
			salida.println("Horas Necesarias;" + it.getSpentTime());

			salida.close();
		}
	}
}
