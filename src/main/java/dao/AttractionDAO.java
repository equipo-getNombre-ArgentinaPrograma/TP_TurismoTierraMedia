package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import inObject.Attraction;
import db.ConnectionProvider;

public class AttractionDAO {
	public static Attraction toAttraction(ResultSet resultSet) throws SQLException {
		Integer id = resultSet.getInt("id");
		String name = resultSet.getString("name");
		Double price = resultSet.getDouble("price");
		Double completionTime = resultSet.getDouble("completion_time");
		Integer quota = resultSet.getInt("quota");
		String type = resultSet.getString("type");

		return new Attraction(id, name, price, completionTime, quota, type);
	}

// Devuelve un arraylist con todas las atracciones en la base de datos
	public static ArrayList<Attraction> listOfAttractions() {
		try {
			ArrayList<Attraction> attractions = new ArrayList<Attraction>();
			Connection connection = ConnectionProvider.getConnection();
			String query = "SELECT * FROM atracciones";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
				attractions.add(toAttraction(resultSet));

			return attractions;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

// Resta un cupo de la atraccion en la base de datos
	public static int useQuota(int id) {
		try {
			Connection connection = ConnectionProvider.getConnection();
			String query = "UPDATE atracciones SET quota = quota - 1 WHERE atracciones.id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			return preparedStatement.executeUpdate();
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

// Devuelve los cupos a su valor inicial
	public static void restoreQuota() {
		ArrayList<Attraction> attractions = listOfAttractions();
		try {
			Connection connection = ConnectionProvider.getConnection();
			
			String query = "UPDATE atracciones SET quota = initial_quota WHERE atracciones.id = ?";
			
			for (Attraction attraction : attractions) {
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, attraction.getId());
				preparedStatement.executeUpdate();
			}
		} catch (
		Exception e) {
			throw new MissingDataException(e);
		}
	}
}
