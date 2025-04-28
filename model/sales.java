package model;

import java.math.BigDecimal;

import java.time.LocalDateTime;

public class Sales {
	private int idSales;
	private int idSeller;
	private int idCustomer;
	private int idProduct;
	private int orderId;
	private int quantity;
	private BigDecimal priceAtSale;
	private LocalDateTime saleTimestamp;
	
	public int getIdSales() {
		return idSales;
	}

	public void setIdSales(int idSales) {
		this.idSales = idSales;
	}

	public int getIdSeller() {
		return idSeller;
	}

	public void setIdSeller(int idSeller) {
		this.idSeller = idSeller;
	}

	public int getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPriceAtSale() {
		return priceAtSale;
	}

	public void setPriceAtSale(BigDecimal priceAtSale) {
		this.priceAtSale = priceAtSale;
	}

	public LocalDateTime getSaleTimestamp() {
		return saleTimestamp;
	}

	public void setSaleTimestamp(LocalDateTime saleTimestamp) {
		this.saleTimestamp = saleTimestamp;
	}
	public Sales() {
		
	}

	// Tüm alanları içeren constructor 
    public Sales(int idSeller, int idCustomer, int idProduct, int orderId, int quantity, BigDecimal priceAtSale, LocalDateTime saleTimestamp) {
        this.idSeller = idSeller;
        this.idCustomer = idCustomer;
        this.idProduct = idProduct;
        this.orderId = orderId;
        this.quantity = quantity;
        this.priceAtSale = priceAtSale;
        this.saleTimestamp = saleTimestamp;
    }
    //toString override for debugging
    @Override
    public String toString() {
        return "Sales{" +
               "idSales=" + idSales +
               ", idSeller=" + idSeller +
               ", idCustomer=" + idCustomer +
               ", idProduct=" + idProduct +
               ", orderId=" + orderId +
               ", quantity=" + quantity +
               ", priceAtSale=" + priceAtSale +
               ", saleTimestamp=" + saleTimestamp +
               '}';
    }
}
