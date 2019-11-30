package com.example.order.event;

public class Address {
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;
    
    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getCountry() {
        return country;
    }
    
    public Address withCity(String city) {
        this.city = city;
        return this;
    }
    
    public Address withCountry(String country) {
        this.country = country;
        return this;
    }
    
    public Address withState(String state) {
        this.state = state;
        return this;
    }
    
    public Address withStreet(String street) {
        this.street = street;
        return this;
    }
    
    public Address withZip(String zip) {
        this.zip = zip;
        return this;
    }
}
