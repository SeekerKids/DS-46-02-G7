package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Artist extends Model<Artist> {

    private int id;                     // primary key
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
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public void addAlbum(Album album) {
        boolean exists = albums.stream().anyMatch(a -> a.getId() == album.getId());
        if (!exists) {
            albums.add(album);
            album.setArtistId(this.id);
            album.update();
        } else {
            System.out.println("Album dengan ID " + album.getId() + " sudah ada di artist " + name);
        }
    }

    public void deleteAlbum(int albumId) {
        Album toRemove = albums.stream()
                .filter(album -> album.getId() == albumId)
                .findFirst()
                .orElse(null);
        if (toRemove != null) {
            albums.remove(toRemove);
            System.out.println("Album dengan ID " + albumId + " berhasil dihapus dari artist " + name);
            toRemove.setArtistId(0);
            toRemove.update();
        } else {
            System.out.println("Album dengan ID " + albumId + " tidak ditemukan di artist " + name);
        }
    }

}
