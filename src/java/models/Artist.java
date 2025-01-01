package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Artist extends Model<Artist> {

    private int id;
    private String name;
    private String bio;
    private ArrayList<Album> albums;

    public Artist() {
        this.table = "artist";
        this.primaryKey = "id";
        this.albums = new ArrayList<>();
    }

    public Artist(int id, String name, String bio) {
        this();
        this.id = id;
        this.name = name;
        this.bio = bio;
    }

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

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }

    @Override
    public Artist toModel(ResultSet rs) {
        try {
            Artist artist = new Artist();
            artist.setId(rs.getInt("id"));
            artist.setName(rs.getString("name"));
            artist.setBio(rs.getString("bio"));
            return artist;
        } catch (SQLException e) {
            System.out.println("Error converting ResultSet to Artist: " + e.getMessage());
            return null;
        }
    }

    public void addAlbum(Album album) {
    if (!albums.contains(album.getId())) {
        albums.add(album.getId());
        System.out.println("Album dengan ID " + album.getId() + " berhasil ditambahkan ke artist " + name);
        // Pastikan album di-update ke database dengan set artist_id
        album.setArtistId(this.id);
        album.update();
    } else {
        System.out.println("Album dengan ID " + album.getId() + " sudah ada di artist " + name);
    }
}

}
