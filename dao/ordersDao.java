import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDAO {

    public boolean addOrder(Order order) {
        // SQL sorgusu orders tablosundaki sütunlarla eşleşmeli
        String sql = "INSERT INTO orders (product_id, customer_id, order_date, total_amount, status) VALUES (?, ?, ?, ?, ?)";

        // try-with-resources bağlantıyı ve statement'ı otomatik kapatır
        try (Connection conn = DbHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Parametreleri ayarla (index 1'den başlar)
            stmt.setInt(1, order.getProductId());
            stmt.setInt(2, order.getCustomerId());
            // LocalDateTime'ı SQL Timestamp'e çevir
            stmt.setTimestamp(3, Timestamp.valueOf(order.getOrderDate()));
            stmt.setDouble(4, order.getTotalAmount());
            stmt.setString(5, order.getStatus());

            // Sorguyu çalıştır ve etkilenen satır sayısını kontrol et
            int rowsAffected = stmt.executeUpdate();

            // Eğer en az bir satır eklendiyse true dön
            return rowsAffected > 0;

        } catch (SQLException e) {
            // Hata durumunda konsola yazdır (Gerçek uygulamada loglama yapın)
            System.err.println("SQL Hatası: Order eklenemedi!");
            e.printStackTrace(); // Detaylı hata mesajı için bu önemli
            return false; // Hata durumunda false dön
        }
    }
}
	
