package Javaödev;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    // Sipariş ekleme
    public boolean addOrder(Order order) {
        String sql = "INSERT INTO orders (product_id, customer_id, order_date, total_amount, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DbHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, order.getProductId());
            stmt.setInt(2, order.getCustomerId());
            stmt.setTimestamp(3, Timestamp.valueOf(order.getOrderDate()));
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

    // Sipariş ID'ye göre bulma
    public Order findOrderById(int orderId) {
        String sql = "SELECT * FROM orders WHERE order_id = ?";

        try (Connection conn = DbHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setProductId(rs.getInt("product_id"));
                order.setCustomerId(rs.getInt("customer_id"));
                order.setOrderDate(rs.getTimestamp("order_date").toLocalDateTime());
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setStatus(rs.getString("status"));
                return order;
            }

        } catch (SQLException e) {
            System.err.println("SQL Hatası: Order bulunamadı!");
            e.printStackTrace();
        }

        return null;
    }

    // Belirli kullanıcıya ait tüm siparişleri getirme
    public List<Order> getOrdersByCustomerId(int customerId) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE customer_id = ?";

        try (Connection conn = DbHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setProductId(rs.getInt("product_id"));
                order.setCustomerId(rs.getInt("customer_id"));
                order.setOrderDate(rs.getTimestamp("order_date").toLocalDateTime());
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setStatus(rs.getString("status"));
                orders.add(order);
            }

        } catch (SQLException e) {
            System.err.println("SQL Hatası: Kullanıcının siparişleri alınamadı!");
            e.printStackTrace();
        }

        return orders;
    }

    // Tüm siparişleri getirme
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";

        try (Connection conn = DbHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setProductId(rs.getInt("product_id"));
                order.setCustomerId(rs.getInt("customer_id"));
                order.setOrderDate(rs.getTimestamp("order_date").toLocalDateTime());
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setStatus(rs.getString("status"));
                orders.add(order);
            }

        } catch (SQLException e) {
            System.err.println("SQL Hatası: Tüm siparişler alınamadı!");
            e.printStackTrace();
        }

        return orders;
    }

    // Sipariş durumu güncelleme
    public boolean updateOrderStatus(int orderId, String newStatus) {
        String sql = "UPDATE orders SET status = ? WHERE order_id = ?";

        try (Connection conn = DbHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newStatus);
            stmt.setInt(2, orderId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("SQL Hatası: Sipariş durumu güncellenemedi!");
            e.printStackTrace();
            return false;
        }
    }
}

