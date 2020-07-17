package d2g.vetclinicwebproject.web.views.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getIndex() {
        return "/index.html";
    }

    @GetMapping("/home")
    public String home() {
        return "/home.html";
    }

    @GetMapping("/about")
    public String getAbout() {
        return "/about.html";
    }

    @GetMapping("/contact")
    public String getContact() {
        return "/contact.html";
    }
}
