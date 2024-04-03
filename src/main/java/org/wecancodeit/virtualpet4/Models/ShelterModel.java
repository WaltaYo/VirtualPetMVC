package org.wecancodeit.virtualpet4.Models;

import java.util.*;

/**
 * Class that models the pet shelter
 */

public class ShelterModel extends ContactModel {

    private String shelterWebSite;

    private Collection<OrganicPetModel> organicPets;


    private Collection<RoboticPetModel> roboticPets;


    private Collection<AdopterModel> adopters;


    private Collection<VolunteerModel> volunteers;

    /**
     * Default Constructor
     */
    public ShelterModel() {
        super();
    }

    /**
     * Parameterized Constructor
     * 
     * @param name           shelter name
     * @param addressLine1   shelter address line 1
     * @param addressLine2   shelter address line 2
     * @param city           shelter city
     * @param state          shelter state (abbreviated)
     * @param zip            shelter zip code
     * @param phoneNumber    shelter phone number
     * @param email          shelter email
     * @param imageURL       shelter image url
     * @param shelterWebSite shelter website
     */
    public ShelterModel(Long id, String name, String addressLine1, String addressLine2, String city, String state,
            String zip, String phoneNumber, String email, String imageURL,
            String shelterWebSite) {
        super(id, name, addressLine1, addressLine2, city, state, zip, phoneNumber, email, imageURL);
        this.shelterWebSite = shelterWebSite;
        this.organicPets = new ArrayList<>();
        this.roboticPets = new ArrayList<>();
    }

    /**
     * Method to get the shelter website
     * 
     * @return shelter website
     */
    public String getShelterWebSite() {
        return shelterWebSite;
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

    /**
     * Override method for toString
     */
    @Override
    public String toString() {
        return super.toString() + "ShelterModel [shelterWebSite=" + shelterWebSite + "]";
    }

}

