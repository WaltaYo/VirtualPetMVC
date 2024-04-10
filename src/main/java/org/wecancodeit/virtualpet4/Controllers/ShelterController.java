package org.wecancodeit.virtualpet4.Controllers;

import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.wecancodeit.virtualpet4.Dto.ShelterDto;
import org.wecancodeit.virtualpet4.Models.ShelterModel;
import org.wecancodeit.virtualpet4.Repositories.ShelterRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ShelterController {

  private final ShelterRepository shelterRepository;

  /**
   * Establish ShelterRepository
   */
  public ShelterController() {
    shelterRepository = new ShelterRepository("http://localhost:8080/api/v1/shelters/");
  }

  /**
   * Method to get all shelters
   * 
   * @param model thymeleaf base model
   * @return webpage showing all shelters
   * @throws Exception
   */
  @GetMapping()
  public String getAllShelters(Model model) throws Exception {
    var shelters = shelterRepository.getAll(""); // establish shelter repository
    model.addAttribute("shelters", shelters);
    return "home/index";
  }

  /**
   * Method to get shelter by id
   * 
   * @param id
   * @param model
   * @return webpage showing details of single shelter
   * @throws Exception
   */
  @GetMapping("/{id}")
  public String getShelter(@PathVariable Long id, Model model) throws Exception {
    ShelterModel shelter = shelterRepository.getById(id);
    model.addAttribute("shelter", shelter);
    return "home/detail";

  }

  /**
   * Method to edit shelter details
   * 
   * @param id    get shelter by id
   * @param model
   * @return webpage with open fields allowing user to change text description
   * @throws Exception
   */
  @GetMapping("/edit/{id}")
  public String editShelter(@PathVariable Long id, Model model) throws Exception {
    ShelterModel shelter = shelterRepository.getById(id);
    ShelterDto dto = new ShelterDto(shelter);
    model.addAttribute("shelter", dto);
    model.addAttribute("title", "Edit Shelter");
    return "home/edit";

  }

  /**
   * Method to save shelter details
   * 
   * @param dto
   * @return
   * @throws Exception
   */
  @PostMapping
  public String saveShelter(@ModelAttribute("shelter") ShelterDto dto) throws Exception {
    ShelterModel shelter = dto.convertToModel();
    shelterRepository.saveShelter(shelter);
    return "redirect:/";
  }

  /**
   * Method to add a new shelter
   * 
   * @param model
   * @return webpage with field to submit details of new shelter
   * @throws Exception
   */
  @GetMapping("create")
  public String createShelter(Model model) throws Exception {
    ShelterDto dto = new ShelterDto();
    model.addAttribute("shelter", dto);
    model.addAttribute("title", "Create Shelter");
    return "home/edit";
  }

  /**
   * Method to delete  shelter by id
   * @param id
   * @param model
   * @return webpage asking for confirmation
   * @throws Exception
   */
  @GetMapping("/delete/{id}")
  public String deleteShelter(@PathVariable Long id, Model model) throws Exception {
    ShelterModel shelter = shelterRepository.getById(id);
    model.addAttribute("shelter", shelter);
    return "home/confirmDelete";
  }

  /**
   * Method to confirm deletetion of shelter by id
   * @param id
   * @param model
   * @return  home/index
   * @throws Exception
   */
  @GetMapping("/delete/confirmDelete/{id}")
  public String confirmDeleteShelter(@PathVariable Long id, Model model) throws Exception {
    shelterRepository.deleteById(id);
    Collection<ShelterModel> shelters = shelterRepository.getAll("");
    model.addAttribute("shelter", shelters);
    return "redirect:/";
  }

  /**
   * Method to view "About me" details
   * @param model
   * @return
   */
  @GetMapping("/about")
  public String getAbout(Model model) {
    return "home/about";
  }

}
