package io.github.aungkothet.padc.assignment5.data.vos;

import com.google.gson.annotations.SerializedName;

public class HouseVo {

    @SerializedName("id")
    private int id;

    @SerializedName("house_image_url")
    private String houseImageUrl;
    
    @SerializedName("name")
    private String name;
    
    @SerializedName("description")
    private String description;
    
    @SerializedName("price")
    private long price;
    
    @SerializedName("address")
    private String address;
    
    @SerializedName("square_feet")
    private int squareFeet;

    @SerializedName("latitude")
    private double latitude;

    @SerializedName("longitude")
    private double longitude;


    public int getSquareFeet() {
        return squareFeet;
    }

    public void setSquareFeet(int squareFeet) {
        this.squareFeet = squareFeet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHouseImageUrl() {
        return houseImageUrl;
    }

    public void setHouseImageUrl(String houseImageUrl) {
        this.houseImageUrl = houseImageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSquare_feet() {
        return squareFeet;
    }

    public void setSquare_feet(int squareFeet) {
        this.squareFeet = squareFeet;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
