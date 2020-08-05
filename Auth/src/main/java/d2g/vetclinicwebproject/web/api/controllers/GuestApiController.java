package d2g.vetclinicwebproject.web.api.controllers;

import d2g.vetclinicwebproject.services.models.GuestServiceModel;
import d2g.vetclinicwebproject.services.services.CloudinaryService;
import d2g.vetclinicwebproject.services.services.GuestService;
import d2g.vetclinicwebproject.web.api.models.guest.GuestApiControllerModel;
import d2g.vetclinicwebproject.web.api.models.guest.GuestUpdateApiControllerModel;
import d2g.vetclinicwebproject.web.api.models.guest.GuestViewApiControllerModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/user/api")
@AllArgsConstructor
public class GuestApiController {

    private final static Logger LOGGER = LoggerFactory.getLogger(GuestApiController.class);

    public static final String SIGN_UP_PAGE = "auth/sign-up-user";
    public static final String SIGN_IN_PAGE = "/sign-in";
    public static final String USER_HOME_PAGE = "/home";
    public static final String LOGOUT_PAGE = "/logout";

    private final ModelMapper modelMapper;
    private final GuestService guestService;
    private final CloudinaryService cloudinaryService;

    @GetMapping("/user-home")
    public ResponseEntity<GuestViewApiControllerModel> getUserHome(@AuthenticationPrincipal Principal principal) {
        GuestViewApiControllerModel user = modelMapper.map(guestService.getUserHome(principal.getName()), GuestViewApiControllerModel.class);

        if (user != null){
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.notFound().build();
    }

    @ModelAttribute("registerUser")
    public GuestApiControllerModel registerUser() {
        return new GuestApiControllerModel();
    }

    @PostMapping("/sign-up-user")
    public ResponseEntity<GuestServiceModel> registerUser(@Valid @ModelAttribute("registerUser") GuestApiControllerModel user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, SIGN_UP_PAGE).build();
        }

        guestService.registerUser(modelMapper.map(user, GuestServiceModel.class));

        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, SIGN_IN_PAGE).build();
    }

    @ModelAttribute("registerUser")
    public GuestUpdateApiControllerModel updateUser() {
        return new GuestUpdateApiControllerModel();
    }

    @PostMapping("/update-info")
    public ResponseEntity<GuestServiceModel> update(@AuthenticationPrincipal Principal principal, @Valid @ModelAttribute("updateUser") GuestUpdateApiControllerModel user, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
        user.setUsername(principal.getName());
        GuestServiceModel userModel = modelMapper.map(user, GuestServiceModel.class);
        userModel.setImageUrl(cloudinaryService.upload(user.getImage()));
        guestService.update(userModel);

        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, USER_HOME_PAGE).build();
    }

    @PostMapping("/delete-user")
    public void deleteUser(@AuthenticationPrincipal Principal principal, HttpServletResponse response) throws IOException {
        guestService.deleteUser(principal.getName());

        response.sendRedirect("/logout");
    }
}
