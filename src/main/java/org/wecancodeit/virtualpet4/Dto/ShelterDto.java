package org.wecancodeit.virtualpet4.Dto;

import org.wecancodeit.virtualpet4.Models.ShelterModel;

public class ShelterDto {

    private long id;

    private String name;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String zip;

    private String phoneNumber;

    private String email;

    private String imageUrl;

    private String website;

    

    public ShelterDto() {
    }



    public ShelterDto(long id, String name, String addressLine1, String addressLine2, String city, String state,
            String zip, String phoneNumber, String email, String imageUrl, String website) {
        this.id = id;
        this.name = name;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.imageUrl = imageUrl;
        this.website = website;
    }

    public ShelterDto(ShelterModel model){
        this.id = model.getID();
        this.name = model.getName();
        this.addressLine1 = model.getAddressLine1();
        this.addressLine2 = model.getAddressLine2();
        this.city = model.getCity();
        this.state = model.getState();
        this.zip = model.getZip();
        this.phoneNumber = model.getPhoneNumber();
        this.email = model.getEmail();
        this.imageUrl = model.getImageUrl();
        this.website = model.getWebsite();

    }
    
    public ShelterModel convertToModel(){
        ShelterModel shelter = new ShelterModel(this.getId(), 
        this.getName(), this.getAddressLine1(), this.getAddressLine2(), 
        this.getCity(), this.getState(), this.getZip(), this.getPhoneNumber(), this.getEmail(),
        this.getImageUrl(), this.getWebsite());
        return shelter;
    }





    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }



    public String getWebsite() {
        return website;
    }



    public void setWebsite(String website) {
        this.website = website;
    }



    public long getId() {
        return id;
    }



    public void setId(long id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    public String getAddressLine1() {
        return addressLine1;
    }



    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }



    public String getAddressLine2() {
        return addressLine2;
    }



    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }



    public String getCity() {
        return city;
    }



    public void setCity(String city) {
        this.city = city;
    }



    public String getState() {
        return state;
    }



    public void setState(String state) {
        this.state = state;
    }



    public String getZip() {
        return zip;
    }



    public void setZip(String zip) {
        this.zip = zip;
    }



    public String getPhoneNumber() {
        return phoneNumber;
    }



    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



    public String getEmail() {
        return email;
    }



    public void setEmail(String email) {
        this.email = email;
    }



    public String getImageUrl() {
        return imageUrl;
    }




    
    
}
