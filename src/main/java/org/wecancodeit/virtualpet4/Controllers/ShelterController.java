package org.wecancodeit.virtualpet4.Controllers;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.wecancodeit.virtualpet4.Models.ShelterModel;
import org.wecancodeit.virtualpet4.Repositories.ShelterRepository;

import jakarta.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class ShelterController {

 
  private final ShelterRepository shelterRepository;



public ShelterController() {
  shelterRepository = new ShelterRepository("http://localhost:8080/api/v1/shelters/");
  }

@GetMapping()
public String getAllShelters(Model model) throws Exception{
    var shelters = shelterRepository.getAll("");
    model.addAttribute("shelters", shelters);
    return "home/index";
}

@GetMapping("/{id}")
public String getShelter(@PathVariable Long id, Model model) throws Exception{
    ShelterModel shelter = shelterRepository.getById(id);
    model.addAttribute("shelter", shelter);
    return "home/detail";

}

@GetMapping("/edit/{id}")
public String editShelter(@PathVariable Long id, Model model) throws Exception{
    ShelterModel shelter = shelterRepository.getById(id);
    model.addAttribute("shelter", shelter);
    return "home/edit";

}

@PostMapping
public String saveShelter(@ModelAttribute("shelter") ShelterModel model) throws Exception{
  shelterRepository.saveShelter(model);
  return "redirect:/";
}

@GetMapping("/delete/{id}")
public String deleteShelter(@PathVariable Long id, Model model) throws Exception {
  ShelterModel shelter =shelterRepository.getById(id);
  model.addAttribute("shelter", shelter);
  return "home/confirm/Delete";
}

@GetMapping("/delete/confirmDelete/{id}")
public String confirmDeleteShelter(@PathVariable Long id, Model model) throws Exception{
  shelterRepository.deleteById(id);
  Collection <ShelterModel> shelters = shelterRepository.getAll("");
  model.addAttribute("shelter", shelters);
  return "redirect:/";
}

@GetMapping("/about")
public String getAbout (Model model){
  return "home/about";
}

    

}
