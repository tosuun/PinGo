package service;
//yarın bakalıcak
import dao.CartItemDao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dao.AdvertDao;
import model.CartItem;
import model.Advert;


public class ShoppingCartService {
	private CartItemDao cartItemDao;
	private AdvertDao advertDAO;
	public ShoppingCartService() {
        this.cartItemDao = new CartItemDao(); 
        this.advertDAO = new AdvertDao();   
    }
	/**
     * Müşterinin sepetine ürün ekler.
     * Eğer ürün sepette zaten varsa, adedini artırır.
     * @param customerId Müşteri ID'si.
     * @param productId Eklenecek ürünün ID'si.
     * @param quantity Eklenecek adet.
     * @return İşlem başarılıysa true, değilse false.
     * @throws Exception Ürün bulunamazsa veya başka bir hata olursa.
     */
	public boolean addItem(int customerId, int idAdvert, int quantity) throws Exception {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Eklenecek adet 0'dan büyük olmalıdır.");
        }

        // İş Mantığı: Ürün var mı ve aktif mi kontrol et
        Advert advert = advertDAO.findById(idAdvert); // ProductDao'da findById olmalı
        if (advert == null  || !advert.isActive()  ) { 
            throw new Exception("Sepete eklenmeye çalışılan ürün bulunamadı veya aktif değil. ID: " + idAdvert);
        }

