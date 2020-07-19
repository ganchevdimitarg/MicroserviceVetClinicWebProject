package d2g.vetclinicwebproject.web.api.controllers;

import d2g.vetclinicwebproject.services.models.AddAnimalServiceModel;
import d2g.vetclinicwebproject.services.services.AnimalService;
import d2g.vetclinicwebproject.web.api.models.animal.AddAnimalApiControllerModel;
import d2g.vetclinicwebproject.web.api.models.animal.AnimalApiControllerModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user/api")
@AllArgsConstructor
public class AnimalApiController {

    private final static Logger logger = LoggerFactory.getLogger(AnimalApiController.class);

    private static final String ADD_PET_PAGE = "user/add-pet";
    private static final String PET_PAGE = "/user/pet";

    private final ModelMapper modelMapper;
    private final AnimalService animalService;

    @GetMapping("/pet")
    public ResponseEntity<List<AnimalApiControllerModel>> getUserAnimals(@AuthenticationPrincipal Principal principal) {
        List<AnimalApiControllerModel> animals = animalService.getUserAnimals(principal.getName());

        if (animals.size() != 0){
            return ResponseEntity.ok(animals);
        }
        return ResponseEntity.noContent().build();
    }

    @ModelAttribute("addAnimal")
    public AddAnimalApiControllerModel addAnimal() {
        return new AddAnimalApiControllerModel();
    }

    @PostMapping("/add-user-animal")
    public ResponseEntity<AddAnimalServiceModel> addAnimals(@Valid @ModelAttribute("addAnimal") AddAnimalApiControllerModel animal, BindingResult bindingResult, @AuthenticationPrincipal Principal principal) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).header(HttpHeaders.LOCATION, ADD_PET_PAGE).build();
        }

        animalService.addAnimals(modelMapper.map(animal, AddAnimalServiceModel.class), principal.getName());

        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, PET_PAGE).build();
    }

    @PostMapping("/delete-user-animal/{animalId}")
    public ResponseEntity<AddAnimalServiceModel> deleteUser(@PathVariable String animalId, AddAnimalApiControllerModel animal) {
        animalService.deleteUser(animalId, modelMapper.map(animal, AddAnimalServiceModel.class));

        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, PET_PAGE).build();
    }
}
