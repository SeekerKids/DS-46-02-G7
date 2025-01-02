package models;

import java.sql.SQLException;
import java.util.ArrayList;

public class Playlist extends Model<Playlist> {

    private int id;                     // primary key
    private int userId;               // foreign key
    private String name;
    private String description;
    private ArrayList<Integer> tracks;

    public Playlist() {
        this.table = "playlist";
        this.primaryKey = "id";
        this.tracks = new ArrayList<>();
    }

    public Playlist(int id, int userId, String name, String description) {
        this();
        this.id = id;
        this.userId = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Integer> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<Integer> tracks) {
        this.tracks = tracks;
    }

    public void addTrack(int trackId) {
        if (!tracks.contains(trackId)) {
            tracks.add(trackId);
            System.out.println("Track dengan ID " + trackId + " berhasil ditambahkan ke playlist " + name);
            this.update();
        } else {
            System.out.println("Track dengan ID " + trackId + " sudah ada di playlist " + name);
        }
    }

    public void removeTrack(int trackId) {
        if (tracks.contains(trackId)) {
            tracks.remove(Integer.valueOf(trackId));
            System.out.println("Track dengan ID " + trackId + " berhasil dihapus dari playlist " + name);
            this.update();
        } else {
            System.out.println("Track dengan ID " + trackId + " tidak ditemukan di playlist " + name);
        }
    }

    @Override
    public Playlist toModel(java.sql.ResultSet rs) {
        try {
            Playlist playlist = new Playlist();
            playlist.setId(rs.getInt("id"));
            playlist.setUserId(rs.getInt("user_id"));
            playlist.setName(rs.getString("name"));
            playlist.setDescription(rs.getString("description"));
            return playlist;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public void loadTracks() {
        tracks.clear(); // Kosongkan list sebelum memuat ulang
        String query = "SELECT track_id FROM playlist_track WHERE playlist_id = " + this.id;
        ArrayList<ArrayList<Object>> results = this.query(query);
        for (ArrayList<Object> row : results) {
            tracks.add((Integer) row.get(0)); // Tambahkan track ID dari hasil query
        }
        System.out.println("Tracks untuk playlist " + name + " berhasil dimuat: " + tracks);
    }

}
