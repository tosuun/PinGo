package dao;

import java.sql.*;
import model.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import util.DbHelper;

public class OrderDao {

    // Sipariş ekleme
    public int addOrder(Order order) {
        String sql = "INSERT INTO `Order` (orderId,productId, customerId, orderDate, totalAmount, status) VALUES (?,?, ?, ?, ?, ?)";
        int generatedId = 0;
        try (Connection conn = DbHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
        	stmt.setInt(1, order.getOrderId());
            stmt.setInt(2, order.getProductId());
            stmt.setInt(3, order.getCustomerId());
            stmt.setTimestamp(4, Timestamp.valueOf(order.getOrderDate()));
            stmt.setDouble(5, order.getTotalAmount());
            stmt.setString(6, order.getStatus());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedId = generatedKeys.getInt(1);
                        order.setOrderId(generatedId); 
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Hatası: Order eklenemedi!");
            e.printStackTrace();
           
        }
        return generatedId;
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

