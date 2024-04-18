package org.wecancodeit.virtualpet4.Repositories;

import java.io.IOException;
import java.util.*;

import org.springframework.stereotype.Service;
import org.wecancodeit.virtualpet4.Dto.ShelterDto;
import org.wecancodeit.virtualpet4.Models.ShelterModel;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ShelterRepository extends ClientHttp {

    private ObjectMapper objectMapper = new ObjectMapper();

    public ShelterRepository() {
        super("http://localhost:8080/api/v1/shelters/");

    }

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
    
    public Collection<ShelterDto> getLookUp() throws Exception {
        List<ShelterDto> shelterDto =new ArrayList<>();
        try {
            String jsonString = getUrl("shelterLookUp");
            shelterDto = objectMapper.readValue(jsonString, new TypeReference<List<ShelterDto>>() {
            });
        } catch (Exception e) {
           e.printStackTrace();
        } return shelterDto;
    }

    public boolean deleteById(Long id) throws Exception {
        deleteObject(id);
        return true;

    }

    public ShelterModel saveShelter(ShelterModel model) throws Exception {
        try {
            String json = objectMapper.writeValueAsString(model);
            String result = saveObject(json);
            objectMapper.readValue(result, new TypeReference<ShelterModel>() {
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return model;
    }

   

}
