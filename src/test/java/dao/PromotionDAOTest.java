package dao;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import inObject.Promotion;

public class PromotionDAOTest {

	@Test
	public void obtenerListaDePromociones() {
		ArrayList<Promotion> promociones = new ArrayList<Promotion>();
		promociones = PromotionDAO.getAll();
		for(Promotion promocion : promociones)
			System.out.println(promocion);
	}
}
