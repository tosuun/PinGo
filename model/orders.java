package model;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {

    private int idOrder; // Bu ID genellikle DB tarafından atanır
    private int idProduct;
    private int idCustomer;
    private LocalDateTime orderDate;
    private BigDecimal totalAmount;
    private String status;

    // --- Getters ve Setters ---
    public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public int getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

    // İsteğe bağlı: toString() metodu eklemek hata ayıklamada yardımcı olabilir
    @Override
    public String toString() {
        return "Order{" +
               "orderId=" + idOrder +
               ", productId=" + idProduct +
               ", customerId=" + idCustomer +
               ", orderDate=" + orderDate +
               ", totalAmount=" + totalAmount +
               ", status='" + status + '\'' +
               '}';
    }

	
}
