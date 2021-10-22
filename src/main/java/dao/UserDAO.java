package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.ConnectionProvider;
import inObject.Acquirable;
import inObject.Attraction;
import inObject.Promotion;
import inObject.User;

public class UserDAO {
// Convierte la consulta a un objeto usuario
	public static User toUser(ResultSet resultSet) throws SQLException {

		return null;
	}

// Agrega las sugerencias adquiridas por el usuario a la db
	public static int acquire(Acquirable suggestion, int id) {
		String suggestionColumn = null;
		if (suggestion.isPromotion())
			suggestionColumn = "promotion_id";
		else
			suggestionColumn = "attraction_id";
		try {
			Connection connection = ConnectionProvider.getConnection();
			String query = "INSERT INTO compras_usuarios (" + suggestionColumn + ", user_id) VALUES (?, ?)";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, suggestion.getId());
			preparedStatement.setInt(2, id);
			return preparedStatement.executeUpdate();
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
}
