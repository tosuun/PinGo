package service;

import java.math.BigDecimal;

import dao.OrderDao;

public class PaymentService {
	private OrderDao OrderDao;
	public PaymentService() {
		this.OrderDao = OrderDao;
		// this.orderService = new OrderService();
        // this.digitalKeyService = new DigitalKeyService();
	}
	public boolean processPayment(int orderId,BigDecimal amount) {
		System.out.println("------ ÖDEME SİMÜLASYONU BAŞLADI ------");
        System.out.println("Sipariş ID: " + orderId + ", Tutar: " + amount + " için ödeme deneniyor...");	
        boolean paymentSuccess = false;
        //şimdilik
        paymentSuccess = true;
        System.out.println("Simülasyon Kuralı: Her zaman başarılı.");
        try {
			if (paymentSuccess) {
				System.out.println("Ödeme SİMÜLASYONU Başarılı! Sipariş ID: " + orderId);
				// Sipariş durumunu "PAID" (ÖDENDİ) veya "COMPLETED" (TAMAMLANDI) yap
                //boolean statusUpdated = orderDao.updateOrderStatus(orderId, "PAID"); // OrderDao'da bu metot olmalı!
			}
		} catch (Exception e) {
			 System.err.println("Sipariş durumu güncellenirken hata oluştu! Sipariş ID: " + orderId + " Hata: " + e.getMessage());
	           e.printStackTrace();
	           paymentSuccess = false;
		}

        System.out.println("------ ÖDEME SİMÜLASYONU BİTTİ ------");
        return paymentSuccess;
	
	}
	
}
