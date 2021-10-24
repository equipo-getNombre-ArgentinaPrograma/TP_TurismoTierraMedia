package dao;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import inObject.Attraction;
import inObject.User;

public class UserDAOTest {

	@Test
	public void obtenerListaUsuarios() {
		ArrayList<User> users = new ArrayList<User>();
		users = UserDAO.getAll();
		for (User user : users)
			System.out.println(user);
		System.out.println("--------------------");
	}

	@Test
	public void agregarCompraDeUsuario() {
		assertEquals(1, UserDAO.acquire(new Attraction(1, "Atraccion de prueba", 10d, 2d, 50, "Aventura"), 1));
	}

	@Test
	public void restarMonedas() {
		assertEquals(1, UserDAO.useCoins(1, 1));
	}

}
