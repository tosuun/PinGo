package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import util.DbHelper;
import model.*;

public class CartItemDao {
	public int save(CartItem cartItem) {
		String sql = "INSERT INTO cart_item (idCartItem,customerId,productId,quantity,addedTimestamp) VALUES (?,?,?,?,?)";
		int generatedId = 0;
		try (Connection connection = DbHelper.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			ps.setInt(1, cartItem.getIdCartItem());
			ps.setInt(2, cartItem.getCustomerId());
			ps.setInt(3, cartItem.getProductId());
			ps.setInt(4, cartItem.getQuantity());
			ps.setTimestamp(5, Timestamp.valueOf(cartItem.getAddedTimestamp()));
			int affectedRows = ps.executeUpdate();
			if(affectedRows > 0) {
				try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getInt(1);
                         cartItem.setIdCartItem(generatedId);
                    }
                }
			}
		} catch (SQLException e) {
			System.err.println("Sepet kaydı(ID bazlı) sırasında hata: "+e.getMessage());
        	e.printStackTrace();
		}
		return generatedId;
	}
	public boolean updateQuantity(int cartItemId, int newQuantity) {
        
        String sql = "UPDATE cartitem SET quantity = ? WHERE idCartItem = ?";
        boolean updated = false;

        try (Connection connection = DbHelper.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, newQuantity);
            ps.setInt(2, cartItemId);

            int affectedRows = ps.executeUpdate();
            updated = (affectedRows > 0); // Eğer en az 1 satır etkilendiyse true olur

        } catch (SQLException e) {
            System.err.println("CartItem miktar güncelleme sırasında hata (ID: " + cartItemId + "): " + e.getMessage());
            e.printStackTrace();
        }
        return updated;
    }
	public boolean delete(int cartItemId) {
        
        String sql = "DELETE FROM cartitem WHERE idCartItem = ?";
        boolean deleted = false;

        try (Connection connection = DbHelper.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, cartItemId);

            int affectedRows = ps.executeUpdate();
            deleted = (affectedRows > 0);

        } catch (SQLException e) {
            System.err.println("CartItem silme sırasında hata (ID: " + cartItemId + "): " + e.getMessage());
            e.printStackTrace();
        }
        return deleted;
    }
	public List<CartItem> findByCustomerId(int customerId) {
        List<CartItem> cartItems = new ArrayList<>();
        // SQL SORGUSU: Tablo ve sütun adlarını kontrol edin! 'added_timestamp' varsa ekleyin.
        String sql = "SELECT idCartItem,customerId,productId,quantity,addedTimestamp FROM cartitem WHERE customerId = ?";

        try (Connection connection = DbHelper.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, customerId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CartItem item = mapResultSetToCartItem(rs); // Yardımcı metoda gönderdik
                    cartItems.add(item);
                }
            }
        } catch (SQLException e) {
            System.err.println("Müşteri ID ile CartItem arama sırasında hata (ID: " + customerId + "): " + e.getMessage());
            e.printStackTrace();
        }
        return cartItems;
    }
	public boolean deleteByCustomerId(int customerId) {
        // SQL SORGUSU: Tablo adını kontrol edin!
        String sql = "DELETE FROM cartitem WHERE customerId = ?";
        boolean deleted = false;

        try (Connection connection = DbHelper.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, customerId);

            int affectedRows = ps.executeUpdate();
            deleted = (affectedRows > 0); // Eğer 0'dan fazla satır silindiyse true olur

        } catch (SQLException e) {
            System.err.println("Müşteri ID ile sepet silme sırasında hata (ID: " + customerId + "): " + e.getMessage());
            e.printStackTrace();
        }
        return deleted;
    }
	
	 private CartItem mapResultSetToCartItem(ResultSet rs) throws SQLException {
	       
	        CartItem item = new CartItem();

	        // ResultSet'ten (rs) sütun isimlerine göre verileri okuyup
	        // CartItem nesnesinin (item) ilgili alanlarına set ediyoruz.
	        item.setIdCartItem(rs.getInt("idCartItem")); // "id" sütunundaki tam sayıyı alıp item'ın id'sine ata.
	        item.setCustomerId(rs.getInt("customerId")); // "customer_id" sütunundaki tam sayıyı al...
	        item.setProductId(rs.getInt("productId"));   // "product_id" sütunundaki tam sayıyı al...
	        item.setQuantity(rs.getInt("quantity"));     // "quantity" sütunundaki tam sayıyı al...

	        // Önce Timestamp olarak alıyoruz.
	        Timestamp timestamp = rs.getTimestamp("added_timestamp");
	        // Eğer gelen değer null değilse (veritabanında boş olabilir)
	        if (timestamp != null) {
	            // Timestamp'i LocalDateTime'a çevirip item'ın zaman damgası alanına atıyoruz.
	            item.setAddedTimestamp(timestamp.toLocalDateTime());
	        }
	        // Doldurduğumuz CartItem nesnesini geri döndürüyoruz.
	        return item;
	    }
	public boolean addCartItem(CartItem cartItem) {  //main yazarken ekledim
		// TODO Auto-generated method stub
		return false;
	}
	 
}
