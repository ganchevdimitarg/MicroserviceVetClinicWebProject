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

    @GetMapping("/{username}")
    public ResponseEntity<DoctorApiControllerModel> getUserById(@PathVariable String username) {
        DoctorApiControllerModel model = modelMapper.map(doctorService.findByUsername(username), DoctorApiControllerModel.class);

        if (model.getId() != null){
            return ResponseEntity.ok(model);
        }
        return ResponseEntity.notFound().build();
    }
}
