package org.wecancodeit.virtualpet4.Repositories;

import org.wecancodeit.virtualpet4.Models.ShelterModel;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ShelterRepository extends ClientHttp{

    private ObjectMapper objectMapper = new ObjectMapper();

    public ShelterRepository(String baseUrlString) {
        super(baseUrlString);
        
    }

    public ShelterModel getById (Long id) throws Exception{
        String model = getUrl(id.toString());
        ShelterModel result = objectMapper.readValue(model, ShelterModel.class);
        return result;
    }



    
}
