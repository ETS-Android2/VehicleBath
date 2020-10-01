package com.example.vehiclebath.Model;

public class Subscription {

    private String Name,Price,DiscountPercentage,Validity,Availability;

    public Subscription() {

    }

    public Subscription(String name, String price, String discountPercentage, String validity, String availability) {
        Name = name;
        Price = price;
        DiscountPercentage = discountPercentage;
        Validity = validity;
        Availability = availability;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return "Rs."+Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDiscountPercentage() {
        return DiscountPercentage+"%";
    }

    public void setDiscountPercentage(String discountPercentage) {
        DiscountPercentage = discountPercentage;
    }

    public String getValidity() {
        return Validity+" months";
    }

    public void setValidity(String validity) {
        Validity = validity;
    }

    public String getAvailability() {
        return Availability;
    }

    public void setAvailability(String availability) {
        Availability = availability;
    }
}
