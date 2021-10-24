package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.ConnectionProvider;
import inObject.Acquirable;
import inObject.Attraction;
import outObject.Itinerary;

public class ItineraryDAO {
// Escribe el itinerario en la db
	public static int print(Itinerary itinerary) {
		String acquiredAttractions = "";
		String acquiredPromotions = "";
		for (Acquirable acquirable : itinerary.getAcquiredSuggestions())
			if (acquirable.isPromotion()) {
				acquiredPromotions += acquirable.getId() + ". ";
				for (Attraction attraction : AttractionDAO.findByPromotion(acquirable.getId()))
					acquiredAttractions += attraction.getName() + ". ";
			} else
				acquiredAttractions += ((Attraction) acquirable).getName() + ". ";
		if (itineraryExists(itinerary))
			delete(itinerary.getUser().getId());
		String query = "INSERT INTO itinerarios (user_id, spent_money, spent_time, attractions_acquired, promotions_acquired) VALUES (?, ?, ?, '"
				+ acquiredAttractions + "', '" + acquiredPromotions + "')";
		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, itinerary.getUser().getId());
			preparedStatement.setDouble(2, itinerary.getSpentMoney());
			preparedStatement.setDouble(3, itinerary.getSpentTime());
			return preparedStatement.executeUpdate();
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

// Devuelve true si el itinerario ya existe	
	private static boolean itineraryExists(Itinerary itinerary) {
		String query = "SELECT count(*) as 'exists' FROM itinerarios WHERE user_id = ?";
		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, itinerary.getUser().getId());
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.getInt("exists") != 0)
				return true;
			return false;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

// Borra un itinerario deseado	
	private static int delete(int id) {
		String query = "DELETE FROM itinerarios WHERE user_id = ?";
		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			return preparedStatement.executeUpdate();
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
// Borra todos los itinerarios creados
	public static int deleteAll() {
		String query = "DELETE FROM itinerarios";
		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			return preparedStatement.executeUpdate();
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
}
