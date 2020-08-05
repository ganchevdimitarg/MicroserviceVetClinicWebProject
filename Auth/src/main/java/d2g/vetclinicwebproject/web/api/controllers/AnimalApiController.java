package d2g.vetclinicwebproject.web.api.controllers;

import d2g.vetclinicwebproject.event.UpdateCachePublisher;
import d2g.vetclinicwebproject.services.models.AnimalServiceModel;
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

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user/api")
@AllArgsConstructor
public class AnimalApiController {

    private final static Logger LOGGER = LoggerFactory.getLogger(AnimalApiController.class);

    private static final String ADD_PET_PAGE = "user/add-pet";
    private static final String PET_PAGE = "/user/pet";

    private final ModelMapper modelMapper;
    private final AnimalService animalService;
    private final UpdateCachePublisher publisher;

    @GetMapping("/pet")
    public ResponseEntity<List<AnimalApiControllerModel>> getUserAnimals(@AuthenticationPrincipal Principal principal) {
        List<AnimalServiceModel> animals = animalService.getUserAnimals(principal.getName());
        List<AnimalApiControllerModel> animalsModel = animals.stream()
                .map(animal -> modelMapper.map(animal, AnimalApiControllerModel.class)).collect(Collectors.toList());

        if (animals.size() != 0){
            return ResponseEntity.ok(animalsModel);
        }
        return ResponseEntity.noContent().build();
    }

    @ModelAttribute("addAnimal")
    public AddAnimalApiControllerModel addAnimal() {
        return new AddAnimalApiControllerModel();
    }


    @PostMapping("/add-user-animal")
    public ResponseEntity<AnimalServiceModel> addAnimals(@Valid @ModelAttribute("addAnimal") AddAnimalApiControllerModel animal, BindingResult bindingResult, @AuthenticationPrincipal Principal principal) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).header(HttpHeaders.LOCATION, ADD_PET_PAGE).build();
        }

        animalService.addAnimals(modelMapper.map(animal, AnimalServiceModel.class), principal.getName());

        publisher.publishUpdateCache(principal.getName());

        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, PET_PAGE).build();
    }

    @PostMapping("/delete-user-animal/{animalId}")
    public ResponseEntity<AnimalServiceModel> deleteUser(@PathVariable String animalId, AddAnimalApiControllerModel animal) {
        animalService.deleteUser(animalId, modelMapper.map(animal, AnimalServiceModel.class));

        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, PET_PAGE).build();
    }
}
