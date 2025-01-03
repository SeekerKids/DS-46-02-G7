package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User extends Model<User> {

    private int id;
    private String username;
    private String email;
    private String password;
    private ArrayList<Playlist> playlists;

    public User() {
        this.table = "user";
        this.primaryKey = "id";
        this.playlists = new ArrayList<>();
    }

    public User(String username, String email, String password) {
        this();
        this.username = username;
        this.email = email;
        this.password = password;
    }

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

    public ArrayList<Playlist> getPlaylist() {
        return playlists;
    }

    public void setPlaylist(ArrayList<Playlist> playlist) {
        this.playlists = playlist;
    }

    @Override
    public User toModel(ResultSet rs) {
        User user = new User();
        try {
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
        } catch (SQLException e) {
            System.out.println("Error mapping user: " + e.getMessage());
        }
        return user;
    }

    public void createPlaylist(String name, ArrayList<Integer> trackIds) {
        Playlist playlist = new Playlist();
        playlist.setName(name);
        playlist.setUserId(this.id);
        playlist.insert();

        for (int trackId : trackIds) {
            String query = "INSERT INTO playlist_track (playlist_id, track_id) VALUES ("
                    + playlist.getId() + ", " + trackId + ")";
            this.query(query);
        }

        playlists.add(playlist);
    }

    public void deletePlaylist(int playlistId) {
        String query = "DELETE FROM playlist WHERE id = " + playlistId;
        this.query(query);

        query = "DELETE FROM playlist_track WHERE playlist_id = " + playlistId;
        this.query(query);

        playlists.removeIf(playlist -> playlist.getId() == playlistId);
    }

}
