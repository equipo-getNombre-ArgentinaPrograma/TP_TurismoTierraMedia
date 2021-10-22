package dao;

import static org.junit.Assert.*;

import org.junit.Test;

import inObject.Attraction;

public class UserDAOTest {

	@Test
	public void agregarCompraDeUsuario() {
		assertEquals(1, UserDAO.acquire(new Attraction(1,"Atraccion de prueba",10d,2d,50,"Aventura"), 5));
	}

}
