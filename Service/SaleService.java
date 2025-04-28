package service;

import java.util.ArrayList;
import java.util.List;

import model.Sale;
import dao.SaleDao;

public class SaleService {
	private SaleDao SaleDao;
	public SaleService() {
		this.SaleDao = new SaleDao();
	}
	public List<Sale> getSalesForOrder(int orderId) {
		try {
			return SaleDao.findByOrderId(orderId);
		} catch (Exception e) {
			System.err.println("Order ID " + orderId + " için satışlar getirilirken hata: " + e.getMessage());
			e.printStackTrace(); 
            return new ArrayList<>();
		}
	}
	public Sale getSalesDetails(int idSales) {
		try {
			return SaleDao.findById(idSales);
		} catch (Exception e) {
			System.err.println("Sales ID " + idSales + " için detaylar getirilirken hata: " + e.getMessage());
            e.printStackTrace();
            return null;
		}
	}
	//Belirli bir müşterinin tüm geçmiş satış kalemlerini getirir.
	public List<Sale> getSalesForCustomer(int customerId) {
		try {
			return SaleDao.findByOrderId(customerId);
		} catch (Exception e) {
			 System.err.println("Customer ID " + customerId + " için satışlar getirilirken hata: " + e.getMessage());
             e.printStackTrace();
             return new ArrayList<>();
		}
	}

}
