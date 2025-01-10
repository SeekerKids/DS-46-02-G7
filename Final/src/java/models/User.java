package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class User extends Model<User> {

    private int id;
    private String username;
    private String email;
    private String password;
    private boolean isPremium;

    public User() {
        this.table = "user";  // Nama tabel di database
        this.primaryKey = "id";  // Primary key di tabel user
    }

    public User(int id, String username, String email, String password, boolean isPremium) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isPremium = isPremium;
    }

    @Override
    public User toModel(ResultSet rs) {
        User user = null;
        try {
            int tempId = rs.getInt("id");
            String tempUsername = rs.getString("username");
            String tempEmail = rs.getString("email");
            String tempPassword = rs.getString("password");
            boolean premium = rs.getInt("premium") == 1;

            if (premium) {
                user = new Premium(tempId, tempUsername, tempEmail, tempPassword);
            } else {
                user = new Free(tempId, tempUsername, tempEmail, tempPassword);
            }

            user.setPremium(premium);
        } catch (SQLException e) {
            System.out.println("Error mapping user: " + e.getMessage());
        }
        return user;
    }

    // Getter dan Setter untuk kolom-kolom di tabel user
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

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }

    public double getPrice() {
        return 0.0;
    }
// Metode untuk menambahkan user baru ke dalam database

    public boolean create() {
        String sql = "INSERT INTO " + this.table + " (username, email, password, premium) VALUES (?, ?, ?, ?)";
        try {
            connect(); // Pastikan koneksi dibuat sebelum digunakan
            PreparedStatement stmt = getCon().prepareStatement(sql);
            stmt.setString(1, this.username);
            stmt.setString(2, this.email);
            stmt.setString(3, this.password);
            stmt.setBoolean(4, this.isPremium);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error creating user: " + e.getMessage());
            return false;
        }
    }

    // Metode untuk mengedit user berdasarkan ID
    public boolean edit() {
        String sql = "UPDATE " + this.table + " SET username = ?, email = ?, password = ?, premium = ? WHERE id = ?";
        try {
            connect(); // Pastikan koneksi dibuat sebelum digunakan
            PreparedStatement stmt = getCon().prepareStatement(sql);
            stmt.setString(1, this.username);
            stmt.setString(2, this.email);
            stmt.setString(3, this.password);
            stmt.setBoolean(4, this.isPremium);
            stmt.setInt(5, this.id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error editing user: " + e.getMessage());
            return false;
        }
    }

    // Metode untuk menghapus user dari database berdasarkan ID
    public boolean delete() {
        String sql = "DELETE FROM " + this.table + " WHERE id = ?";
        try {
            connect(); // Pastikan koneksi dibuat sebelum digunakan
            PreparedStatement stmt = getCon().prepareStatement(sql);
            stmt.setInt(1, this.id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
            return false;
        }
    }

    // Metode untuk menemukan user berdasarkan ID
    public User findById(int id) {
        String sql = "SELECT * FROM " + this.table + " WHERE id = ?";
        try {
            connect(); // Pastikan koneksi dibuat sebelum digunakan
            PreparedStatement stmt = getCon().prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return toModel(rs);  // Mengonversi ResultSet ke objek User
            }
        } catch (SQLException e) {
            System.out.println("Error finding user by id: " + e.getMessage());
        }
        return null;  // Return null jika user tidak ditemukan
    }

    // Metode untuk menemukan user berdasarkan username
    public User findByUsername(String username) {
        String sql = "SELECT * FROM " + this.table + " WHERE username = ?";
        try {
            connect(); // Pastikan koneksi dibuat sebelum digunakan
            PreparedStatement stmt = getCon().prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return toModel(rs);  // Mengonversi ResultSet ke objek User
            }
        } catch (SQLException e) {
            System.out.println("Error finding user by username: " + e.getMessage());
        }
        return null;  // Return null jika user tidak ditemukan
    }

    // Metode untuk mendapatkan semua user
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM " + this.table;
        try {
            connect(); // Pastikan koneksi dibuat sebelum digunakan
            PreparedStatement stmt = getCon().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users.add(toModel(rs));  // Mengonversi ResultSet ke objek User
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving all users: " + e.getMessage());
        }
        return users;
    }

}
