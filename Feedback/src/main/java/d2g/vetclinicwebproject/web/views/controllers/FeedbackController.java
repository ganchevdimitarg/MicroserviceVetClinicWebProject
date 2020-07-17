package d2g.vetclinicwebproject.web.views.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class FeedbackController {

    @GetMapping("/contact")
    public String getContact() {
        return "/contact";
    }
}
