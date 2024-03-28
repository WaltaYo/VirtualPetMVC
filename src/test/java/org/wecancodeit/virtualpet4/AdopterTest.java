package org.wecancodeit.virtualpet4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.wecancodeit.virtualpet4.Models.AdopterModel;
import org.wecancodeit.virtualpet4.Repositories.AdopterRepository;

@SpringBootTest
public class AdopterTest {
    @Test
    public void getById() throws Exception{
        AdopterRepository adopter = new AdopterRepository("http://localhost:8080/api/v1/adopters/");
        AdopterModel model = adopter.getById(1L);
        assertEquals(1, model.getID());
    }
    
}
