import java.time.LocalDateTime;

public class Order {

    private int orderId; // Bu ID genellikle DB tarafından atanır
    private int productId;
    private int customerId;
    private LocalDateTime orderDate;
    private double totalAmount;
    private String status;

    // --- Getters ve Setters ---
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
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
               "orderId=" + orderId +
               ", productId=" + productId +
               ", customerId=" + customerId +
               ", orderDate=" + orderDate +
               ", totalAmount=" + totalAmount +
               ", status='" + status + '\'' +
               '}';
    }
