package org.wecancodeit.virtualpet4.Repositories;

import org.wecancodeit.virtualpet4.Models.OrganicPetModel;

import com.fasterxml.jackson.databind.ObjectMapper;

public class OrganicPetRepository extends ClientHttp {

    private ObjectMapper objectMapper = new ObjectMapper();

    public OrganicPetRepository(String baseUrlString) {
        super(baseUrlString);
       
    }

    public OrganicPetModel getById (Long id) throws Exception{
        String model = getUrl(id.toString());
        OrganicPetModel result = objectMapper.readValue(model, OrganicPetModel.class);
        return result;
    }
    
}
