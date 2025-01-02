package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Album extends Model<Album> {

    private int id;                     // primary key
    private int artistId;               // foreign key
    private String title;
    private Date relase;
    private ArrayList<Track> tracks;

    public Album() {
        this.table = "album";
        this.primaryKey = "id";
        this.tracks = new ArrayList<>();
    }

    public Album(int id, int artistId, String title, Date relase) {
        this();
        this.id = id;
        this.artistId = artistId;
        this.title = title;
        this.relase = relase;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int id) {
        this.artistId = artistId;
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
            album.setArtistId(rs.getInt("artist_id"));
            return album;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public void addTrack(Track track) {
        for (Track t : tracks) {
            if (t.getId() == track.getId()) {
                return;
            }
        }
        tracks.add(track);
        track.setAlbumId(this.id);
        track.update();
    }

    public void deleteTrack(int trackId) {
        Track trackToRemove = null;
        for (Track t : tracks) {
            if (t.getId() == trackId) {
                trackToRemove = t;
                break;
            }
        }
        if (trackToRemove == null) {
            System.out.println("Track dengan ID " + trackId + " tidak ditemukan di album " + title);
            return;
        }
        tracks.remove(trackToRemove);
        trackToRemove.delete();
        System.out.println("Track dengan ID " + trackId + " berhasil dihapus dari album " + title);
    }

}
