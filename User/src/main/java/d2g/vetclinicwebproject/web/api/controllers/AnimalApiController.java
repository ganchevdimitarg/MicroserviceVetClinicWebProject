package d2g.vetclinicwebproject.web.api.controllers;

import d2g.vetclinicwebproject.services.models.AnimalServiceModel;
import d2g.vetclinicwebproject.services.services.animal.AnimalService;
import d2g.vetclinicwebproject.web.api.models.animal.AddTreatmentControllerModel;
import d2g.vetclinicwebproject.web.api.models.animal.AnimalApiControllerModel;
import d2g.vetclinicwebproject.web.api.models.animal.AddAnimalApiControllerModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class AnimalApiController {
    private final ModelMapper modelMapper;
    private final AnimalService animalService;

    @GetMapping("/animals/{username}")
    public ResponseEntity<List<AnimalApiControllerModel>> getAnimals(@PathVariable String username) {
        List<AnimalApiControllerModel> models = animalService.getCurrentUserAnimal(username)
                .stream()
                .map(s -> {
                    AnimalApiControllerModel animal = new AnimalApiControllerModel();
                    animal.setId(s.getId());
                    animal.setName(s.getName());
                    animal.setBreed(s.getBreed());
                    animal.setAge(s.getAge());
                    setDisease(s, animal);
                    setMedicine(s, animal);
                    return animal;
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(models, HttpStatus.OK);
    }

    @GetMapping("/animal/{animalName}")
    public ResponseEntity<AnimalApiControllerModel> getAnimalByName(@PathVariable String animalName) {
        return new ResponseEntity<>(modelMapper.map(animalService.findByName(animalName),
                AnimalApiControllerModel.class),HttpStatus.OK);
    }

    @PostMapping("/animal/add-animal/{username}")
    public ResponseEntity<Void> addUserAnimal(@RequestBody AddAnimalApiControllerModel model, @PathVariable String username) {
        animalService.save(modelMapper.map(model, AnimalServiceModel.class), username);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/animal/delete-animal/{animalId}")
    public ResponseEntity<Void> deleteUserAnimal(@PathVariable String animalId) {
        animalService.deleteAnimal(animalId);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("/animal/add-treatment/{animalId}")
    public ResponseEntity<Void> addTreatment(@RequestBody AddTreatmentControllerModel model, @PathVariable String animalId){
        animalService.addMedicineDisease(animalId, model.getMedicine(), model.getDisease());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void setMedicine(AnimalServiceModel s, AnimalApiControllerModel animal) {
        animal.setMedicine("-");
        if (s.getMedicine() != null){
            animal.setMedicine(s.getMedicine());
        }
    }

    private void setDisease(AnimalServiceModel s, AnimalApiControllerModel animal) {
        animal.setDisease("-");
        if (s.getDisease() != null){
            animal.setDisease(s.getDisease());
        }
    }
}
