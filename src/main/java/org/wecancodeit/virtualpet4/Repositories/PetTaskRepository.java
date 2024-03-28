package org.wecancodeit.virtualpet4.Repositories;

import org.wecancodeit.virtualpet4.Models.PetTaskScheduleModel;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PetTaskRepository extends ClientHttp {

    private ObjectMapper objectMapper = new ObjectMapper(); 
    
    public PetTaskRepository(String baseUrlString) {
        super(baseUrlString);
   
    }

    public PetTaskScheduleModel getById(Long id) throws Exception{
        String model = getUrl(id.toString());
        PetTaskScheduleModel result = objectMapper.readValue(model, PetTaskScheduleModel.class);
        return result;
    }

   
    
}
