package org.wecancodeit.virtualpet4.Repositories;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import org.wecancodeit.virtualpet4.Models.RoboticPetModel;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RoboticPetRepository extends ClientHttp {

    private ObjectMapper objectMapper = new ObjectMapper();

    public RoboticPetRepository(String baseUrlString) {
        super(baseUrlString);
       
    }

    public RoboticPetRepository() {
        super("http://localhost:8080/api/v1/roboticpets/");
       
    }


    public RoboticPetModel getById (Long id) throws Exception{
        String model = getUrl(id.toString());
        RoboticPetModel result = objectMapper.readValue(model, RoboticPetModel.class);
        return result;
    }

    public Collection<RoboticPetModel> getAll(String urlPath) throws Exception {
     List<RoboticPetModel> roboticPetList = null;
        try {
            String jsonString = getUrl(urlPath);
            // convert Json string to list
            roboticPetList = objectMapper.readValue(jsonString, new TypeReference<List<RoboticPetModel>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return roboticPetList;
    }
    
}
