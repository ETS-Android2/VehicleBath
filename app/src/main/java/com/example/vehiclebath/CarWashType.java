package com.example.vehiclebath;

public class CarWashType {

    private String typeName;
    private String typeDescription;
    private String typePrice;

    public CarWashType(){

    }

    public CarWashType(String typeName, String typeDescription, String typePrice) {
        this.typeName = typeName;
        this.typeDescription = typeDescription;
        this.typePrice = typePrice;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    public String getTypePrice() {
        return typePrice;
    }

    public void setTypePrice(String typePrice) {
        this.typePrice = typePrice;
    }

}
