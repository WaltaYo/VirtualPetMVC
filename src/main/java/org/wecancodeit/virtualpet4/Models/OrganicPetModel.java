package org.wecancodeit.virtualpet4.Models;

import java.util.ArrayList;

import org.wecancodeit.virtualpet4.Models.Enums.PetHealthEnum;
import org.wecancodeit.virtualpet4.Models.Enums.PetMoodEnum;
import org.wecancodeit.virtualpet4.Models.Enums.PetTemperamentEnum;
import org.wecancodeit.virtualpet4.Models.Enums.PetTypeEnum;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class OrganicPetModel extends PetModel{

      private String breed;

    private int petAge;

    private PetHealthEnum petHealth;
    private PetMoodEnum petMood;
    private PetTemperamentEnum petTemperament;
    private boolean petFixed;


    private ShelterModel shelterModel;

    /**
     * Default Constructor
     */
    public OrganicPetModel() {
        super();
    }

    /**
     * Parameterized Constructor
     * 
     * @param petName            pet name
     * @param petType            pet type
     * @param imageURL           pet image url
     * @param petBreed           pet breed
     * @param petAge             pet age (years)
     * @param petHealth          pet health
     * @param petMood            pet mood
     * @param petTemperament     pet Temperament
     * @param petFixed           is the pet fixed
     * @param maintenanceTaskIDs maintenance task IDs
     * @param scheduleTaskIDs    scheduled task IDs
     */

    public OrganicPetModel(String petName, PetTypeEnum petType, String imageURL, String breed,
            int petAge, PetHealthEnum petHealth, PetMoodEnum petMood, PetTemperamentEnum petTemperament,
            boolean petFixed, ArrayList<Long> maintenanceTaskIDs, ArrayList<Long> scheduleTaskIDs) {
        super(petName, petType, imageURL, maintenanceTaskIDs, scheduleTaskIDs);
        this.breed = breed;
        this.petAge = petAge;
        this.petHealth = petHealth;
        this.petMood = petMood;
        this.petTemperament = petTemperament;
        this.petFixed = petFixed;
    }

    /**
     * Method to get the pet breed
     * 
     * @return pet breed
     */
    public String getBreed() {
        return breed;
    }

    /**
     * Method to get the pet age
     * 
     * @return pet age
     */
    public int getPetAge() {
        return petAge;
    }

    /**
     * Method to get the pet health
     * 
     * @return pet health
     */
    public PetHealthEnum getPetHealth() {
        return petHealth;
    }

    /**
     * Method to get the pet mood
     * 
     * @return pet mood
     */
    public PetMoodEnum getPetMood() {
        return petMood;
    }

    /**
     * Method to get the pet temperament
     * 
     * @return pet temperament
     */
    public PetTemperamentEnum getPetTemperament() {
        return petTemperament;
    }

    /**
     * Method to get if the pet is fixed
     * 
     * @return is the pet fixed
     */
    public boolean isPetFixed() {
        return petFixed;
    }

    /**
     * Override method for the toString
     */
    @Override
    public String toString() {
        return super.toString() + "OrganicPetModel [petBreed=" + breed + ", petAge=" + petAge + ", petHealth="
                + petHealth
                + ", petMood=" + petMood + ", petTemperament=" + petTemperament + ", petFixed=" + petFixed + "]";
    }
}

    
