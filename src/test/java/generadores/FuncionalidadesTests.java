package generadores;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import dao.AttractionDAO;
import dao.PromotionDAO;
import fileManagement.Reader;
import fileManagement.Writer;
import generator.ListGenerator;
import inObject.*;
import outObject.Itinerary;

public class FuncionalidadesTests {
	ArrayList<User> usuarios = ListGenerator.ofUsers();
	ArrayList<Attraction> atracciones = AttractionDAO.getAll();
	ArrayList<Promotion> promos = PromotionDAO.getAll();

	@Test
	public void testDeUsuarios() {
		assertEquals(usuarios.get(0).getName(), "Eowyn");
		assertEquals(usuarios.get(1).getAvailableMoney(), 100, 0.1);
		assertEquals(usuarios.get(2).getAvailableTime(), 8, 0.1);
		assertEquals(usuarios.get(3).getPreferredType(), "Paisaje");
	}

	@Test
	public void testDeAtracciones() {
		assertEquals(atracciones.get(0).getName(), "Moria");
		assertEquals(atracciones.get(1).getPrice(), 5, 0.1);
		assertEquals(atracciones.get(2).getCompletionTime(), 6.5, 0.1);
		assertEquals(atracciones.get(3).getCuposXdia(), 4);
		assertEquals(atracciones.get(4).getAttractionType(), "Paisaje");
	}

	@Test
	public void testDePromos() {
		assertEquals(promos.get(0).getAttraction1().getName(), "Minas Tirith");
		assertEquals(promos.get(1).getAttraction2().getName(), "Bosque Negro");
		assertEquals(promos.get(2).getAttractionType(), "Aventura");
		assertEquals(promos.get(3).getCompletionTime(), 7.5, 0.1);
	}

	@Test
	public void adquirirAtraccionConUsuario() {
		User user = usuarios.get(0); // Usuario 'Ewowyn'
		assertEquals(user.getAvailableMoney(), 10, 0.1); // Tiene 10 monedas

		assertTrue(user.acquire(atracciones.get(0))); // Adquiere 'Moria' por 10 monedas y 2 horas

		assertEquals(user.getAvailableMoney(), 0, 0.1); // Le quedan 0 monedas
		assertEquals(user.getAvailableTime(), 6, 0.1); // Le quedan 6 horas

		assertFalse(user.acquire(atracciones.get(1))); // No puede adquirir 'Minas Tirith'

		assertEquals(user.getAvailableMoney(), 0, 0.1);// Otra vez le quedan 0 monedas
	}

	@Test
	public void noPuedoAdquirirAtraccionQueYaFueAdquiridaEnPromocion() { // Muy largo el nombre no?
		User user = usuarios.get(2); // Usuario 'Sam'
		assertTrue(user.acquire(promos.get(1))); // Adquiere promo 'Moria' y 'Bosque Negro' por 10 monedas y 6 horas

		assertEquals(user.getAvailableMoney(), 26, 0.1); // Le quedan 26 monedas
		assertEquals(user.getAvailableTime(), 2, 0.1); // Le quedan 2 horas

		assertFalse(user.acquire(atracciones.get(0))); // No puedo adquirir 'Moria' aunque posea el dinero y el tiempo

		assertEquals(user.getAvailableMoney(), 26, 0.1); // Le siguen quedando 26 monedas

		assertTrue(user.acquire(atracciones.get(4))); // Adquiere 'Abismo de Helm'

		assertEquals(user.getAvailableMoney(), 21, 0.1); // Le quedan 21 monedas
		assertEquals(user.getAvailableTime(), 0, 0.1); // Le quedan 0 horas
	}

	@Test
	public void restarCuposAtraccion() {
		User user = usuarios.get(1); // Usuario 'Gandalf'
		assertEquals(user.getAvailableMoney(), 100, 0.1); // Tiene 100 monedas
		assertEquals(atracciones.get(3).getCuposXdia(), 4); // 'Mordor' tiene 4 cupos

		assertTrue(user.acquire(atracciones.get(3))); // Adquiere 'Mordor'

		assertEquals(atracciones.get(3).getCuposXdia(), 3); // 'Mordor' tiene 3 cupos
	}

	@Test
	public void restarCuposPromocion() {
		User user = usuarios.get(2); // Usuario 'Sam'
		assertEquals(user.getAvailableMoney(), 36, 0.1); // Tiene 36 monedas
		assertTrue(promos.get(0).isFull()); // Hay cupos en la primera promo

		assertTrue(user.acquire(promos.get(0))); // Adquiere la promocion

		assertEquals(atracciones.get(1).getCuposXdia(), 24); // 'Minas Tirith' tiene 24 cupos
		assertEquals(atracciones.get(4).getCuposXdia(), 14); // 'Abismo de Helm' tiene 14 cupos
	}

