package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import db.ConnectionProvider;
import inObject.Acquirable;
import inObject.Attraction;

import outObject.Itinerary;

public class ItineraryDAO {
	public static int print(Itinerary itinerary) {
		String acquiredAttractions = "";
		String acquiredPromotions = "";
		for (Acquirable acquirable : itinerary.getAcquiredSuggestions())
			if (acquirable.isPromotion())
				acquiredPromotions += acquirable.getId() + ". ";
			else
				acquiredAttractions += ((Attraction) acquirable).getName() + ". ";
		try {
			Connection connection = ConnectionProvider.getConnection();
			String query = "INSERT INTO itinerarios (user_id, spent_money, spent_time, attractions_acquired, promotions_acquired) VALUES (?, ?, ?, '"
					+ acquiredAttractions + "', '" + acquiredPromotions + "')";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, itinerary.getUser().getId());
			preparedStatement.setDouble(2, itinerary.getSpentMoney());
			preparedStatement.setDouble(3, itinerary.getSpentTime());
			return preparedStatement.executeUpdate();
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
}
