package d2g.vetclinicwebproject.web.views.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

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
