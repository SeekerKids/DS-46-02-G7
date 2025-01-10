package models;

public class Free extends User implements Subscription {

    public Free() {
        super();
    }

    public Free(int id, String username, String email, String password) {
        super(id, username, email, password, false); // Default status free
    }

    @Override
    public double getPrice() {
        return 0.0;  // Pengguna Free tidak membayar
    }

    @Override
    public String upgrade() {
        Premium premiumUser = new Premium(getId(), getUsername(), getEmail(), getPassword());
        premiumUser.setPremium(true);  // Set pengguna ke status Premium

        return "Akun Anda telah berhasil di-upgrade menjadi premium!";
    }

    @Override
    public String cancel() {
        return "Anda tidak dapat membatalkan langganan karena Anda adalah pengguna Free.";
    }
}
