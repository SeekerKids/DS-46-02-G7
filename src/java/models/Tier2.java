package models;
//ini Family

public class Tier2 extends User implements Subscription {
    
    private int id;
    private String type;
    private double price;
    private String benefits;
    

    public Tier2(int id, String type, double price, String benefits) {
        this.id = id;
        this.type = "Family";
        this.price = price;
        this.benefits = benefits;
    }

    @Override
    public void upgrade() {
        System.out.println("Upgrading from Family Tier to Premium Tier.");
    }

    @Override
    public void cancel() {
        System.out.println("Family Tier subscription canceled.");
    }

    @Override
    public String toString() {
        return "FamilyTier [subscriptionID=" + id + ", type=" + type + ", price=" + price + ", benefits=" + benefits + "]";
    }
}
