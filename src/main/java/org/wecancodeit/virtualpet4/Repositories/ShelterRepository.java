package org.wecancodeit.virtualpet4.Repositories;

import java.io.IOException;
import java.util.*;

import org.wecancodeit.virtualpet4.Models.ShelterModel;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ShelterRepository extends ClientHttp {

    private ObjectMapper objectMapper = new ObjectMapper();

    public ShelterRepository(String baseUrlString) {
        super(baseUrlString);

    }

    public ShelterModel getById(Long id) throws Exception {
        String model = getUrl(id.toString());
        ShelterModel result = objectMapper.readValue(model, ShelterModel.class);
        return result;
    }

    public Collection<ShelterModel> getAll(String urlPath) throws Exception {
        List<ShelterModel> shelterList = null;
        try {
            String jsonString = getUrl(urlPath);
            // convert JSON string to List
            shelterList = objectMapper.readValue(jsonString, new TypeReference<List<ShelterModel>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return shelterList;
    }

}
