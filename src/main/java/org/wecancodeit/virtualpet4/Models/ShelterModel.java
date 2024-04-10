package org.wecancodeit.virtualpet4.Models;

import java.util.*;

/**
 * Class that models the pet shelter
 */

public class ShelterModel extends ContactModel {

    /**
     * Below are the models that are going to populate the shelter
     */
    private Collection<OrganicPetModel> organicPets;
    private Collection<RoboticPetModel> roboticPets;
    private Collection<AdopterModel> adopters;
    private Collection<VolunteerModel> volunteers;

    private String website; // for shelter website

    /**
     * Default Constructor
     */
    public ShelterModel() {
       
    }

    /**
     * Parametarized constructor 
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
     * @param website
     */
    public ShelterModel(Long id, String name, String addressLine1, String addressLine2, String city, String state,
            String zip, String phoneNumber, String email, String imageUrl, String website) {
        super(id, name, addressLine1, addressLine2, city, state, zip, phoneNumber, email, imageUrl);
        this.website = website;
    
    }

    
    /**
     * Method to get website
     * @return  website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Method to set website
     * @return this website
     */
    public void setWebsite(String website) {
        this.website = website;
    }


    /**
     * Method to get the organic pets in the shelter
     * 
     * @return organic pets in the shelter
     */
    public Collection<OrganicPetModel> getOrganicPets() {
        return organicPets;
    }

    /**
     * Method to get the robotic pets in the shelter
     * 
     * @return robotic pets in the shelter
     */
    public Collection<RoboticPetModel> getRoboticPets() {
        return roboticPets;
    }

    /**
     * Method to get the adopters in the shelter
     * 
     * @return adopters in the shelter
     */
    public Collection<AdopterModel> getAdopters() {
        return adopters;
    }

    /**
     * Method to get the volunteers in the shelter
     * 
     * @return volunteers in the shelter
     */
    public Collection<VolunteerModel> getVolunteers() {
        return volunteers;
    }


    @Override
    public String toString() {
        return "ShelterModel [organicPets=" + organicPets + ", roboticPets=" + roboticPets + ", adopters=" + adopters
                + ", volunteers=" + volunteers + "]";
    }


}