	@Test
	public void atraccionSeQuedaSinCupos() {
		assertEquals(atracciones.get(5).getCuposXdia(), 30); // 'Lothlorien' tiene 30 cupos

		for (int i = 0; i < 30; i++)
			assertTrue(atracciones.get(5).useQuota()); // Uso 30 cupos

		assertEquals(atracciones.get(5).getCuposXdia(), 0); // 'Lothlorien' tiene 0 cupos

		assertFalse(atracciones.get(5).useQuota()); // No puedo usar mas cupos

		assertEquals(atracciones.get(5).getCuposXdia(), 0); // 'Lothlorien' sigue con 0 cupos
	}

	@Test
	public void promocionSeQuedaSinCupos() {
		assertEquals(promos.get(2).getAttraction1().getCuposXdia(), 12); // 'Bosque Negro' tiene 12 cupos
		assertEquals(promos.get(2).getAttraction2().getCuposXdia(), 4); // 'Mordor' tiene 4 cupos

		for (int i = 0; i < 4; i++)
			assertTrue(promos.get(2).useQuota()); // Uso 4 cupos

		assertEquals(promos.get(2).getAttraction2().getCuposXdia(), 0); // 'Mordor' tiene 0 cupos

		assertFalse(promos.get(2).useQuota()); // No puedo usar mas cupos

		assertEquals(promos.get(2).getAttraction1().getCuposXdia(), 8); // 'Bosque Negro' tiene 8 cupos
		assertEquals(promos.get(2).getAttraction2().getCuposXdia(), 0); // 'Mordor' tiene 0 cupos
	}

	@Test
	public void itinerarioDeUnUsuario() {
		User user = usuarios.get(3); // Usuario 'Galadriel'
		assertEquals(user.getAvailableMoney(), 120, 0.1); // tiene 120 monedas

		assertTrue(user.acquire(atracciones.get(0))); // Adquiere 'Moria'
		assertTrue(user.acquire(atracciones.get(5))); // Adquiere 'Lothlorien'

		assertEquals(user.getAvailableMoney(), 75, 0.1); // Le quedan 55 monedas
		assertEquals(user.getAvailableTime(), 1.5, 0.1); // Le quedan 1.5 horas restantes

		Itinerary it = new Itinerary(user); // Inicializo el itinerario del usuario
		assertEquals(it.getSpentMoney(), 45, 0.1); // Debe gastar 45 monedas
		assertEquals(it.getSpentTime(), 3, 0.1); // Necesita 3 horas

		// Accedo a las sugerencias desde el itinerario
		assertEquals(it.getAcquiredSuggestions().get(0).toString(),
				"Atraccion Aventura;Nombre: Moria;Duracion: 2.0 hora/s;Precio: 10.0 moneda/s");
		assertEquals(it.getAcquiredSuggestions().get(1).toString(),
				"Atraccion Degustacion;Nombre: Lothlorien;Duracion: 1.0 hora/s;Precio: 35.0 moneda/s");
	}

	@Test
	public void escriboUnArchivoYLeo() throws IOException {
		User user = usuarios.get(0); // Usuario 'Eowyn'
		Itinerary it = new Itinerary(user); // Inicializo el itinerario del usuario
		ArrayList<Itinerary> its = new ArrayList<Itinerary>(); // Array necesario para enviarle al escritor
		its.add(it); // Agrego el itinerario al array

		assertTrue(user.acquire(atracciones.get(2))); // Adquiere 'La Comarca'
		assertEquals(user.getAvailableMoney(), 7, 0.1); // Le quedan 7 monedas

		Writer.setItinerary(its); // Escribo el archivo 'Eowyn.csv'
		ArrayList<String[]> Eowyn = Reader.get("salida", "Eowyn"); // Leo el archivo
		assertEquals(Eowyn.get(0)[0], "User"); // Leo los campos: cada linea es un array de Strings
													// y cada campo es un elemento del array
		assertEquals(Eowyn.get(0)[1], "Eowyn");
		assertEquals(Eowyn.get(1)[0], "Atraccion Degustacion");
		assertEquals(Eowyn.get(1)[1], "Nombre: La Comarca");
		assertEquals(Eowyn.get(2)[1], "3.0");
		assertEquals(Eowyn.get(3)[1], "6.5");
	}
}