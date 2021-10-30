package dao;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import inObject.Attraction;

public class AttractionDAOTest {

	@Test
	public void obtenerListaDeAtracciones() {
		System.out.println("Todas las atracciones:");
		ArrayList<Attraction> atracciones = new ArrayList<Attraction>();
		atracciones = AttractionDAO.getAll();
		for (Attraction atraccion : atracciones)
			System.out.println(atraccion);
		System.out.println("--------------------");
	}

	@Test
	public void obtenerListaDeAtraccionesEnUnaPromocion() {
		System.out.println("Atracciones en promocion 1:");
		ArrayList<Attraction> atracciones = new ArrayList<Attraction>();
		atracciones = AttractionDAO.findByPromotion(1);
		for (Attraction atraccion : atracciones)
			System.out.println(atraccion);
		System.out.println("--------------------");
	}

	@Test
	public void obtenerAtraccionesPorId() {
		System.out.println("Atraccion id = 1:");
		Attraction atraccion = AttractionDAO.findById(1);
		System.out.println(atraccion);
		System.out.println("--------------------");
	}

	@Test
	public void obtenerAtraccionesPorTipo() {
		System.out.println("Atracciones tipo aventura:");
		ArrayList<Attraction> atracciones = new ArrayList<Attraction>();
		atracciones = AttractionDAO.findByType("Aventura");
		for (Attraction atraccion : atracciones)
			System.out.println(atraccion);
		System.out.println("--------------------");
	}

	@Test
	public void usarCupo() {
		assertEquals(1, AttractionDAO.useQuota(1));
	}

	@Test
	public void checkearSiEstaLlena() {
		assertFalse(AttractionDAO.isFull(1));
	}
}
