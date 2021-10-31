package dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import db.ConnectionProvider;
import inObject.User;
import outObject.Itinerary;

public class ItineraryDAOTest {

	@Before
	public void resetearTablas() {
		AttractionDAO.restoreQuota();
		ItineraryDAO.deleteAll();
		UserDAO.deleteCompras();
	}

	@Test
	public void agregarAtraccionesAlItinerario() {
		User user = new User(1, "Juan", 100d, 100d, "Aventura");
		assertTrue(user.acquire(AttractionDAO.findById(1)));
		Itinerary itinerary = new Itinerary(user);
		assertEquals(1, ItineraryDAO.print(user, itinerary.getSpentCoins(), itinerary.getSpentTime()));
	}

	@Test
	public void agregarPromocionesAlItinerario() {
		User user = new User(2, "Pedro", 100d, 100d, "Aventura");
		assertTrue(user.acquire(PromotionDAO.findById(1)));
		assertTrue(user.acquire(PromotionDAO.findById(2)));
		Itinerary itinerary = new Itinerary(user);
		assertEquals(1, ItineraryDAO.print(user, itinerary.getSpentCoins(), itinerary.getSpentTime()));
	}
}
