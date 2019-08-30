package io.github.aungkothet.padc.assignment5.data.vos;

public class HouseVo {

    private String house_image_url;
    private String name;
    private String description;
    private long price;
    private String address;
    private int square_feet;
    private double latitude;
    private double longitude;

    public String getHouseImageUrl() {
        return house_image_url;
    }

    public void setHouseImageUrl(String houseImageUrl) {
        this.house_image_url = houseImageUrl;
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
        return square_feet;
    }

    public void setSquare_feet(int square_feet) {
        this.square_feet = square_feet;
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
