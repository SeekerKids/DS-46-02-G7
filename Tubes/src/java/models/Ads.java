package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Ads extends Model<Ads> {

    private int id;
    private String content;  // URL atau path gambar
    private double duration;

    public Ads() {
        this.table = "ads";
        this.primaryKey = "id";
    }

    public Ads(int id, String content, double duration) {
        this();
        this.id = id;
        this.content = content;  // URL gambar
        this.duration = duration;
    }

    // Getter dan setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;  // URL gambar
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    @Override
    public Ads toModel(ResultSet rs) {
        try {
            Ads ad = new Ads();
            ad.setId(rs.getInt("id"));
            ad.setContent(rs.getString("content"));
            ad.setDuration(rs.getDouble("duration"));
            return ad;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
