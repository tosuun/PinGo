package advertDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;


public class AdvertDAO {
	// This method saves a new Advert object into the database and returns the generated ID
	public int save(Advert advert) {
        String sql = "INSERT INTO advert (product, seller, price, stock, isActive, createdAt, imageUrl) VALUES (?, ?, ?, ?, ?, ?, ?)";
        int generatedId = 0;
        
        // Automatically closes connection and statement after use
        try (Connection connection = DbHelper.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, advert.getProduct());
            ps.setString(2, advert.getSeller());
            ps.setBigDecimal(3, advert.getPrice());
            ps.setInt(4, advert.getStock());
            ps.setBoolean(5, advert.isActive());
            ps.setTimestamp(6, new Timestamp(advert.getCreatedAt().getTime())); // java.util.Date → java.sql.Timestamp
            ps.setString(7, advert.getImageUrl());
            
            // Execute the insert operation
            int affectedRows = ps.executeUpdate();

            // If insert was successful, retrieve the generated primary key (id)
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedId = generatedKeys.getInt(1);
                        advert.setIdAdvert(generatedId);
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Error while adding advert record: " + e.getMessage());
            e.printStackTrace();
        }

        return generatedId;
    }
	
	// This method retrieves an Advert from the database by its ID
    public Advert findById(int id) {
        String sql = "SELECT * FROM advert WHERE idAdvert = ?";
        Advert advert = null;

        try (Connection connection = DbHelper.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

        	
            ps.setInt(1, id);
            
            // Execute the query and process the result set
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                	// Map the result set into an Advert object
                    advert = new Advert(
                        rs.getInt("idAdvert"),
                        rs.getString("product"),
                        rs.getString("seller"),
                        rs.getBigDecimal("price"),
                        rs.getInt("stock"),
                        rs.getBoolean("isActive"),
                        rs.getTimestamp("createdAt"),
                        rs.getString("imageUrl"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error while searching for advert by ID: " + e.getMessage());
            e.printStackTrace();
        }

        return advert;
    }
}
