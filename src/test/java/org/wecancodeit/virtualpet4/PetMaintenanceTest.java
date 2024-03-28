package org.wecancodeit.virtualpet4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.wecancodeit.virtualpet4.Models.PetMaintenanceModel;
import org.wecancodeit.virtualpet4.Repositories.PetMaintenanceRepository;

@SpringBootTest
public class PetMaintenanceTest {
    @Test
    public void getById() throws Exception{
        PetMaintenanceRepository petMaintenance = new PetMaintenanceRepository("http://localhost:8080/api/v1/petmaintenances/");
        PetMaintenanceModel model = petMaintenance.getById(1L);
        assertEquals(1, model.getId());
    }

     @Test
    public void getAll() throws Exception {
        PetMaintenanceRepository petMaintenance = new PetMaintenanceRepository("http://localhost:8080/api/v1/petmaintenances/");
        Collection<PetMaintenanceModel> model = petMaintenance.getAll("");
        assertEquals(model.iterator().next().getId(), 1);

    }
    
}
