package dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.junit.Test;

import db.ConnectionProvider;
import inObject.User;
import outObject.Itinerary;

public class ItineraryDAOTest {

	@Test
	public void agregarAtraccionesAlItinerario() {
		AttractionDAO.restoreQuota();
		User user = new User(2, "Juan", 100d, 100d, "Aventura");
		assertTrue(user.acquire(AttractionDAO.findById(1)));
		assertTrue(user.acquire(PromotionDAO.findById(3)));
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

	@Test
	public void resetearCupos() {
		assertTrue(AttractionDAO.restoreQuota());
	}
}
