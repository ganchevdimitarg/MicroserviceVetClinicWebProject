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

    @GetMapping("/{username}/{password}")
    public ResponseEntity<UserApiControllerModel> getUserByUsernameAndPassword(@PathVariable String username, @PathVariable String password) {
        UserServiceModel user = userService.findByUsernameAndPassword(username, password);

        if (user != null) {
            return new ResponseEntity<>(modelMapper.map(user, UserApiControllerModel.class), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserApiControllerModel> getUserByUsername(@PathVariable String username) {
        UserApiControllerModel user = modelMapper.map(userService.findByUsername(username), UserApiControllerModel.class);

        if (user != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/register-user")
    public ResponseEntity<Void> registerUser(@RequestBody UserRegisterApiControllerModel model) {
        userService.registerUser(modelMapper.map(model, UserServiceModel.class));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/update-user-info")
    public ResponseEntity<Void> updateUserInfo(@RequestBody UserUpdateApiControllerModel model) {
        userService.updateUser(modelMapper.map(model, UserServiceModel.class));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/delete-user")
    public ResponseEntity<Void> deleteUser(@RequestBody UserUpdateApiControllerModel model) {
        userService.deleteUser(model.getUsername());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/delete-user/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
