package d2g.vetclinicwebproject.web.views.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class AnimalController {

    @GetMapping("/add-animal")
    public String addUserAnimal() {
        return "user/add-pet.html";
    }

}
