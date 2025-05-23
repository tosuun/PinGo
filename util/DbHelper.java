package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {
	// Veritabanı bağlantı bilgileri
	private static final String  DB_USER = "root";
	private static final String DB_PASSWORD = "12345";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/e_ticaret";
	 /**
     * Veritabanına yeni bir bağlantı döndürür.
     * @throws SQLException Bağlantı hatası olursa
     */
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
	}
	public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Hata loglama
            }
        }
	}
}
/*
public class DbHelper {
    // Veritabanı bağlantı bilgileri
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "12345";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/oyun_e_ticaret";
    ****Yasin----"Bende 3306 ve datanın ismi e-ticaret şifre ve user aynı halen"***
*/
