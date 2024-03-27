package org.wecancodeit.virtualpet4.Models;

import java.util.ArrayList;

import org.wecancodeit.virtualpet4.Models.Enums.PetTypeEnum;

/**
 * Class that models the pets
 */
public abstract class PetModel {

    private long id;

    private String petName;

    private PetTypeEnum petType;
    private String imageURL;
    private ArrayList<Long> maintenanceTaskIDs;
    private ArrayList<Long> scheduleTaskIDs;

    /**
     * Default Constructor
     */
    public PetModel() {
    }

    /**
     * Parameterized Constructor
     * 
     * @param petName pet name
     * @param petType pet type
     * @param imageURL pet image url
     * @param maintenanceTaskIDs maintenance Task IDs
     * @param scheduleTaskIDs scheduled Task IDs
     */
    public PetModel( String petName, PetTypeEnum petType, String imageURL, ArrayList<Long> maintenanceTaskIDs, ArrayList<Long> scheduleTaskIDs) {
        this.petName = petName;
        this.petType = petType;
        this.imageURL = imageURL;
        this.maintenanceTaskIDs = maintenanceTaskIDs;
        this.scheduleTaskIDs = scheduleTaskIDs;
    }

    /**
     * Method to get the pet ID.
     * 
     * @return pet ID
     */
    public long getID() {
        return id;
    }

    /**
     * Method to set the pet ID
     * 
     * @param petID pet ID
     */
    public void setID(long id) {
        this.id = id;
    }

    /**
     * Method to get the pet name
     * 
     * @return pet name
     */
    public String getPetName() {
        return petName;
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
     * Method to get the image URL
     * 
     * @return image URL
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * Method to get the Maintenance Task IDs
     * 
     * @return maintenance task IDs
     */
    public ArrayList<Long> getMaintenanceTaskIDs() {
        return maintenanceTaskIDs;
    }

    /**
     * Method to get the scheduled task IDs
     * 
     * @return scheduled task IDs
     */
    public ArrayList<Long> getScheduleTaskIDs() {
        return scheduleTaskIDs;
    }

    /**
     * Override to toString
     */
    @Override
    public String toString() {
        return "PetModel [petID=" + id + ", petName=" + petName + ", petType=" + petType + ", imageURL=" + imageURL
                + "]";
    }

    
}
