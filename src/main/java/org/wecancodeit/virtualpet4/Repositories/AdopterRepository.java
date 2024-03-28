package org.wecancodeit.virtualpet4.Repositories;

import org.wecancodeit.virtualpet4.Models.AdopterModel;


import com.fasterxml.jackson.databind.ObjectMapper;

public class AdopterRepository extends ClientHttp {

    private ObjectMapper objectMapper = new ObjectMapper();

    public AdopterRepository(String baseUrlString) {
        super(baseUrlString);
    
    }

     public AdopterModel getById (Long id) throws Exception{
        String model = getUrl(id.toString());
        AdopterModel result = objectMapper.readValue(model, AdopterModel.class);
        return result;
    }

    
}
