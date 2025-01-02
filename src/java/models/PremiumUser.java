package models;

public class PremiumUser extends User implements Subscription {

    private boolean premium;
    private double price;

    public PremiumUser(String username, String email, String password) {
        super(username, email, password);
        this.premium = true;
        this.price = 150_000.0 + (150_000.0 * TAX_PERCENT); // Harga $10 + pajak
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public void upgrade() {
        System.out.println("You are already a Premium user!");
    }

    @Override
    public void cancel() {
        FreeUser freeUser = new FreeUser(getUsername(), getEmail(), getPassword());
        System.out.println("User " + getUsername() + " has downgraded to FreeUser.");
    }
}
