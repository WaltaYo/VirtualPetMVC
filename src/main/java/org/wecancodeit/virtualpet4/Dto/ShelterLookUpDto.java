package org.wecancodeit.virtualpet4.Dto;



public class ShelterLookUpDto { //will give only necessary details to user, instead of alll shelter details
    private String name;
    private Long id;

    
    public ShelterLookUpDto() {
    }


    public ShelterLookUpDto(String name, Long id) {
        this.name = name;
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    

    
}