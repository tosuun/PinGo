package model;

public class users {
    private int id;
    private String name;
    private String email;
    private String password;
    private String role;         // "seller" ya da "buyer"
    private String telNo;
    
    // Satıcıya özel alanlar
    private int satisSayisi;
    private String tc;
    private double ciro;

  public Users() {
    this.role = "buyer"; // varsayılan rol
}

    // Constructor
    public users(int id, String name, String email, String password, String role,
                 String telNo, int satisSayisi, String tc, double ciro) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.telNo = telNo;
        this.satisSayisi = satisSayisi;
        this.tc = tc;
        this.ciro = ciro;
    }

    // Getter ve Setter'lar
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public String getTelNo() {
        return telNo;
    }
    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public int getSatisSayisi() {
        return satisSayisi;
    }
    public void setSatisSayisi(int satisSayisi) {
        this.satisSayisi = satisSayisi;
    }

    public String getTc() {
        return tc;
    }
    public void setTc(String tc) {
        this.tc = tc;
    }

    public double getCiro() {
        return ciro;
    }
    public void setCiro(double ciro) {
        this.ciro = ciro;
    }
}

