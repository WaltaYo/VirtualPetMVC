package org.wecancodeit.virtualpet4.Repositories;

import java.io.IOException;
import java.util.*;


import org.wecancodeit.virtualpet4.Models.AdopterModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AdopterRepository extends ClientHttp {

    private ObjectMapper objectMapper = new ObjectMapper();

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


}
