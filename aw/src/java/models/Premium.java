package models;

public class Premium extends User implements Subscription {

    private static final double BASE_PRICE = 150_000.0;

    public Premium() {
        super();
    }

    public Premium(int id, String username, String email, String password) {
        super(id, username, email, password, true);
    }

    @Override
    public double getPrice() {
        return BASE_PRICE + (BASE_PRICE * TAX_PERCENT);
    }

    @Override
    public String upgrade() {
        return "Anda sudah menjadi pengguna Premium!";
    }

    @Override
    public String cancel() {
        Free freeUser = new Free(getId(), getUsername(), getEmail(), getPassword());
        freeUser.setPremium(false);  // Set pengguna ke status Free

        // Jika pengguna di-upgrade ke Premium, mereka akan dibalikkan ke status Free
        return "Langganan Premium Anda telah dibatalkan dan akun Anda dikembalikan ke status Free.";
    }
}
