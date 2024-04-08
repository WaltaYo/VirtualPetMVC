package org.wecancodeit.virtualpet4.Models;

/**
 * Class the models the contact information
 */

public abstract class ContactModel {

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

    /**
     * Default Constructor
     */
    public ContactModel() {
    }

    /**
     * Parameterized Constructor
     * 
     * @param name         name
     * @param addressLine1 address line 1
     * @param addressLine2 address line 2
     * @param city         city
     * @param state        state (abbreviated)
     * @param zip          zip code
     * @param phoneNumber  phone number
     * @param email        email
     * @param imageURL     image URL
     */
    public ContactModel(Long id, String name, String addressLine1, String addressLine2, String city, String state,
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

    /**
     * Method to get the ID
     * 
     * @return ID
     */
    public long getID() {
        return id;
    }

    // /**
    // * Method to set the ID
    // *
    // * @param id ID
    // */
    // public void setID(long id) {
    // this.id = id;
    // }

    /**
     * Method to get the name
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Method to get the address line 1
     * 
     * @return address line 1
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * Method to get the address line 2
     * 
     * @return address line 2
     */
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * Method to get the city
     * 
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * Method to get the state
     * 
     * @return state (abbreviated)
     */
    public String getState() {
        return state;
    }

    /**
     * Method to get the zip code
     * 
     * @return zip code
     */
    public String getZip() {
        return zip;
    }

    /**
     * Method to get the phone number
     * 
     * @return phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Method to get the email
     * 
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method to get the image URL
     * 
     * @return image URL
     */
    public String getImageUrl() {
        return imageUrl;
    }

    public String getWebsite() {
        return website;
    }

    /**
     * Override method for toString
     */
    @Override
    public String toString() {
        return "ContactModel [id=" + id + ", name=" + name + ", addressLine1=" + addressLine1 + ", addressLine2="
                + addressLine2 + ", city=" + city + ", state=" + state + ", zip=" + zip + ", phoneNumber=" + phoneNumber
                + ", email=" + email + ", imageUrl=" + imageUrl + ", website=" + website + "]";
    }

}
