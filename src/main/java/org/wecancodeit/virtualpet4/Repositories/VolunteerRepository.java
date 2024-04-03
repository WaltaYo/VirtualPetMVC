package org.wecancodeit.virtualpet4.Repositories;

import org.springframework.stereotype.Service;
import org.wecancodeit.virtualpet4.Models.VolunteerModel;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class VolunteerRepository extends ClientHttp {

    private ObjectMapper objectMapper = new ObjectMapper();

    public VolunteerRepository() {
        super("http://localhost:8080/api/v1/volunteers/");
       
    }

    public VolunteerRepository(String baseUrlString) {
        super(baseUrlString);
       
    }

    public VolunteerModel getById (Long id) throws Exception{
        String model = getUrl(id.toString());
        VolunteerModel result = objectMapper.readValue(model, VolunteerModel.class);
        return result;
    }
    
}
