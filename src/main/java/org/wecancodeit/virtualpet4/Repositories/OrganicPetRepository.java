package org.wecancodeit.virtualpet4.Repositories;

import java.io.IOException;
import java.util.*;

import org.springframework.stereotype.Service;
import org.wecancodeit.virtualpet4.Models.OrganicPetModel;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OrganicPetRepository extends ClientHttp {

    private ObjectMapper objectMapper = new ObjectMapper();

    public OrganicPetRepository() {
        super("http://localhost:8080/api/v1/organicpets/");

    }

    public OrganicPetRepository(String baseUrlString) {
        super(baseUrlString);

    }

    public OrganicPetModel getById(Long id) throws Exception {
        String model = getUrl(id.toString());
        OrganicPetModel result = objectMapper.readValue(model, OrganicPetModel.class);
        return result;
    }

    public Collection<OrganicPetModel> getAll(String urlPath) throws Exception {
        List<OrganicPetModel> organicPetList = null;
        try {
            String jsonString = getUrl(urlPath);
            // convert Json string to list
            organicPetList = objectMapper.readValue(jsonString, new TypeReference<List<OrganicPetModel>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return organicPetList;
    }

}
