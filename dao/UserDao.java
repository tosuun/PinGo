package dao;

import java.sql.*;

import model.User;
import util.DbHelper;

public class UserDao {

  // Kullanıcı ekleme işlemi
  public void addUser(User user) {
      String sql = "INSERT INTO user (name, email, password, role, telNo, SatisSayisi, tc, ciro) "
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
  public User getUserById(int id) {
      String sql = "SELECT * FROM user WHERE id = ?";
      User user = null;

      try (Connection conn = DbHelper.getConnection();
           PreparedStatement stmt = conn.prepareStatement(sql)) {

          stmt.setInt(1, id);
          ResultSet rs = stmt.executeQuery();

          if (rs.next()) {
              user = new User(
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
  public User getUserByEmail(String email) {
      String sql = "SELECT * FROM user WHERE email = ?";
      User user = null;

      try (Connection conn = DbHelper.getConnection();
           PreparedStatement stmt = conn.prepareStatement(sql)) {

          stmt.setString(1, email);
          ResultSet rs = stmt.executeQuery();

          if (rs.next()) {
              user = new User(
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
  public void updateUser(User user) {
      String sql = "UPDATE user SET name = ?, email = ?, password = ?, role = ?, tel_no = ?, "
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
      String sql = "DELETE FROM user WHERE id = ?";

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
//sonradan authservice için eklendi
public boolean updatePassword(int i, String newPassword) {
	// TODO Auto-generated method stub
	return false;
}

public User findById(String email) {
	// TODO Auto-generated method stub
	return null;
}

public User findByUsername(String username) {
	// TODO Auto-generated method stub
	return null;
}
}
