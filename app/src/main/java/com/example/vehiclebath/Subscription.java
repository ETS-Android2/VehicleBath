package com.example.vehiclebath;

public class Subscription {

    private String SubscriptionName;
    private Double SubscriptionPrice;
    private int SubscriptionValidity;
    private String SubscriptionAvailability;

    public Subscription(String subscriptionName, Double subscriptionPrice, int subscriptionValidity, String subscriptionAvailability) {
        SubscriptionName = subscriptionName;
        SubscriptionPrice = subscriptionPrice;
        SubscriptionValidity = subscriptionValidity;
        SubscriptionAvailability = subscriptionAvailability;
    }

    public String getSubscriptionName() {
        return SubscriptionName;
    }

    public void setSubscriptionName(String subscriptionName) {
        SubscriptionName = subscriptionName;
    }

    public Double getSubscriptionPrice() {
        return SubscriptionPrice;
    }

    public void setSubscriptionPrice(Double subscriptionPrice) {
        SubscriptionPrice = subscriptionPrice;
    }

    public int getSubscriptionValidity() {
        return SubscriptionValidity;
    }

    public void setSubscriptionValidity(int subscriptionValidity) {
        SubscriptionValidity = subscriptionValidity;
    }

    public String getSubscriptionAvailability() {
        return SubscriptionAvailability;
    }

    public void setSubscriptionAvailability(String subscriptionAvailability) {
        SubscriptionAvailability = subscriptionAvailability;
    }
}
