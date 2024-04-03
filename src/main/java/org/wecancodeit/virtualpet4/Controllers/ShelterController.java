package org.wecancodeit.virtualpet4.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.wecancodeit.virtualpet4.Repositories.ShelterRepository;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ShelterController {

@GetMapping("/")
public String getMethodName (Model model) throws Exception{
    ShelterRepository shelter = new ShelterRepository("http://localhost:8080/api/v1/shelters/");
    var shelters = shelter.getAll("");
    model.addAttribute("shelters", shelters);
    return "home/index";
}

    

}
