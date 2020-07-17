package d2g.vetclinicwebproject.web.views.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class GuestController {
    public static final String USER_HOME_PAGE = "/home.html";
    public static final String ANIMAL_PAGE = "user/pet.html";

    @GetMapping("/user-home")
    public String getUserHome() {
        return USER_HOME_PAGE;
    }

    @GetMapping("/pet")
    public String getAnimals() {
        return ANIMAL_PAGE;
    }
}
