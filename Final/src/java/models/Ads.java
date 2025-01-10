package models;

import java.sql.*;
import java.util.ArrayList;

public class Ads extends Model<Ads> {

    private int id;
    private String content;
    private double duration;

    public Ads() {
        this.table = "ads";  // Nama tabel di database
        this.primaryKey = "id";  // Primary key di tabel ads
    }

    @Override
    public Ads toModel(ResultSet rs) {
        Ads ads = new Ads();
        try {
            ads.id = rs.getInt("id");
            ads.setContent(rs.getString("content"));
        } catch (SQLException e) {
            System.out.println("Error mapping ads: " + e.getMessage());
        }
        return ads;
    }
    
    public Ads getRandomAd() {
        this.addQuery("ORDER BY RAND() LIMIT 1");
        return this.get().isEmpty() ? null : this.get().get(0);
    }
    public ArrayList<Ads> getAll() {
        ArrayList<Ads> Adss = new ArrayList<>();
        String sql = "SELECT * FROM " + this.table;
        try {
            connect(); // Pastikan koneksi dibuat sebelum digunakan
            PreparedStatement stmt = getCon().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Adss.add(toModel(rs)); // Map setiap baris ke objek Artist dan tambahkan ke daftar
            }
        } catch (SQLException e) {
            System.out.println("Error fetching all artists: " + e.getMessage());
        }
        return Adss; // Return daftar artis
    }
    // Getter dan Setter untuk kolom-kolom di tabel ads
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    // Metode untuk menambahkan iklan baru ke dalam database
    public boolean create() {
        String sql = "INSERT INTO " + this.table + " (content, duration) VALUES (?, ?)";
        try {
            connect(); // Pastikan koneksi dibuat sebelum digunakan
            PreparedStatement stmt = getCon().prepareStatement(sql);
            stmt.setString(1, this.content);
            stmt.setDouble(2, this.duration);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error creating ad: " + e.getMessage());
            return false;
        }
    }

    // Method to Edit an Advertisement
    public boolean edit() {
        String sql = "UPDATE " + this.table + " SET content = ?, duration = ? WHERE id = ?";
        try {
            connect(); // Pastikan koneksi dibuat sebelum digunakan
            PreparedStatement stmt = getCon().prepareStatement(sql);
            stmt.setString(1, this.content);
            stmt.setDouble(2, this.duration);
            stmt.setInt(3, this.id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error editing ad: " + e.getMessage());
            return false;
        }
    }

    // Method to Delete an Advertisement
    public boolean delete() {
        String sql = "DELETE FROM " + this.table + " WHERE id = ?";
        try {
            connect(); // Pastikan koneksi dibuat sebelum digunakan
            PreparedStatement stmt = getCon().prepareStatement(sql);
            stmt.setInt(1, this.id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting ad: " + e.getMessage());
            return false;
        }
    }

    // Method to Find an Advertisement by ID
    public Ads findById(int id) {
        String sql = "SELECT * FROM " + this.table + " WHERE id = ?";
        try {
            connect(); // Pastikan koneksi dibuat sebelum digunakan
            PreparedStatement stmt = getCon().prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return toModel(rs); // Map hasil ResultSet ke objek Ads
            }
        } catch (SQLException e) {
            System.out.println("Error finding ad by id: " + e.getMessage());
        }
        return null; // Return null jika iklan tidak ditemukan
    }
}
