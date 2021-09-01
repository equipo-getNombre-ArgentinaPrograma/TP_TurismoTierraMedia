package tierraMedia;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import generadores.GeneradorListas;
import objetosDeEntrada.*;

public class tierraMediaTest {
	ArrayList<Atraccion> atracciones;
	ArrayList<Usuario> usuarios;
	ArrayList<Promocion> promos;

	// Genera las listas a partir de los archivos de entrada
	@Before
	public void inicializarArrayDeAtracciones() {
		atracciones = GeneradorListas.deAtracciones();
		usuarios = GeneradorListas.deUsuarios();
		promos = GeneradorListas.dePromos();
	}

	@Test
	public void buscoValoresEnElArrayDeAtracciones() {
		assertEquals("Mordor", atracciones.get(3).getNombre());
		assertEquals(30, atracciones.get(5).getCuposXdia());
		assertEquals("Paisaje", atracciones.get(1).getTipoDeAtraccion());
		assertEquals(10, atracciones.get(0).getCostoXvisita(), 0.1);
	}

	@Test
	public void buscoValoresEnElArrayDeUsuarios() {
		assertEquals("Sam", usuarios.get(2).getNombre());
		assertEquals(100, usuarios.get(1).getPresupuesto(), 0.1);
		assertEquals("Paisaje", usuarios.get(3).getTipoPreferido());
		assertEquals(8, usuarios.get(0).getTiempoDisponible(), 0.1);
	}

	@Test
	public void buscoValoresEnElArrayDePromos() {
		assertEquals("Paisaje", promos.get(1).getTipoDeAtraccion());
		assertEquals("La Comarca", promos.get(6).getAtraccion1().getNombre());
		assertEquals("La Comarca", promos.get(0).getAtraccion2().getNombre());
		assertEquals(10, promos.get(8).getPrecio(), 0.1);
	}
}
