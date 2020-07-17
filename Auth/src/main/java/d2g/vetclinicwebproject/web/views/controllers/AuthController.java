package d2g.vetclinicwebproject.web.views.controllers;

import d2g.vetclinicwebproject.services.models.UserServiceModel;
import d2g.vetclinicwebproject.services.services.UserEntityService;
import d2g.vetclinicwebproject.web.views.models.UserRegisterModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
public class AuthController {

    private final ModelMapper modelMapper;
    private final UserEntityService userEntityService;

    @GetMapping("/sign-in")
    public String getSignIn() {
        return "auth/sign-in.html";
    }

    @GetMapping("/register")
    public String register() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerConfirm(@RequestBody @ModelAttribute UserRegisterModel model) {
        if (!model.getPassword().equals(model.getConfirmPassword())) {
            return "auth/register";
        }

        this.userEntityService.register(this.modelMapper.map(model, UserServiceModel.class));
        return "redirect:/home";
    }

}