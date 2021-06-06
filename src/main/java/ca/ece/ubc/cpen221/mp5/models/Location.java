package ca.ece.ubc.cpen221.mp5.models;

import ca.ece.ubc.cpen221.mp5.helpers.Validator;

import java.util.List;

public class Location implements Validator {
    private Float longitude;
    private Float latitude;
    private String full_address;
    private String city;
    private String state;
    private List<String> neighborhoods;

    public Float getLatitude() {
        return latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public List<String> getNeighborhoods() {
        return neighborhoods;
    }

    public String getCity() {
        return city;
    }

    public String getFull_address() {
        return full_address;
    }

    public String getState() {
        return state;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setFull_address(String full_address) {
        this.full_address = full_address;
    }

    public void setNeighborhoods(List<String> neighborhoods) {
        this.neighborhoods = neighborhoods;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean isObjectValidatedForInsert() {
        return getLatitude() != null && getLongitude() != null;
    }

    @Override
    public void autofillFields() {
    }
}
