package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Track extends Model<Track> {

    private int id;
    private String title;
    private String genre;
    private double duration;
    private int streamCount;
    private int likeCount;
    private int shareCount;
    private Artist artist;
    private Album album;

    public Track() {
        this.table = "track";
        this.primaryKey = "id";
        this.likeCount = 0;
        this.shareCount = 0;
        this.streamCount = 0;
    }

    public Track(int id, String title, String genre, double duration, int streamCount, Artist artist, Album album) {
        this();
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.artist = artist;
        this.album = album;
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

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public Track toModel(ResultSet rs) {
        try {
            Track track = new Track();
            track.setId(rs.getInt("id"));
            track.setTitle(rs.getString("title"));
            track.setGenre(rs.getString("genre"));
            track.setDuration(rs.getDouble("duration"));
            track.setStreamCount(rs.getInt("streamCount"));
            track.setLikeCount(rs.getInt("likeCount"));
            track.setShareCount(rs.getInt("shareCount"));

            Artist artist = new Artist();
            track.setArtist(artist.find(String.valueOf(rs.getInt("artist_id"))));

            Album album = new Album();
            track.setAlbum(album.find(String.valueOf(rs.getInt("album_id"))));

            return track;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public void play() {
        System.out.println("Playing: " + title);
        streamCount++;
        this.update();
    }

    public void like() {
        System.out.println("Liked track: " + title);
        likeCount++;
        this.update();
    }

    public void share() {
        System.out.println("Shared track: " + title);
        shareCount++;
        this.update();
    }

}
