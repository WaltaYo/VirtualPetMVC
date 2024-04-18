package org.wecancodeit.virtualpet4.Models;

import org.wecancodeit.virtualpet4.Models.Enums.AdoptionStatusEnum;
import org.wecancodeit.virtualpet4.Models.Enums.PetTypeEnum;

/**
 * Class that models the adopters
 */

public class AdopterModel extends ContactModel {
    private PetTypeEnum preferredPetType;
    private AdoptionStatusEnum adoptionStatus;
    private String notes;

    private ShelterModel shelterModel;

    /**
     * Default Constructor
     */
    public AdopterModel() {

    }

    /**
     * Parameterized Constructor
     * 
     * @param name             adopter name
     * @param addressLine1     adopter address line 1
     * @param addressLine2     adopter address line 2
     * @param city             adopter city
     * @param state            adopter state (abbreviated)
     * @param zip              adopter zip
     * @param phoneNumber      adopter phone number US Only
     * @param email            adopter email
     * @param imageURL         adopter image URL
     * @param preferredPetType adopter preferred pet type
     * @param adoptionStatus
     * @param notes
     */
    public AdopterModel(Long id, String name, String addressLine1, String addressLine2, String city, String state,
            String zip,
            String phoneNumber, String email, String imageUrl, PetTypeEnum preferredPetType,
            AdoptionStatusEnum adoptionStatus, String notes) {
        super(id, name, addressLine1, addressLine2, city, state, zip, phoneNumber, email, imageUrl);
        this.preferredPetType = preferredPetType;
        this.adoptionStatus = adoptionStatus;
        this.notes = notes;
    }

    /**
     * Method to get the preferred pet type
     * 
     * @return preferred pet type
     */
    public PetTypeEnum getPreferredPetType() {
        return preferredPetType;
    }

    public AdoptionStatusEnum getAdoptionStatus() {
        return adoptionStatus;
    }

    public String getNotes() {
        return notes;
    }

    public ShelterModel getShelterModel() {
        return shelterModel;
    }

    
    
    public void setShelterModel(ShelterModel shelterModel) {
        this.shelterModel = shelterModel;
    }

    /**
     * Override for the toString
     */
    @Override
    public String toString() {
        return "AdopterModel [preferredPetType=" + preferredPetType + ", adoptionStatus=" + adoptionStatus + ", notes="
                + notes + ", shelterModel=" + shelterModel + "]";
    }

   
}
