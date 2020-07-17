package d2g.vetclinicwebproject.web.api.controllers;

import d2g.vetclinicwebproject.services.services.doctor.DoctorService;
import d2g.vetclinicwebproject.web.api.models.doctor.DoctorApiControllerModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
@AllArgsConstructor
public class DoctorApiController {
    private final ModelMapper modelMapper;
    private final DoctorService doctorService;

    @GetMapping("/{username}/{password}")
    public ResponseEntity<DoctorApiControllerModel> getUserByUsernameAndPassword(@PathVariable String username, @PathVariable String password) {
        DoctorApiControllerModel model = modelMapper.map(doctorService.findByUsernameAndPassword(username, password), DoctorApiControllerModel.class);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<DoctorApiControllerModel> getUserById(@PathVariable String username) {
        return new ResponseEntity<>(modelMapper.map(doctorService.findByUsername(username), DoctorApiControllerModel.class), HttpStatus.OK);
    }
}
