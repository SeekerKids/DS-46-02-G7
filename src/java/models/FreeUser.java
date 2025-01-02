package models;

public class FreeUser extends User implements Subscription {

    private boolean premium;
    private double price;

    public FreeUser(String username, String email, String password) {
        super(username, email, password);
        this.premium = false;
        this.price = 0;
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
        PremiumUser premiumUser = new PremiumUser(getUsername(), getEmail(), getPassword());
        premiumUser.setPremium(true);
        System.out.println("User " + getUsername() + " has upgraded to Premium!");
    }

    @Override
    public void cancel() {
        System.out.println("Free users cannot cancel a subscription as they don't have one.");
    }

}
