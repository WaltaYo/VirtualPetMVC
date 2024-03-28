package org.wecancodeit.virtualpet4.Repositories;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.wecancodeit.virtualpet4.Models.PetTaskScheduleModel;
import com.fasterxml.jackson.core.type.TypeReference;
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

    public Collection<PetTaskScheduleModel> getAll(String urlPath) throws Exception {
        List<PetTaskScheduleModel> petTaskList = null;
        try {
            String jsonString = getUrl(urlPath);
            // convert JSON string to List
            petTaskList = objectMapper.readValue(jsonString, new TypeReference<List<PetTaskScheduleModel>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return petTaskList;
    }

  

   
   
    
}
