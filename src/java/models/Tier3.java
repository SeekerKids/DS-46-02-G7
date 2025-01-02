package models;

public class Tier3 extends User implements Subscription {
    
    private int id;
    private String type;
    private double price;
    private String benefits;
    

    public Tier3(int id, String type, double price, String benefits) {
        this.id = id;
        this.type = "Free";
        this.price = price;
        this.benefits = benefits;
    }
        @Override
    public void upgrade() {
        System.out.println("Upgrading from Free Tier to Family or Premium Tier.");
    }

    @Override
    public void cancel() {
        System.out.println("Free Tier subscription canceled.");
    }

    @Override
    public String toString() {
        return "FreeTier [subscriptionID=" + id + ", type=" + type + ", price=" + price + ", benefits=" + benefits + "]";
    }
}
