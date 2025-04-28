package model;
import java.time.LocalDateTime;

public class CartItem {
	private int idCartItem;
	private int customerId;
	private int productId;
	private int quantity;
	private LocalDateTime addedTimestamp;
	public int getIdCartItem() {
		return idCartItem;
	}
	public void setIdCartItem(int idCartItem) {
		this.idCartItem = idCartItem;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public LocalDateTime getAddedTimestamp() {
		return addedTimestamp;
	}
	public void setAddedTimestamp(LocalDateTime addedTimestamp) {
		this.addedTimestamp = addedTimestamp;
	}
	
}
