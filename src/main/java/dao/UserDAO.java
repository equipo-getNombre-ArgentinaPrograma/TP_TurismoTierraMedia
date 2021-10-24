package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import db.ConnectionProvider;
import inObject.Acquirable;
import inObject.User;

public class UserDAO {
// Convierte la consulta a un objeto usuario
	public static User toUser(ResultSet resultSet) throws SQLException {
		Integer id = resultSet.getInt("id");
		String name = resultSet.getString("name");
		Double coins = resultSet.getDouble("available_coins");
		Double time = resultSet.getDouble("available_time");
		String type = resultSet.getString("preferred_type");
		return new User(id, name, coins, time, type);
	}

// Devuelve todos los usuarios en la base de datos
	public static ArrayList<User> getAll() {
		ArrayList<User> users = new ArrayList<User>();
		String query = "SELECT * FROM usuarios";
		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
				users.add(toUser(resultSet));
			return users;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

// Agrega las sugerencias adquiridas por el usuario a la db
	public static int acquire(Acquirable suggestion, int id) {
		String suggestionColumn;
		if (suggestion.isPromotion())
			suggestionColumn = "promotion_id";
		else
			suggestionColumn = "attraction_id";
		String query = "INSERT INTO compras_usuarios (" + suggestionColumn + ", user_id) VALUES (?, ?)";
		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, suggestion.getId());
			preparedStatement.setInt(2, id);
			return preparedStatement.executeUpdate();
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

//	Usa monedas del usuario
	public static int useCoins(double coins, int id) {
		String query = "UPDATE usuarios SET available_coins = available_coins - " + coins + " where id = ?";
		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			return preparedStatement.executeUpdate();
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

// Usa tiempo del usuario
	public static int useTime(double time, int id) {
		String query = "UPDATE usuarios SET available_time = available_time - " + time + " where id = ?";
		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			return preparedStatement.executeUpdate();
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

// Devuelve el tiempo disponible a su estado inicial
	public static boolean restoreTime() {
		ArrayList<User> users = getAll();
		String query = "UPDATE usuarios SET available_time = initial_time WHERE usuarios.id = ?";
		try {
			Connection connection = ConnectionProvider.getConnection();
			for (User user : users) {
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, user.getId());
				preparedStatement.executeUpdate();
			}
			return true;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

// Devuelve las monedas disponibles a su estado inicial
	public static boolean restoreCoins() {
		ArrayList<User> users = getAll();
		String query = "UPDATE usuarios SET available_coins = initial_coins WHERE usuarios.id = ?";
		try {
			Connection connection = ConnectionProvider.getConnection();
			for (User user : users) {
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, user.getId());
				preparedStatement.executeUpdate();
			}
			return true;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

// Borra el historial de compras
	public static int deleteCompras() {
		String query = "DELETE FROM compras_usuarios";
		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			return preparedStatement.executeUpdate();
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
}
