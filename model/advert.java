import java.math.BigDecimal;
import java.util.Date;

public class advert {


	public class Ilan {
	    private int idAdvert;
	    private String product;
	    private String seller;
	    private BigDecimal price;
	    private int stok;
	    private boolean isActive;
	    private Date createdAt;
	    private String imageUrl;

	    // parameterized constructor
	    public Ilan(int idAdvert, String product, String seller, BigDecimal price, int stok, boolean isActive, Date createdAt, String imageUrl) {
	        this.idAdvert = idAdvert;
	        this.product = product;
	        this.seller = seller;
	        this.price = price;
	        this.stok = stok;
	        this.isActive = isActive;
	        this.createdAt = createdAt;
	        this.imageUrl = imageUrl;
	    }

	    // Getter and Setter methods
	    public int getId() {
	        return idAdvert;
	    }

	    public void setId(int idAdvert) {
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

	    public int getStok() {
	        return stok;
	    }

	    public void setStok(int stok) {
	        this.stok = stok;
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
	                ", stok=" + stok +
	                ", isActive=" + isActive +
	                ", createdAt=" + createdAt +
	                ", imageUrl='" + imageUrl + '\'' +
	                '}';
	    }
	}

}
