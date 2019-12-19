package com.YonginUniv.YonginRestaurants;

public class Restaurant {
    private String name;
    private String address;
    private String number;
    private String homepage;
    private String latitude;
    private String longitude;
    private String code;

    public Restaurant(String name, String address, String number, String homepage, String latitude, String longitude,
                      String code) {
        super();
        this.name = name;
        this.address = address;
        this.number = number;
        this.homepage = homepage;
        this.latitude = latitude;
        this.longitude = longitude;
        this.code = code;
    }
    public Restaurant() {
        super();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getHomepage() {
        return homepage;
    }
    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }
    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
}
