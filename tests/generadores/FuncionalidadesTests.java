package generadores;

import objetosDeEntrada.*;
import objetosDeSalida.Itinerario;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import lectorYescritor.EscritorDeArchivos;
import lectorYescritor.LectorDeArchivos;

public class FuncionalidadesTests {
	ArrayList<Usuario> usuarios = GenerarLista.deUsuarios();
	ArrayList<Atraccion> atracciones = GenerarLista.deAtracciones();
	ArrayList<Promocion> promos = GenerarLista.dePromos(atracciones);

	@Test
	public void testDeUsuarios() {
		assertEquals(usuarios.get(0).getNombre(), "Eowyn");
		assertEquals(usuarios.get(1).getPresupuesto(), 100, 0.1);
		assertEquals(usuarios.get(2).getTiempoDisponible(), 8, 0.1);
		assertEquals(usuarios.get(3).getTipoPreferido(), "Paisaje");
	}

	@Test
	public void testDeAtracciones() {
		assertEquals(atracciones.get(0).getNombre(), "Moria");
		assertEquals(atracciones.get(1).getPrecio(), 5, 0.1);
		assertEquals(atracciones.get(2).getTiempoDeRealizacion(), 6.5, 0.1);
		assertEquals(atracciones.get(3).getCuposXdia(), 4);
		assertEquals(atracciones.get(4).getTipoDeAtraccion(), "Paisaje");
	}

	@Test
	public void testDePromos() {
		assertEquals(promos.get(0).getAtraccion1().getNombre(), "Minas Tirith");
		assertEquals(promos.get(1).getAtraccion2().getNombre(), "Bosque Negro");
		assertEquals(promos.get(2).getTipoDeAtraccion(), "Aventura");
		assertEquals(promos.get(3).getTiempoDeRealizacion(), 7.5, 0.1);
	}

	@Test
	public void adquirirAtraccionConUsuario() {
		Usuario user = usuarios.get(0); // Usuario 'Ewowyn'
		assertEquals(user.getPresupuesto(), 10, 0.1); // Tiene 10 monedas

		assertTrue(user.adquirir(atracciones.get(0))); // Adquiere 'Moria' por 10 monedas y 2 horas

		assertEquals(user.getPresupuesto(), 0, 0.1); // Le quedan 0 monedas
		assertEquals(user.getTiempoDisponible(), 6, 0.1); // Le quedan 6 horas

		assertFalse(user.adquirir(atracciones.get(1))); // No puede adquirir 'Minas Tirith'

		assertEquals(user.getPresupuesto(), 0, 0.1);// Otra vez le quedan 0 monedas
	}

	@Test
	public void noPuedoAdquirirAtraccionQueYaFueAdquiridaEnPromocion() { // Muy largo el nombre no?
		Usuario user = usuarios.get(2); // Usuario 'Sam'
		assertTrue(user.adquirir(promos.get(1))); // Adquiere promo 'Moria' y 'Bosque Negro' por 10 monedas y 6 horas

		assertEquals(user.getPresupuesto(), 26, 0.1); // Le quedan 26 monedas
		assertEquals(user.getTiempoDisponible(), 2, 0.1); // Le quedan 2 horas

		assertFalse(user.adquirir(atracciones.get(0))); // No puedo adquirir 'Moria' aunque posea el dinero y el tiempo

		assertEquals(user.getPresupuesto(), 26, 0.1); // Le siguen quedando 26 monedas

		assertTrue(user.adquirir(atracciones.get(4))); // Adquiere 'Abismo de Helm'

		assertEquals(user.getPresupuesto(), 21, 0.1); // Le quedan 21 monedas
		assertEquals(user.getTiempoDisponible(), 0, 0.1); // Le quedan 0 horas
	}

	@Test
	public void restarCuposAtraccion() {
		Usuario user = usuarios.get(1); // Usuario 'Gandalf'
		assertEquals(user.getPresupuesto(), 100, 0.1); // Tiene 100 monedas
		assertEquals(atracciones.get(3).getCuposXdia(), 4); // 'Mordor' tiene 4 cupos

		assertTrue(user.adquirir(atracciones.get(3))); // Adquiere 'Mordor'

		assertEquals(atracciones.get(3).getCuposXdia(), 3); // 'Mordor' tiene 3 cupos
	}

	@Test
	public void restarCuposPromocion() {
		Usuario user = usuarios.get(2); // Usuario 'Sam'
		assertEquals(user.getPresupuesto(), 36, 0.1); // Tiene 36 monedas
		assertTrue(promos.get(0).hayCupos()); // Hay cupos en la primera promo

		assertTrue(user.adquirir(promos.get(0))); // Adquiere la promocion

		assertEquals(atracciones.get(1).getCuposXdia(), 24); // 'Minas Tirith' tiene 24 cupos
		assertEquals(atracciones.get(4).getCuposXdia(), 14); // 'Abismo de Helm' tiene 14 cupos
	}

