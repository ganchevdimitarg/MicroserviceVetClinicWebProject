package d2g.vetclinicwebproject.web.views.controllers;

import d2g.vetclinicwebproject.services.models.UserServiceModel;
import d2g.vetclinicwebproject.services.services.UserEntityService;
import d2g.vetclinicwebproject.services.services.impl.UserEntityServiceImpl;
import d2g.vetclinicwebproject.web.views.models.UserRegisterModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class AuthController {

    private final ModelMapper modelMapper;
    private final UserEntityService userEntityService;

    @GetMapping("/sign-in")
    public String getSignIn() {
        return "auth/sign-in";
    }

    @PostMapping("/login-error")
    public ModelAndView onLoginError(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.
                    SPRING_SECURITY_FORM_USERNAME_KEY) String email
    ) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error", "bad.credentials");
        modelAndView.addObject("username", email);
        modelAndView.setViewName("auth/sign-in");

        return modelAndView;
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
        return "redirect: auth/sign-in";
    }

}