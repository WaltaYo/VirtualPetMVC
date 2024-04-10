package org.wecancodeit.virtualpet4.Dto;

import org.wecancodeit.virtualpet4.Models.AdopterModel;
import org.wecancodeit.virtualpet4.Models.Enums.*;

public class AdopterDto {

    private Long id;
    private String name;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;
    private String email;
    private String imageUrl;
    private PetTypeEnum preferredPetType;
    private AdoptionStatusEnum adoptionStatus;
    private String notes;

    /**
     * Blank constructor
     */
    public AdopterDto() {
    }

    /**
     * Parametarized constructor for this class
     * 
     * @param id
     * @param name
     * @param addressLine1
     * @param addressLine2
     * @param city
     * @param state
     * @param zip
     * @param phoneNumber
     * @param email
     * @param imageUrl
     * @param preferredPetType
     * @param adoptionStatus
     * @param notes
     */
    public AdopterDto(Long id, String name, String addressLine1, String addressLine2, String city,
     String state, String zip, String phoneNumber, String email, String imageUrl, 
     PetTypeEnum preferredPetType, AdoptionStatusEnum adoptionStatus, String notes) {
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
        this.preferredPetType = preferredPetType;
        this.adoptionStatus = adoptionStatus;
        this.notes = notes;
    }

    /**
     * Parametarized constructor to get details from AdopterModel
     * 
     * @param model
     */
    public AdopterDto(AdopterModel model) {
        this.id = model.getId();
        this.name = model.getName();
        this.addressLine1 = model.getAddressLine1();
        this.addressLine2 = model.getAddressLine2();
        this.city = model.getCity();
        this.state = model.getState();
        this.zip = model.getZip();
        this.phoneNumber = model.getPhoneNumber();
        this.email = model.getEmail();
        this.imageUrl = model.getImageUrl();
        this.preferredPetType = model.getPreferredPetType();
        this.adoptionStatus = model.getAdoptionStatus();
        this.notes = model.getNotes();
    }

     /**
     * Method to convert Adopterdto to AdopterModel
     * 
     * @return
     */
    public AdopterModel convertToModel() {
        AdopterModel adopter = new AdopterModel(this.getId(), this.getName(), this.getAddressLine1(),
                this.getAddressLine2(), this.getCity(), this.getState(), this.getZip(), this.getPhoneNumber(),
                this.getEmail(), this.getImageUrl(), this.getPreferredPetType(), this.getAdoptionStatus(), 
                this.getNotes());
        return adopter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public PetTypeEnum getPreferredPetType() {
        return preferredPetType;
    }

    public void setPreferredPetType(PetTypeEnum preferredPetType) {
        this.preferredPetType = preferredPetType;
    }

    public AdoptionStatusEnum getAdoptionStatus() {
        return adoptionStatus;
    }

    public void setAdoptionStatus(AdoptionStatusEnum adoptionStatus) {
        this.adoptionStatus = adoptionStatus;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
