package dao;
import java.util.ArrayList;

import org.junit.Test;

import inObject.Attraction;

public class AttractionDAOTest {
	
	@Test
	public void obtenerListaDeAtracciones() {
		ArrayList<Attraction> atracciones = new ArrayList<Attraction>();
		atracciones = AttractionDAO.getAll();
		for(Attraction atraccion : atracciones)
			System.out.println(atraccion);
		System.out.println("--------------------");
	}
	@Test
	public void obtenerListaDeAtraccionesEnUnaPromocion() {
		ArrayList<Attraction> atracciones = new ArrayList<Attraction>();
		atracciones = AttractionDAO.findByPromotion(1);
		for(Attraction atraccion : atracciones)
			System.out.println(atraccion);
		System.out.println("--------------------");
	}
}
