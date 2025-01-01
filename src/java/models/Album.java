package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Album extends Model<Album> {

    private int id;
    private String title;
    private Date relase;
    private ArrayList<Track> tracks;

    public Album() {
        this.table = "album";
        this.primaryKey = "id";
        this.tracks = new ArrayList<>();
    }

    public Album(int id, String title, Date relase) {
        this();
        this.id = id;
        this.title = title;
        this.relase = relase;
    }

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

    public Date getRelase() {
        return relase;
    }

    public void setRelase(Date relase) {
        this.relase = relase;
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }

    @Override
    public Album toModel(ResultSet rs) {
        try {
            Album album = new Album();
            album.setId(rs.getInt("id"));
            album.setTitle(rs.getString("title"));
            album.setRelase(rs.getDate("relase"));
            return album;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
