package org.wecancodeit.virtualpet4.Repositories;

import org.wecancodeit.virtualpet4.Models.PetMaintenanceModel;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PetMaintenanceRepository extends ClientHttp{

    private ObjectMapper objectMapper = new ObjectMapper();

    public PetMaintenanceRepository(String baseUrlString) {
        super(baseUrlString);
       
    }

    public PetMaintenanceModel getById (Long id) throws Exception{
        String model = getUrl(id.toString());
        PetMaintenanceModel result = objectMapper.readValue(model, PetMaintenanceModel.class);
        return result;
    }

    
}
