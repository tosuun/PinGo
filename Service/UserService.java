package service;

import dao.UserDao;
import model.User;

public class UserService {
    private UserDao UserDao;

    public UserService() {
        this.UserDao = new UserDao();
    }

    // Kullanıcı ekleme
    public void createUser(User user) {
        // Gerekirse burada iş mantığı eklenebilir (örneğin e-posta doğrulama vs.)
        UserDao.addUser(user);
    }

    // ID'ye göre kullanıcı getir
    public User getUserById(int id) {
        return UserDao.getUserById(id);
    }

    // Email'e göre kullanıcı getir
    public User getUserByEmail(String email) {
        return UserDao.getUserByEmail(email);
    }

    // Kullanıcı güncelleme
    public void updateUser(User user) {
        UserDao.updateUser(user);
    }

    // Kullanıcı silme
    public void deleteUser(int id) {
        UserDao.deleteUser(id);
    }
}
