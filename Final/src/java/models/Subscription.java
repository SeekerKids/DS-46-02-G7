package models;

public interface Subscription {

    final double TAX_PERCENT = 0.12;

    String upgrade();

    String cancel();
}
