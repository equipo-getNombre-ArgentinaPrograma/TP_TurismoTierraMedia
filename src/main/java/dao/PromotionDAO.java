package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.ConnectionProvider;
import inObject.*;

public class PromotionDAO {
// Convierte la consulta al objeto promocion que corresponda
	public static Promotion toPromotion(ResultSet resultSet) throws SQLException {
		Promotion prom = null;
		Integer id = resultSet.getInt("id");
		String promotionType = resultSet.getString("promotion_type");
		String attractionType = resultSet.getString("attraction_type");
		Double price = resultSet.getDouble("price");
		Double discount = resultSet.getDouble("discount");
		ArrayList<Attraction> includedAttractions = AttractionDAO.findByPromotion(id);
		if (promotionType.equals("Absoluta"))
			prom = new AbsoluteProm(id, includedAttractions, attractionType, price);
		else if (promotionType.equals("Porcentual"))
			prom = new PorcentualProm(id, includedAttractions, attractionType, discount);
		else if (promotionType.equals("AxB"))
			prom = new AxBProm(id, includedAttractions, attractionType);
		
		return prom;
	}

// Devuelve un arraylist con todas las promociones en la base de datos
	public static ArrayList<Promotion> getAll() {
		try {
			ArrayList<Promotion> promotions = new ArrayList<Promotion>();
			Connection connection = ConnectionProvider.getConnection();
			String query = "SELECT * FROM promociones";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
				promotions.add(toPromotion(resultSet));

			return promotions;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
}
