package org.wecancodeit.virtualpet4.Repositories;

import java.io.IOException;
import java.util.*;

import org.springframework.stereotype.Service;
import org.wecancodeit.virtualpet4.Models.AdopterModel;
import org.wecancodeit.virtualpet4.Models.ShelterModel;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AdopterRepository extends ClientHttp {

    private ObjectMapper objectMapper = new ObjectMapper();

    public AdopterRepository() {
        super("http://localhost:8080/api/v1/adopters/");
    }

    public AdopterRepository(String baseUrlString) {
        super(baseUrlString);

    }

    public AdopterModel getById(Long id) throws Exception {
        String model = getUrl(id.toString());
        AdopterModel result = objectMapper.readValue(model, AdopterModel.class);
        return result;
    }

    public Collection<AdopterModel> getAll(String urlPath) throws Exception {
        List<AdopterModel> adopterList = null;
        try {
            String jsonString = getUrl(urlPath);
            // convert JSON string to List
            adopterList = objectMapper.readValue(jsonString, new TypeReference<List<AdopterModel>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return adopterList;
    }

    public AdopterModel saveAdopter(AdopterModel model) throws Exception {
        try {
            String json = objectMapper.writeValueAsString(model);
            String result = saveObject(json);
            objectMapper.readValue(result, new TypeReference<AdopterModel>() {
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return model;
    }

}
