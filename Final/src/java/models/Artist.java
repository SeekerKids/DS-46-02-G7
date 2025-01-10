package models;

import java.sql.*;
import java.util.ArrayList;

public class Artist extends Model<Artist> {

    private int id;
    private String name;
    private String bio;
    private String gambar; // URL gambar

    public Artist() {
        this.table = "artist";  // Nama tabel di database
        this.primaryKey = "id";  // Primary key di tabel artist
    }

    @Override
    public Artist toModel(ResultSet rs) {
        Artist artist = new Artist();
        try {
            artist.id = rs.getInt("id");
            artist.name = rs.getString("name");
            artist.bio = rs.getString("bio");
            artist.gambar = rs.getString("gambar");
        } catch (SQLException e) {
            System.out.println("Error mapping artist: " + e.getMessage());
        }
        return artist;
    }

    // Getter dan Setter untuk kolom-kolom di tabel artist
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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    // Metode untuk menambahkan artist baru ke dalam database
    public boolean create() {
        String sql = "INSERT INTO " + this.table + " (name, bio, gambar) VALUES (?, ?, ?)";
        try {
            connect(); // Pastikan koneksi dibuat sebelum digunakan
            PreparedStatement stmt = getCon().prepareStatement(sql);
            stmt.setString(1, this.name);
            stmt.setString(2, this.bio);
            stmt.setString(3, this.gambar);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error creating artist: " + e.getMessage());
            return false;
        }
    }

    // Metode untuk mengedit artis berdasarkan ID
    public boolean edit() {
        String sql = "UPDATE " + this.table + " SET name = ?, bio = ?, gambar = ? WHERE id = ?";
        try {
            connect(); // Pastikan koneksi dibuat sebelum digunakan
            PreparedStatement stmt = getCon().prepareStatement(sql);
            stmt.setString(1, this.name);
            stmt.setString(2, this.bio);
            stmt.setString(3, this.gambar);
            stmt.setInt(4, this.id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error editing artist: " + e.getMessage());
            return false;
        }
    }

    // Metode untuk menghapus artist dari database berdasarkan ID
    public boolean delete() {
        String sql = "DELETE FROM " + this.table + " WHERE id = ?";
        try {
            connect(); // Pastikan koneksi dibuat sebelum digunakan
            PreparedStatement stmt = getCon().prepareStatement(sql);
            stmt.setInt(1, this.id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting artist: " + e.getMessage());
            return false;
        }
    }

    // Metode untuk menemukan artist berdasarkan ID
    public Artist findById(int id) {
        String sql = "SELECT * FROM " + this.table + " WHERE id = ?";
        try {
            connect(); // Pastikan koneksi dibuat sebelum digunakan
            PreparedStatement stmt = getCon().prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return toModel(rs); // Map hasil ResultSet ke objek Artist
            }
        } catch (SQLException e) {
            System.out.println("Error finding artist by id: " + e.getMessage());
        }
        return null; // Return null jika artist tidak ditemukan
    }

    public Artist findByName(String name) {
        String sql = "SELECT * FROM " + this.table + " WHERE name = ?";
        try {
            connect(); // Pastikan koneksi dibuat sebelum digunakan
            PreparedStatement stmt = getCon().prepareStatement(sql);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return toModel(rs); // Map hasil ResultSet ke objek Artist
            }
        } catch (SQLException e) {
            System.out.println("Error finding artist by id: " + e.getMessage());
        }
        return null; // Return null jika artist tidak ditemukan
    }

    // Fungsi untuk mendapatkan semua artis
    public ArrayList<Artist> getAll() {
        ArrayList<Artist> artists = new ArrayList<>();
        String sql = "SELECT * FROM " + this.table;
        try {
            connect(); // Pastikan koneksi dibuat sebelum digunakan
            PreparedStatement stmt = getCon().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                artists.add(toModel(rs)); // Map setiap baris ke objek Artist dan tambahkan ke daftar
            }
        } catch (SQLException e) {
            System.out.println("Error fetching all artists: " + e.getMessage());
        }
        return artists; // Return daftar artis
    }

    public ResultSet getTracks() {
        String sql = "SELECT * FROM track WHERE artist_id = ?";
        ResultSet rs = null;
        try {
            connect();  // Pastikan koneksi sudah terbuka
            PreparedStatement stmt = getCon().prepareStatement(sql);
            stmt.setInt(1, this.id);  // Set artist_id dari object artist
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error getting tracks: " + e.getMessage());
        }
        return rs;
    }

}
