package org.wecancodeit.virtualpet4.Repositories;

import java.io.IOException;
import java.util.*;

import org.wecancodeit.virtualpet4.Models.PetMaintenanceModel;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PetMaintenanceRepository extends ClientHttp {

    private ObjectMapper objectMapper = new ObjectMapper();

    public PetMaintenanceRepository(String baseUrlString) {
        super(baseUrlString);

    }

    public PetMaintenanceModel getById(Long id) throws Exception {
        String model = getUrl(id.toString());
        PetMaintenanceModel result = objectMapper.readValue(model, PetMaintenanceModel.class);
        return result;
    }

    public Collection<PetMaintenanceModel> getAll(String urlPath) throws Exception {
        List<PetMaintenanceModel> petMAintenanceList = null;
        try {
            String jsonString = getUrl(urlPath);
            // convert JSON to string list
            petMAintenanceList = objectMapper.readValue(jsonString, new TypeReference<List<PetMaintenanceModel>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return petMAintenanceList;
    }

}
