package service;

//Sipariş oluşturma (createOrder), siparişleri getirme (getUserOrders, getAllOrders), sipariş detaylarını getirme (getOrderById),
//sipariş durumu güncelleme (updateOrderStatus), sipariş iptali (cancelOrder).

import java.time.LocalDateTime;
import java.util.List;

import model.Order;

import java.util.ArrayList;
import dao.OrderDao;

public class OrderService {
  private final OrderDao OrderDao = new OrderDao();

  // 1. Sipariş oluşturma
  public boolean createOrder(int productId, int customerId, double totalAmount) {
      Order order = new Order();
      order.setProductId(productId);
      order.setCustomerId(customerId);
      order.setOrderDate(LocalDateTime.now());
      order.setTotalAmount(totalAmount);
      order.setStatus("Pending");
      return OrderDao.addOrder(order);
  }

  // 2. Belirli bir kullanıcıya ait siparişleri getirme
  public List<Order> getUserOrders(int customerId) {
      // Bu fonksiyonu OrderDAO tarafında yazmalısın
      return OrderDao.getOrdersByCustomerId(customerId);
  }

  // 3. Tüm siparişleri getirme
  public List<Order> getAllOrders() {
      // Bu fonksiyonu OrderDAO tarafında yazmalısın
      return OrderDao.getAllOrders();
  }

  // 4. Sipariş ID ile detay getirme
  public Order getOrderById(int orderId) {
      return OrderDao.findOrderById(orderId);
  }

  // 5. Sipariş durumunu güncelleme
  public boolean updateOrderStatus(int orderId, String newStatus) {
      return OrderDao.updateOrderStatus(orderId, newStatus);
  }

  // 6. Siparişi iptal etme (durumu "Cancelled" yapar)
  public boolean cancelOrder(int orderId) {
      return OrderDao.updateOrderStatus(orderId, "Cancelled");
  }
}
