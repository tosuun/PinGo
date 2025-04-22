package advertDB;

import java.math.BigDecimal;
import java.util.Date;



	public class Advert {
	    private int idAdvert;
	    private String product;
	    private String seller;
	    private BigDecimal price;
	    private int stock;
	    private boolean isActive;
	    private Date createdAt;
	    private String imageUrl;
	    // parameterless constructor
	    public Advert () {
	    	
	    }
	    
	    // parameterized constructor
	    public Advert(int idAdvert, String product, String seller, BigDecimal price, int stock, boolean isActive, Date createdAt, String imageUrl) {
	        this.idAdvert = idAdvert;
	        this.product = product;
	        this.seller = seller;
	        this.price = price;
	        this.stock = stock;
	        this.isActive = isActive;
	        this.createdAt = createdAt;
	        this.imageUrl = imageUrl;
	    }

	    // Getter and Setter methods
	    public int getIdAdvert() {
	        return idAdvert;
	    }

	    public void setIdAdvert(int idAdvert) {
	        this.idAdvert = idAdvert;
	    }

	    public String getProduct() {
	        return product;
	    }

	    public void setProduct(String product) {
	        this.product = product;
	    }

	    public String getSeller() {
	        return seller;
	    }

	    public void setSeller(String seller) {
	        this.seller = seller;
	    }

	    public BigDecimal getPrice() {
	        return price;
	    }

	    public void setPrice(BigDecimal price) {
	        this.price = price;
	    }

	    public int getStock() {
	        return stock;
	    }

	    public void setStock(int stock) {
	        this.stock = stock;
	    }

	    public boolean isActive() {
	        return isActive;
	    }

	    public void setActive(boolean isActive) {
	        this.isActive = isActive;
	    }

	    public Date getCreatedAt() {
	        return createdAt;
	    }

	    public void setCreatedAt(Date createdAt) {
	        this.createdAt = createdAt;
	    }

	    public String getImageUrl() {
	        return imageUrl;
	    }

	    public void setImageUrl(String imageUrl) {
	        this.imageUrl = imageUrl;
	    }

	    // toString override for debugging
	    @Override
	    public String toString() {
	        return "Ilan{" +
	                "idAdvert=" + idAdvert +
	                ", product='" + product + '\'' +
	                ", seller='" + seller + '\'' +
	                ", price=" + price +
	                ", stock=" + stock +
	                ", isActive=" + isActive +
	                ", createdAt=" + createdAt +
	                ", imageUrl='" + imageUrl + '\'' +
	                '}';
	    }
	}



