package dao;

import java.util.ArrayList;

import org.junit.Test;

import inObject.Promotion;

public class PromotionDAOTest {

	@Test
	public void obtenerListaDePromociones() {
		System.out.println("Todas las promos:");
		ArrayList<Promotion> promociones = new ArrayList<Promotion>();
		promociones = PromotionDAO.getAll();
		for(Promotion promocion : promociones)
			System.out.println(promocion);
		System.out.println("--------------------");
	}
	
	@Test
	public void obtenerPromocionPorId() {
		System.out.println("Promocion id = 1:");
		Promotion promocion = PromotionDAO.findById(1);
		System.out.println(promocion);
		System.out.println("--------------------");
	}
}
