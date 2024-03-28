package org.wecancodeit.virtualpet4.Repositories;

import org.wecancodeit.virtualpet4.Models.VolunteerModel;

import com.fasterxml.jackson.databind.ObjectMapper;

public class VolunteerRepository extends ClientHttp {

    private ObjectMapper objectMapper = new ObjectMapper();

    public VolunteerRepository(String baseUrlString) {
        super(baseUrlString);
       
    }

    public VolunteerModel getById (Long id) throws Exception{
        String model = getUrl(id.toString());
        VolunteerModel result = objectMapper.readValue(model, VolunteerModel.class);
        return result;
    }
    
}
