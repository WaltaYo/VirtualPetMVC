package org.wecancodeit.virtualpet4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.wecancodeit.virtualpet4.Models.OrganicPetModel;
import org.wecancodeit.virtualpet4.Repositories.OrganicPetRepository;

@SpringBootTest
public class OrganicPetTest {
    @Test
    public void getById() throws Exception {
        OrganicPetRepository organicPet = new OrganicPetRepository("http://localhost:8080/api/v1/organicpets/");
        OrganicPetModel model = organicPet.getById(1L);
        assertEquals(1, model.getID());
    }

    @Test
    public void getAll() throws Exception {
        OrganicPetRepository organicPet = new OrganicPetRepository("http://localhost:8080/api/v1/organicpets/");
        Collection<OrganicPetModel> model = organicPet.getAll("");
        assertEquals(model.iterator().next().getID(), 1);
    }

}
