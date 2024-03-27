package org.wecancodeit.virtualpet4.Models;

import org.wecancodeit.virtualpet4.Models.Enums.PetTaskEnum;
import org.wecancodeit.virtualpet4.Models.Enums.PetTypeEnum;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class PetMaintenanceModel {

      private long id;

    private String name;

    private int frequency;

    private PetTypeEnum petType;
    private PetTaskEnum effectedProperty;

    /**
     * Default Constructor
     */
    public PetMaintenanceModel() {
    }

    /**
     * Parameterized Constructor
     * 
     * @param name maintenance name
     * @param frequency frequency required
     * @param petType type of pet
     * @param effectedProperty effected property of pet
     */
    public PetMaintenanceModel(String name, int frequency, PetTypeEnum petType,
            PetTaskEnum effectedProperty) {
        this.name = name;
        this.frequency = frequency;
        this.petType = petType;
        this.effectedProperty = effectedProperty;
    }

    /**
     * Method to get the id
     * 
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * Method to set the id
     * 
     * @param id id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Method to get the name
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Method to get the frequency
     * 
     * @return frequency
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * Method to get the pet type
     * 
     * @return pet type
     */
    public PetTypeEnum getPetType() {
        return petType;
    }

    /**
     * Method to get the effected property
     * 
     * @return effected property
     */
    public PetTaskEnum getEffectedProperty() {
        return effectedProperty;
    }

    /**
     * Override method for toString
     */
    @Override
    public String toString() {
        return "PetMaintenanceModel [id=" + id + ", name=" + name + ", frequency=" + frequency + ", petType=" + petType
                + ", effectedProperty=" + effectedProperty + "]";
    }

}

    

