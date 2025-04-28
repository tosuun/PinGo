package dao;

import model.Sale;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import util.DbHelper;

public class SaleDao {
	//For add new sales record
	public int save(Sale sale) {
		String sqlString = "INSERT INTO sale (idSeller, idCustomer, idProduct, orderId, quantity, priceAtSale, saleTimestamp) VALUES (?, ?, ?, ?, ?, ?, ?)";
		int generatedId = 0;
		try(Connection connection = DbHelper.getConnection();
			PreparedStatement ps = connection.prepareStatement(sqlString,Statement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, sale.getIdSeller());
            ps.setInt(2, sale.getIdCustomer());
            ps.setInt(3, sale.getIdProduct());
            ps.setInt(4, sale.getOrderId());
            ps.setInt(5, sale.getQuantity());
            ps.setBigDecimal(6, sale.getPriceAtSale());
            // LocalDateTime to SQL Timestamp
            ps.setTimestamp(7, Timestamp.valueOf(sale.getSaleTimestamp()));
            int affectedRows = ps.executeUpdate();
            //kayıt işleminin başarılı olup olmadığının kontrolü 
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedId = generatedKeys.getInt(1);
                        sale.setIdSales(generatedId); 
                    }
                }
            }

        } catch(SQLException e) {
        	System.err.println("Sales kaydı(ID bazlı) sırasında hata: "+e.getMessage());
        	e.printStackTrace();
		}
		//yeni oluşturulan kaydın id'si
		return generatedId;
	}
	//Belirli bir ID'ye sahip satış kalemini bulma.
	public Sale findById(int idSales) {
		String sql = "SELECT idSales, idSeller, idCustomer, idProduct, orderId, quantity, priceAtSale, saleTimestamp FROM sales WHERE idSales = ?";
		Sale sale = null;
		try(Connection connection = DbHelper.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, idSales);
			try(ResultSet rs = ps.executeQuery()) {
				if(rs.next()) {
					sale = new Sale();
					sale.setIdSales(rs.getInt("idSales"));
                    sale.setIdSeller(rs.getInt("idSeller"));
                    sale.setIdCustomer(rs.getInt("idCustomer"));
                    sale.setIdProduct(rs.getInt("idProduct"));
                    sale.setOrderId(rs.getInt("orderId"));
                    sale.setQuantity(rs.getInt("quantity"));
                    sale.setPriceAtSale(rs.getBigDecimal("priceAtSale"));
                    sale.setSaleTimestamp(rs.getTimestamp("saleTimestamp").toLocalDateTime());
				}
			}
		}catch(SQLException e) {
			System.err.println("Sales ID ile arama sırasında hata :" +e.getMessage());
			e.printStackTrace();
		}
		return sale;
	}
	public List<Sale> findByOrderId(int orderId) {
        List<Sale> salesList = new ArrayList<>();
       
        String sql = "SELECT idSales, idSeller, idCustomer, idProduct, orderId, quantity, priceAtSale, saleTimestamp FROM sales WHERE orderId = ?";

        try (Connection connection = DbHelper.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, orderId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Sale sale = new Sale();
                    sale.setIdSales(rs.getInt("idSales"));
                    sale.setIdSeller(rs.getInt("idSeller"));
                    sale.setIdCustomer(rs.getInt("idCustomer"));
                    sale.setIdProduct(rs.getInt("idProduct"));
                    sale.setOrderId(rs.getInt("orderId"));
                    sale.setQuantity(rs.getInt("quantity"));
                    sale.setPriceAtSale(rs.getBigDecimal("priceAtSale"));
                    sale.setSaleTimestamp(rs.getTimestamp("saleTimestamp").toLocalDateTime());
                    salesList.add(sale);
                }
            }
        } catch (SQLException e) {
            System.err.println("Order ID ile satış arama sırasında hata: " + e.getMessage());
            e.printStackTrace(); 
        }
        return salesList;
    }
 }
