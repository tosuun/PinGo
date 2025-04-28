package service;

/*(veya IlanService): Ürün (ilan) ekleme (addProduct), ürünleri listeleme (getAllProducts, getProductsByCategory), 
ürün detaylarını getirme (getProductById), ürün güncelleme (updateProduct), ürün silme (deleteProduct
*/
import java.util.List;

import dao.AdvertDao;
import model.Advert;


public class ProductService {

	private AdvertDao advertDAO = new AdvertDao();
	// Eklemek için.
   public void addProduct(Advert advert) {
       advertDAO.save(advert);
   }
   // Listelemek için.
   public List<Advert> getAllProducts() {
       return advertDAO.findAll();
   }
   //Id'ye göre bul.
   public Advert findById(int id) {
       return advertDAO.findById(id);
   }
   //Güncelleme.
   public void updateProduct(Advert advert) {
       advertDAO.update(advert);
   }
   //Id'ye göre sil.
   public void deleteProduct(int id) {
       advertDAO.delete(id);
   }
}
