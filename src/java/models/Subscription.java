package models;

public interface Subscription {
    double TAX_PERCENT = 0.12;
    
    public void upgrade();
    public void cancel();
}
