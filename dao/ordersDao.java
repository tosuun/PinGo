package Javaödev;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class OrderDAO {

    public boolean addOrder(Order order) {
        String sql = "INSERT INTO orders (product_id, customer_id, order_date, total_amount, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DbHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, order.getProductId());
            stmt.setInt(2, order.getCustomerId());
            stmt.setTimestamp(3, Timestamp.valueOf(order.getOrderDate())); // ✅ Doğru Timestamp kullanımı
            stmt.setDouble(4, order.getTotalAmount());
            stmt.setString(5, order.getStatus());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("SQL Hatası: Order eklenemedi!");
            e.printStackTrace();
            return false;
        }
    }

    public Order findOrderById(int orderId) {
        String sql = "SELECT * FROM orders WHERE order_id = ?";

        try (Connection conn = DbHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, orderId);
            var rs = stmt.executeQuery();

            if (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setProductId(rs.getInt("product_id"));
                order.setCustomerId(rs.getInt("customer_id"));
                order.setOrderDate(rs.getTimestamp("order_date").toLocalDateTime());
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setStatus(rs.getString("status"));

                return order;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.err.println("SQL Hatası: Order bulunamadı!");
            e.printStackTrace();
            return null;
        }
    }
}
