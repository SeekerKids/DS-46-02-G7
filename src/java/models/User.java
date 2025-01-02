package models;
//masih belom
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private Subscription type;
    private List<String> playlists;

    // Constructor
    public User(String userID, String username, String password, String email, Subscription subscriptionType) {
        this.id = userID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.subscriptionType = subscriptionType;
        this.playlists = new ArrayList<>();
    }

    public void createPlaylist(String playlistName) {
        playlists.add(playlistName);
        System.out.println("Playlist '" + playlistName + "' created.");
    }

    public void deletePlaylist(String playlistName) {
        if (playlists.remove(playlistName)) {
            System.out.println("Playlist '" + playlistName + "' deleted.");
        } else {
            System.out.println("Playlist '" + playlistName + "' not found.");
        }
    }

    public void upgradeSubs(Subscription newSubscription) {
        System.out.println("Upgrading subscription from " + subscriptionType.getClass().getSimpleName() + " to " + newSubscription.getClass().getSimpleName() + ".");
        this.subscriptionType = newSubscription;
    }

    @Override
    public String toString() {
        return "User [userID=" + id + ", username=" + username + ", email=" + email + ", subscriptionType=" + subscriptionType.getClass().getSimpleName() + ", playlists=" + playlists + "]";
    }
}

