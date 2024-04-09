package org.wecancodeit.virtualpet4.Models;

public class VolunteerModel extends ContactModel {

    /**
     * Default Constructor
     */
    public VolunteerModel() {
        super();
    }

    /**
     * Parameterized Constructor
     * 
     * @param name         volunteer's name
     * @param addressLine1 volunteer's address line 1
     * @param addressLine2 volunteer's address line 2
     * @param city         volunteer's city
     * @param state        volunteer's state (abbreviated)
     * @param zip          volunteer's zip code
     * @param phoneNumber  volunteer's phone number
     * @param email        volunteer's email
     * @param imageURL     volunteer's image url
     */
    public VolunteerModel(Long id, String name, String addressLine1, String addressLine2, String city, String state,
            String zip, String phoneNumber, String email, String imageUrl, String website) {
        super(id, name, addressLine1, addressLine2, city, state,
                zip, phoneNumber, email, imageUrl);
    }

    /**
     * Override method for toString
     */
    @Override
    public String toString() {
        return super.toString() + "VolunteerModel []";
    }

    public VolunteerModel getById(VolunteerModel id) {
        return id;
    
    }

    
}
