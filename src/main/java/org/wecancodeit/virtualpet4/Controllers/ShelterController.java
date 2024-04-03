package org.wecancodeit.virtualpet4.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wecancodeit.virtualpet4.Repositories.ShelterRepository;

import jakarta.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping({"", "/", "home"})
public class ShelterController {
@Resource
    private final ShelterRepository shelterRepository;

    public ShelterController(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }
    
  @GetMapping
  public String getMethodName() {
      return "home/index";
  }
    
}
