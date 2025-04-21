//package Javaproject;

import java.sql.*;

public class usersDao {

    // Kullanıcı ekleme işlemi
    public void addUser(users user) {
        String sql = "INSERT INTO users (name, email, password, role, tel_no, satis_sayisi, tc, ciro) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DbHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());
            stmt.setString(5, user.getTelNo());
            stmt.setInt(6, user.getSatisSayisi());
            stmt.setString(7, user.getTc());
            stmt.setDouble(8, user.getCiro());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Yeni kullanıcı eklendi.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Kullanıcı ID'sine göre kullanıcıyı getirir
    public users getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        users user = null;

        try (Connection conn = DbHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new users(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("tel_no"),
                        rs.getInt("satis_sayisi"),
                        rs.getString("tc"),
                        rs.getDouble("ciro")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // Kullanıcı e-posta adresine göre kullanıcıyı getirir
    public users getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        users user = null;

        try (Connection conn = DbHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new users(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("tel_no"),
                        rs.getInt("satis_sayisi"),
                        rs.getString("tc"),
                        rs.getDouble("ciro")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // Kullanıcı bilgilerini günceller
    public void updateUser(users user) {
        String sql = "UPDATE users SET name = ?, email = ?, password = ?, role = ?, tel_no = ?, "
                   + "satis_sayisi = ?, tc = ?, ciro = ? WHERE id = ?";

        try (Connection conn = DbHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());
            stmt.setString(5, user.getTelNo());
            stmt.setInt(6, user.getSatisSayisi());
            stmt.setString(7, user.getTc());
            stmt.setDouble(8, user.getCiro());
            stmt.setInt(9, user.getId());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Kullanıcı bilgileri güncellendi.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Kullanıcıyı ID'ye göre siler
    public void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection conn = DbHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Kullanıcı silindi.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

