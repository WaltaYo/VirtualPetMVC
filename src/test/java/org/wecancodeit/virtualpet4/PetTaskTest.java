package org.wecancodeit.virtualpet4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.wecancodeit.virtualpet4.Models.PetTaskScheduleModel;
import org.wecancodeit.virtualpet4.Repositories.PetTaskRepository;

@SpringBootTest
public class PetTaskTest {
    @Test
    public void getById () throws Exception{
        PetTaskRepository petTask = new PetTaskRepository("http://localhost:8080/api/v1/schedules/");
        PetTaskScheduleModel model = petTask.getById(1L);
        assertEquals(1, model.getId());
    }
    
}
