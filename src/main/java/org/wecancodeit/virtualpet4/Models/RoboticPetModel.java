package org.wecancodeit.virtualpet4.Models;

import java.util.ArrayList;
import org.wecancodeit.virtualpet4.Models.Enums.PetTypeEnum;
import org.wecancodeit.virtualpet4.Models.Enums.RoboticPetHealthEnum;


/**
 * Class that models the robotic pet
 */

public class RoboticPetModel extends PetModel {


    private String model;

 
    private String manufacturer;


    private int energyLevel;


    private int oilLevel;

    private RoboticPetHealthEnum petHealth;


    private ShelterModel shelterModel;

    /**
     * Default Constructor
     */
    public RoboticPetModel() {
        super();
    }

    /**
     * Parameterized Constructor
     * 
     * @param petName            pet name
     * @param petType            pet type
     * @param imageURL           pet image url
     * @param petModel           pet model
     * @param petManufacturer    pet manufacturer
     * @param energyLevel        pet energy level
     * @param oilLevel           pet oil level
     * @param petHealth          pet health
     * @param maintenanceTaskIDs maintenance task IDs
     * @param scheduleTaskIDs    scheduled task IDs
     */
    public RoboticPetModel(String petName, PetTypeEnum petType, String imageURL, String model,
            String manufacturer, int energyLevel, int oilLevel,
            RoboticPetHealthEnum petHealth, ArrayList<Long> maintenanceTaskIDs, ArrayList<Long> scheduleTaskIDs) {
        super(petName, petType, imageURL, maintenanceTaskIDs, scheduleTaskIDs);
        this.model = model;
        this.manufacturer = manufacturer;
        this.energyLevel = energyLevel;
        this.oilLevel = oilLevel;
        this.petHealth = petHealth;
    }

    /**
     * Method to get the pet model
     * 
     * @return model #
     */
    public String getModel() {
        return model;
    }

    /**
     * Method to get the pet manufacturer
     * 
     * @return pet manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Method to get pet energy level
     * 
     * @return Energy Level
     */
    public int getEnergyLevel() {
        return energyLevel;
    }

    /**
     * Method to get the oil level
     * 
     * @return oil level
     */
    public int getOilLevel() {
        return oilLevel;
    }

    /**
     * Method to get the pet health
     * 
     * @return pet health
     */
    public RoboticPetHealthEnum getPetHealth() {
        return petHealth;
    }

    /**
     * Override method for toString
     */
    @Override
    public String toString() {
        return super.toString() + "RoboticPetModel [petModel=" + model + ", petManufacturer=" + manufacturer
                + ", energyLevel="
                + energyLevel + ", oilLevel=" + oilLevel + ", petHealth=" + petHealth + "]";
    }

}