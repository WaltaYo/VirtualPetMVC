package org.wecancodeit.virtualpet4.Controllers;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wecancodeit.virtualpet4.Dto.AdopterDto;
import org.wecancodeit.virtualpet4.Models.AdopterModel;
import org.wecancodeit.virtualpet4.Models.ShelterModel;
import org.wecancodeit.virtualpet4.Models.Enums.AdoptionStatusEnum;
import org.wecancodeit.virtualpet4.Models.Enums.PetTypeEnum;
import org.wecancodeit.virtualpet4.Repositories.AdopterRepository;
import org.wecancodeit.virtualpet4.Repositories.ShelterRepository;


@Controller
@RequestMapping("/adopter")
public class AdopterController {

    private final AdopterRepository adopterRepository;
    private final ShelterRepository shelterRepository;

    /**
     * Establish AdopterRepository
     */
    public AdopterController() {
        adopterRepository = new AdopterRepository("http://localhost:8080/api/v1/adopters/");
        shelterRepository = new ShelterRepository("http://localhost:8080/api/v1/shelters/");
    }

    /**
     * Method to get all adopters
     * 
     * @param model thymeleaf base model
     * @return webpage showing all adopters
     * @throws Exception
     */
    @GetMapping()
    public String getAllAdopters(Model model) throws Exception {
        var adopters = adopterRepository.getAll("");
        model.addAttribute("adopters", adopters);
        return "adopter/index";

    }

    /**
     * Method to get adopter by id
     * 
     * @param id    get adopter by id
     * @param model
     * @return webpage showing details of single adopter
     * @throws Exception
     */
    @GetMapping("/{id}")
    public String getAdopter(@PathVariable Long id, Model model) throws Exception {
        AdopterModel adopter = adopterRepository.getById(id);
        AdopterDto dto = new AdopterDto(adopter);
        model.addAttribute("adopter", dto);  //add model attribute for shelter
        return "adopter/detail";
    }
     /**
      * Method to add a new adopter

      * @param model
      * @return
      * @throws Exception
      */
    @GetMapping("create")
    public String createAdopter(Model model) throws Exception {
        AdopterDto dto = new AdopterDto();
        model.addAttribute("adopter", dto);
        model.addAttribute("title", "Edit Adopter");

        model.addAttribute("shelters", shelterRepository.getLookUp());

        List<String> prefType = enumToList(PetTypeEnum.class);
        model.addAttribute("prefType", prefType);

        List<String> adoptionStatus = enumToList(AdoptionStatusEnum.class);
        model.addAttribute("adoptionStatus", adoptionStatus);

        return "adopter/edit";
    }


    /**
     * Method to edit adopter detials
     * 
     * @param id
     * @param model
     * @return webpage with open fields allowing user to change text description
     * @throws Exception
     */
    @GetMapping("/edit/{id}")
    public String editAdopter(@PathVariable Long id, Model model) throws Exception {
        //find adopter by id
        AdopterModel adopter = adopterRepository.getById(id);

        //put adopter into adopter dto
        AdopterDto dto = new AdopterDto(adopter);
        model.addAttribute("adopter", dto);

        //find the shelter they are assoc with
        model.addAttribute("shelters", shelterRepository.getLookUp());

        //drop downs for the enums
        List<String> prefType = enumToList(PetTypeEnum.class);
        model.addAttribute("prefType", prefType);
        List<String> adoptionStatus = enumToList(AdoptionStatusEnum.class);
        model.addAttribute("adoptionStatus", adoptionStatus);

        return "adopter/edit";
    }

    /**
     * Method to save adopter
     * @param dto
     * @return
     * @throws Exception
     */
    @PostMapping
    public String saveAdopter(@ModelAttribute("adopter") AdopterDto dto) throws Exception {
        AdopterModel adopter = dto.convertToModel();
        ShelterModel shelter = shelterRepository.getById(dto.getShelterId());
        adopter.setShelterModel(shelter);
        adopterRepository.saveAdopter(adopter);
        return "redirect:/adopter";
    }
    
      /**
   * Method to delete adopter by id
   * @param id
   * @param model
   * @return webpage asking for confirmation
   * @throws Exception
   */
  @GetMapping("/delete/{id}")
  public String deleteAdopter(@PathVariable Long id, Model model) throws Exception {
    AdopterModel adopter = adopterRepository.getById(id);
    model.addAttribute("adopter", adopter);
    return "adopter/confirmDelete";
  }

  /**
   * Method to confirm deletetion of adopter by id
   * @param id
   * @param model
   * @return  adopter/index
   * @throws Exception
   */
  @GetMapping("/delete/confirmDelete/{id}")
  public String confirmDeleteShelter(@PathVariable Long id, Model model) throws Exception {
    adopterRepository.deleteObject(id);
    Collection<AdopterModel> adopter = adopterRepository.getAll("");
    model.addAttribute("adopter", adopter);
    return "redirect:/adopter";
  }

  
    public <T extends Enum<T>> List<String> enumToList(Class<T> class1) {
        List<String> list = new ArrayList<>();
        T[] enumConstants = class1.getEnumConstants();
        for (T enumConstant : enumConstants) {
            list.add(enumConstant.name());
        }
        return list;
    }

}
