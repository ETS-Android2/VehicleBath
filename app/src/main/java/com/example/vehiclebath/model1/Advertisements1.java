package com.example.vehiclebath.model1;

public class Advertisements1 {


    private String AddDescription;
    private String ImageURL;

    public Advertisements1(){

    }

    public Advertisements1(String addDescription, String imageURL) {
        AddDescription = addDescription;
        ImageURL = imageURL;
    }

    public String getAddDescription() {
        return AddDescription;
    }

    public void setAddDescription(String addDescription) {
        AddDescription = addDescription;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }
}
