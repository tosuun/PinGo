package service;

import model.User;
import dao.UserDao;
// Kullanıcı kaydı (register), kullanıcı girişi (login), kullanıcı yetkilendirme (authorize), şifre işlemleri (sıfırlama, güncelleme).

public class AuthService {
   private final UserDao UserDao = new UserDao();

   // Kullanıcı kaydı (register)
   public void register(String username, String email, String password) {
       if (UserDao.findByUsername(username) != null) {
           System.out.println("Kullanıcı adı zaten alınmış.");
          
       }
       User newUser = new User();
       newUser.setName(username);
       newUser.setEmail(email);
       newUser.setPassword(password); // Gerçek uygulamada hashle!
       UserDao.addUser(newUser);
   }

   // Giriş işlemi (login)
   public User login(String username, String password) {
       User user = UserDao.findByUsername(username);
       if (user != null && user.getPassword().equals(password)) {
           return user; // Giriş başarılı
       }
       return null; // Başarısız
   }

   // Yetkilendirme kontrolü (örneğin admin mi?)
   public boolean authorize(User user, String requiredRole) {
       return user != null && requiredRole.equalsIgnoreCase(user.getRole());
   }

   // Şifre sıfırlama (e-posta eşleşiyorsa yeni şifre atanır)
   public boolean resetPassword(String email, String newPassword) {
       User user = UserDao.findById(email);
       if (user != null) {
           user.setPassword(newPassword);
           return UserDao.updatePassword(user.getId(), newPassword);
       }
       return false;
   }

   // Mevcut kullanıcı şifresini güncelleme
   public boolean updatePassword(String userId, String oldPassword, String newPassword) {
       User user = UserDao.findById(userId);
       if (user != null && user.getPassword().equals(oldPassword)) {
           return UserDao.updatePassword(userId, newPassword);
       }
       return false;
   }
}
//cok hata alıyorum