        // İş Mantığı: Ürün sepette zaten var mı?
        CartItem existingItem = (CartItem) cartItemDao.findByCustomerId(customerId);
        try {
            if (existingItem != null) {
                // Ürün zaten sepette var, adedini güncelle
                int newQuantity = existingItem.getQuantity() + quantity;
                // Stok kontrolü burada da yapılabilir: if(product.getStok() < newQuantity) {...}
                boolean updated = cartItemDao.updateQuantity(existingItem.getProductId(), newQuantity); // DAO'da bu metot olmalı
                System.out.println("Ürün ("+idAdvert+") adedi güncellendi: " + newQuantity);
                return updated;
            } else {
                // Ürün sepette yok, yeni kalem olarak ekle
                CartItem newItem = new CartItem();
                newItem.setCustomerId(customerId);
                newItem.setProductId(idAdvert);
                newItem.setQuantity(quantity);
                newItem.setAddedTimestamp(LocalDateTime.now()); // İsterseniz eklenme zamanını da ekleyin

                int generatedId = cartItemDao.save(newItem); // DAO'da bu metot olmalı
                System.out.println("Yeni ürün ("+idAdvert+") sepete eklendi.");
                return generatedId > 0;
            }
        } catch (Exception e) {
            System.err.println("Sepete ürün eklenirken hata: " + e.getMessage());
            e.printStackTrace();
            throw e; // Hatayı yukarı fırlat
        }
    }
	/**
     * Sepetten bir kalemi siler (Sepet Kalemi ID'si ile).
     * @param cartItemId Silinecek sepet kaleminin ID'si (`cart_items` tablosundaki ID).
     * @return İşlem başarılıysa true, değilse false.
     */
    public boolean removeItem(int idCartItem) {
        try {
             
            boolean deleted = cartItemDao.delete(idCartItem); // DAO'da bu metot olmalı
            if(deleted) {
                 System.out.println("Sepet kalemi ("+idCartItem+") silindi.");
            } else {
                 System.out.println("Sepet kalemi ("+idCartItem+") silinemedi veya bulunamadı.");
            }
            return deleted;
        } catch (Exception e) {
            System.err.println("Sepetten ürün silinirken hata: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Belirli bir müşterinin sepetindeki tüm kalemleri getirir.
     * @param customerId Müşteri ID'si.
     * @return Müşterinin sepetindeki CartItem nesnelerinin listesi.
     */
    public List<CartItem> getCartItems(int customerId) {
        try {
            // Bu metot sadece CartItem listesi döndürür (içinde product ID'ler var).
           
            return cartItemDao.findByCustomerId(customerId); // DAO'da bu metot olmalı
        } catch (Exception e) {
            System.err.println("Sepet içeriği getirilirken hata: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>(); // Hata durumunda boş liste
        }
    }
    /**
     * Belirli bir müşterinin sepetini tamamen temizler.
     * @param customerId Müşteri ID'si.
     * @return İşlem başarılıysa true, değilse false.
     */
    public boolean clearCart(int customerId) {
        try {
            boolean deleted = cartItemDao.deleteByCustomerId(customerId); // DAO'da bu metot olmalı
            if(deleted) {
                System.out.println("Müşteri ("+customerId+") sepeti temizlendi.");
            } else {
                 System.out.println("Müşteri ("+customerId+") sepeti temizlenemedi (belki zaten boştu).");
            }
            return deleted;
        } catch (Exception e) {
            System.err.println("Sepet temizlenirken hata: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Sepetteki bir ürünün adedini günceller.
     * @param cartItemId Güncellenecek sepet kaleminin ID'si.
     * @param newQuantity Yeni adet (0'dan büyük olmalı).
     * @return İşlem başarılıysa true, değilse false.
     */
    public boolean updateItemQuantity(int cartItemId, int newQuantity) {
        if (newQuantity <= 0) {
            // Adet 0 veya daha az ise ürünü tamamen silmek daha mantıklı olabilir
            System.out.println("Adet 0 veya daha az, ürün siliniyor (ID: "+cartItemId+")");
            return removeItem(cartItemId);
            // Veya hata fırlat: throw new IllegalArgumentException("Adet 0'dan büyük olmalıdır.");
        }
        // Stok kontrolü burada da yapılabilir.

        try {
            boolean updated = cartItemDao.updateQuantity(cartItemId, newQuantity); // DAO'da bu metot olmalı
             if(updated) {
                 System.out.println("Sepet kalemi ("+cartItemId+") adedi güncellendi: "+ newQuantity);
            } else {
                 System.out.println("Sepet kalemi ("+cartItemId+") adedi güncellenemedi veya bulunamadı.");
            }
            return updated;
        } catch (Exception e) {
            System.err.println("Sepet ürün adedi güncellenirken hata: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Müşterinin sepetinin toplam tutarını hesaplar.
     * @param customerId Müşteri ID'si.
     * @return Sepetin toplam tutarı (BigDecimal). Hata olursa 0 dönebilir.
     */
    public BigDecimal getCartTotal(int customerId) {
        BigDecimal total = BigDecimal.ZERO; // Başlangıç toplamı 0
        List<CartItem> items = getCartItems(customerId); // Sepetteki ürünleri al

        if (items.isEmpty()) {
            return total; // Sepet boşsa toplam 0
        }

        try {
            for (CartItem item : items) {
                Advert advert = advertDAO.findById(item.getProductId()); // Ürün detayını al
                if (advert != null && advert.getPrice() != null) { // Ürün bulunduysa ve fiyatı varsa
                    BigDecimal itemPrice = advert.getPrice(); // Ürünün fiyatı
                    BigDecimal itemQuantity = new BigDecimal(item.getQuantity()); // Ürünün adedi
                    BigDecimal itemTotal = itemPrice.multiply(itemQuantity); // Kalem toplamı = Fiyat * Adet
                    total = total.add(itemTotal); // Genel toplama ekle
                } else {
                     System.err.println("Uyarı: Sepetteki ürün (ID: "+item.getProductId()+") bulunamadı veya fiyatı yok, toplam hesaplamasına dahil edilmedi.");
                }
            }
        } catch (Exception e) {
             System.err.println("Sepet toplamı hesaplanırken hata: " + e.getMessage());
             e.printStackTrace();
             return BigDecimal.ZERO; // Hata durumunda 0 döndür
        }
        return total;
    
}
}
