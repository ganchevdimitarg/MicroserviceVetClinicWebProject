package d2g.vetclinicwebproject.web.api.controllers;

import d2g.vetclinicwebproject.errors.UserNotFoundException;
import d2g.vetclinicwebproject.services.models.UserServiceModel;
import d2g.vetclinicwebproject.services.services.user.UserService;
import d2g.vetclinicwebproject.web.api.models.user.UserRegisterApiControllerModel;
import d2g.vetclinicwebproject.web.api.models.user.UserUpdateApiControllerModel;
import d2g.vetclinicwebproject.web.api.models.user.UserApiControllerModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserApiController {
    private final ModelMapper modelMapper;
    private final UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<UserApiControllerModel> getUserByUsername(@PathVariable String username) {
        UserApiControllerModel user = modelMapper.map(userService.findByUsername(username), UserApiControllerModel.class);

        if (user != null){
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/register-user")
    public ResponseEntity<UserServiceModel> registerUser(@RequestBody UserRegisterApiControllerModel model) {
        userService.registerUser(modelMapper.map(model, UserServiceModel.class));

        return ResponseEntity.ok().build();
    }

    @PostMapping("/update-user-info")
    public ResponseEntity<UserServiceModel> updateUserInfo(@RequestBody UserUpdateApiControllerModel model) {
        userService.updateUser(modelMapper.map(model, UserServiceModel.class));

        return ResponseEntity.ok().build();
    }


    @PostMapping("/delete-user/{username}")
    public ResponseEntity<UserServiceModel> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);

        return ResponseEntity.noContent().build();
    }
}
