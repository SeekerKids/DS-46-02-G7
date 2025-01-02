package models;

public interface Subscription {

    final double TAX_PERCENT = 0.12;

    void upgrade();

    void cancel();
}
