package models;
//ini Premium
public class Tier1 extends User implements Subscription {
    
    private int id;
    private String type;
    private double price;
    private String benefits;
    

    public Tier1(int id, String type, double price, String benefits) {
        this.id = id;
        this.type = "Premium";
        this.price = price;
        this.benefits = benefits;
    }

    @Override
    public void upgrade() {
        System.out.println("You are already on the Premium Tier. No upgrade available.");
    }

    @Override
    public void cancel() {
        System.out.println("Premium Tier subscription canceled.");
    }

    @Override
    public String toString() {
        return "PremiumTier [subscriptionID=" + id + ", type=" + type + ", price=" + price + ", benefits=" + benefits + "]";
    }
}
