package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Track extends Model<Track> {

    private int id;
    private String title;
    private String genre;
    private double duration;
    private int streamCount;
    private int likeCount;
    private int shareCount;
    private int artistId;

    public Track() {
        this.table = "track";  // Nama tabel di database
        this.primaryKey = "id";  // Primary key di tabel track
    }

    @Override
    public Track toModel(ResultSet rs) {
        Track track = new Track();
        try {
            track.id = rs.getInt("id");
            track.title = rs.getString("title");
            track.genre = rs.getString("genre");
            track.duration = rs.getDouble("duration");
            track.streamCount = rs.getInt("streamCount");
            track.likeCount = rs.getInt("likeCount");
            track.shareCount = rs.getInt("shareCount");
            track.artistId = rs.getInt("artist_id");
        } catch (SQLException e) {
            System.out.println("Error mapping user: " + e.getMessage());
        }
        return track;
    }

    // Getter dan Setter untuk kolom-kolom di tabel track
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public int getStreamCount() {
        return streamCount;
    }

    public void setStreamCount(int streamCount) {
        this.streamCount = streamCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    // Metode untuk menambahkan track baru ke dalam database
    public boolean create() {
        String sql = "INSERT INTO " + this.table + " (title, genre, duration, streamCount, likeCount, shareCount, artist_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            connect(); // Pastikan koneksi dibuat sebelum digunakan
            PreparedStatement stmt = getCon().prepareStatement(sql);
            stmt.setString(1, this.title);
            stmt.setString(2, this.genre);
            stmt.setDouble(3, this.duration);
            stmt.setInt(4, this.streamCount);
            stmt.setInt(5, this.likeCount);
            stmt.setInt(6, this.shareCount);
            stmt.setInt(7, this.artistId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error creating track: " + e.getMessage());
            return false;
        }
    }

    // Metode untuk mengedit track berdasarkan ID
    public boolean edit() {
        String sql = "UPDATE " + this.table + " SET title = ?, genre = ?, duration = ?, streamCount = ?, likeCount = ?, shareCount = ?, artist_id = ? WHERE id = ?";
        try {
            connect(); // Pastikan koneksi dibuat sebelum digunakan
            PreparedStatement stmt = getCon().prepareStatement(sql);
            stmt.setString(1, this.title);
            stmt.setString(2, this.genre);
            stmt.setDouble(3, this.duration);
            stmt.setInt(4, this.streamCount);
            stmt.setInt(5, this.likeCount);
            stmt.setInt(6, this.shareCount);
            stmt.setInt(7, this.artistId);
            stmt.setInt(8, this.id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error editing track: " + e.getMessage());
            return false;
        }
    }

    // Metode untuk menghapus track dari database berdasarkan ID
    public boolean delete() {
        String sql = "DELETE FROM " + this.table + " WHERE id = ?";
        try {
            connect(); // Pastikan koneksi dibuat sebelum digunakan
            PreparedStatement stmt = getCon().prepareStatement(sql);
            stmt.setInt(1, this.id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting track: " + e.getMessage());
            return false;
        }
    }

    // Metode untuk menemukan track berdasarkan ID
    public Track findById(int id) {
        String sql = "SELECT * FROM " + this.table + " WHERE id = ?";
        try {
            connect(); // Pastikan koneksi dibuat sebelum digunakan
            PreparedStatement stmt = getCon().prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return toModel(rs);  // Map hasil ResultSet ke objek Track
            }
        } catch (SQLException e) {
            System.out.println("Error finding track by id: " + e.getMessage());
        }
        return null; // Return null jika track tidak ditemukan
    }

    // Metode untuk mendapatkan semua track
    public List<Track> findAll() {
        List<Track> tracks = new ArrayList<>();
        String sql = "SELECT * FROM " + this.table;
        try {
            connect(); // Pastikan koneksi dibuat sebelum digunakan
            PreparedStatement stmt = getCon().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                tracks.add(toModel(rs)); // Map hasil ResultSet ke objek Track
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving all tracks: " + e.getMessage());
        }
        return tracks;
    }

    public void play() {
        System.out.println("Playing: " + title);
        streamCount++;
        this.edit();
    }

    public void like() {
        System.out.println("Liked track: " + title);
        likeCount++;
        this.edit();
    }

    public void share() {
        System.out.println("Shared track: " + title);
        shareCount++;
        this.edit();
    }
}
