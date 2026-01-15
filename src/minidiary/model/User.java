package minidiary.model;

public class User {

    private int id;
    private String username;
    private String email;
    private String password;

    // ===== CONSTRUCTOR =====

    // constructor kosong (WAJIB ADA)
    public User() {
    }

    // constructor untuk register / insert
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // constructor lengkap (AMAN untuk View / Controller)
    public User(int id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // ===== GETTER & SETTER =====

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
