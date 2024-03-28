package org.wecancodeit.virtualpet4.Repositories;

import org.wecancodeit.virtualpet4.Models.RoboticPetModel;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RoboticPetRepository extends ClientHttp {

    private ObjectMapper objectMapper = new ObjectMapper();

    public RoboticPetRepository(String baseUrlString) {
        super(baseUrlString);
       
    }

    public RoboticPetModel getById (Long id) throws Exception{
        String model = getUrl(id.toString());
        RoboticPetModel result = objectMapper.readValue(model, RoboticPetModel.class);
        return result;
    }
    
}