	@Test
	public void atraccionSeQuedaSinCupos() {
		assertEquals(atracciones.get(5).getCuposXdia(), 30); // 'Lothlorien' tiene 30 cupos

		for (int i = 0; i < 30; i++)
			assertTrue(atracciones.get(5).usarCupo()); // Uso 30 cupos

		assertEquals(atracciones.get(5).getCuposXdia(), 0); // 'Lothlorien' tiene 0 cupos

		assertFalse(atracciones.get(5).usarCupo()); // No puedo usar mas cupos

		assertEquals(atracciones.get(5).getCuposXdia(), 0); // 'Lothlorien' sigue con 0 cupos
	}

	@Test
	public void promocionSeQuedaSinCupos() {
		assertEquals(promos.get(2).getAtraccion1().getCuposXdia(), 12); // 'Bosque Negro' tiene 12 cupos
		assertEquals(promos.get(2).getAtraccion2().getCuposXdia(), 4); // 'Mordor' tiene 4 cupos

		for (int i = 0; i < 4; i++)
			assertTrue(promos.get(2).usarCupo()); // Uso 4 cupos

		assertEquals(promos.get(2).getAtraccion2().getCuposXdia(), 0); // 'Mordor' tiene 0 cupos

		assertFalse(promos.get(2).usarCupo()); // No puedo usar mas cupos

		assertEquals(promos.get(2).getAtraccion1().getCuposXdia(), 8); // 'Bosque Negro' tiene 8 cupos
		assertEquals(promos.get(2).getAtraccion2().getCuposXdia(), 0); // 'Mordor' tiene 0 cupos
	}

	@Test
	public void itinerarioDeUnUsuario() {
		Usuario user = usuarios.get(3); // Usuario 'Galadriel'
		assertEquals(user.getPresupuesto(), 120, 0.1); // tiene 120 monedas

		assertTrue(user.adquirir(atracciones.get(0))); // Adquiere 'Moria'
		assertTrue(user.adquirir(atracciones.get(5))); // Adquiere 'Lothlorien'

		assertEquals(user.getPresupuesto(), 75, 0.1); // Le quedan 55 monedas
		assertEquals(user.getTiempoDisponible(), 1.5, 0.1); // Le quedan 1.5 horas restantes

		Itinerario it = new Itinerario(user); // Inicializo el itinerario del usuario
		assertEquals(it.getDineroGastado(), 45, 0.1); // Debe gastar 45 monedas
		assertEquals(it.getTiempoNecesario(), 3, 0.1); // Necesita 3 horas

		// Accedo a las sugerencias desde el itinerario
		assertEquals(it.getSugerenciasAdquiridas().get(0).toString(),
				"Atraccion Aventura;Nombre: Moria;Duracion: 2.0 hora/s;Precio: 10.0 moneda/s");
		assertEquals(it.getSugerenciasAdquiridas().get(1).toString(),
				"Atraccion Degustacion;Nombre: Lothlorien;Duracion: 1.0 hora/s;Precio: 35.0 moneda/s");
	}

	@Test
	public void escriboUnArchivoYLeo() throws IOException {
		Usuario user = usuarios.get(0); // Usuario 'Eowyn'
		Itinerario it = new Itinerario(user); // Inicializo el itinerario del usuario
		ArrayList<Itinerario> its = new ArrayList<Itinerario>(); // Array necesario para enviarle al escritor
		its.add(it); // Agrego el itinerario al array

		assertTrue(user.adquirir(atracciones.get(2))); // Adquiere 'La Comarca'
		assertEquals(user.getPresupuesto(), 7, 0.1); // Le quedan 7 monedas

		EscritorDeArchivos.setItinerarios(its); // Escribo el archivo 'Eowyn.csv'
		ArrayList<String[]> Eowyn = LectorDeArchivos.get("salida", "Eowyn"); // Leo el archivo
		assertEquals(Eowyn.get(0)[0], "Usuario:"); // Leo los campos: cada linea es un array de Strings
													// y cada campo es un elemento del array
		assertEquals(Eowyn.get(0)[1], "Eowyn");
		assertEquals(Eowyn.get(1)[0], "Atraccion Degustacion");
		assertEquals(Eowyn.get(1)[1], "Nombre: La Comarca");
		assertEquals(Eowyn.get(2)[1], "3.0");
		assertEquals(Eowyn.get(3)[1], "6.5");
	}
}