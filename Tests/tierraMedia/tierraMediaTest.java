package tierraMedia;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import generadores.GenerarArray;
import objetosDeEntrada.*;

public class tierraMediaTest {
	Atraccion[] atracciones;
	@Before
	public void inicializarArrayDeAtracciones() throws IOException {
		atracciones = GenerarArray.deAtracciones();
	}
	
	@Test
	public void buscoValoresEnElArrayDeAtracciones(){
		assertEquals("Mordor", atracciones[3].getNombre());
		assertEquals(30, atracciones[5].getCuposXdia());
		assertEquals("Paisaje", atracciones[1].getTipoDeAtraccion());
		assertEquals(10, atracciones[0].getCostoXvisita(), 0.1);
	}

	@Test
	public void buscoValoresEnElArrayDeUsuarios() throws IOException {
		Usuario[] usuarios = GenerarArray.deUsuarios();
		assertEquals("Sam", usuarios[2].getNombre());
		assertEquals(100, usuarios[1].getPresupuesto(), 0.1);
		assertEquals("Paisaje", usuarios[3].getTipoPreferido());
		assertEquals(8, usuarios[0].getTiempoDisponible(), 0.1);
	}

	@Test
	public void buscoValoresEnElArrayDePromocionesPorcentuales() throws IOException {
		Promocion[] promosPor = GenerarArray.dePromocionesPorcentuales(atracciones);
		assertEquals("Aventura", promosPor[0].getTipoDeAtraccion());
		assertEquals("Moria", promosPor[3].getAtraccion1());
		assertEquals("Abismo de Helm", promosPor[1].getAtraccion2());
		assertEquals(22.4, promosPor[0].getPrecio(), 0.1);
	}
	
	@Test
	public void buscoValoresEnElArrayDePromocionesAbsolutas() throws IOException {
		Promocion[] promosAbs = GenerarArray.dePromocionesAbsolutas(atracciones);
		assertEquals("Degustacion", promosAbs[0].getTipoDeAtraccion());
		assertEquals("Lothlorien", promosAbs[0].getAtraccion1());
		assertEquals("La Comarca", promosAbs[0].getAtraccion2());
		assertEquals(7.5, promosAbs[0].getTiempoNecesario(), 0.1);
	}
	
	@Test
	public void buscoValoresEnElArrayDePromocionesAxB() throws IOException {
		Promocion[] promosAxB = GenerarArray.dePromocionesAxB(atracciones);
		assertEquals("Paisaje", promosAxB[0].getTipoDeAtraccion());
		assertEquals("Minas Tirith", promosAxB[0].getAtraccion1());
		assertEquals("Abismo de Helm", promosAxB[0].getAtraccion2());
		assertEquals(7.5, promosAxB[0].getTiempoNecesario(),0.1);
	}


}
