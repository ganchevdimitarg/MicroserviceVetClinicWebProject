package d2g.vetclinicwebproject.web.views.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getIndex() {
        return "/index";
    }

    @GetMapping("/home")

    public String home() {
        return "/home";
    }

    @GetMapping("/about")
    public String getAbout() {
        return "/about";
    }

    @GetMapping("/contact")
    public String getContact() {
        return "/contact";
    }
}
