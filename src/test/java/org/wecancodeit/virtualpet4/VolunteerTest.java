package org.wecancodeit.virtualpet4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.wecancodeit.virtualpet4.Models.VolunteerModel;
import org.wecancodeit.virtualpet4.Repositories.VolunteerRepository;

@SpringBootTest
public class VolunteerTest {
   @Test
    public void getById() throws Exception{
        VolunteerRepository volunteer = new VolunteerRepository("http://localhost:8080/api/v1/volunteers/");
        VolunteerModel model = volunteer.getById(1L);
        assertEquals(1, model.getId());
    } 
}
