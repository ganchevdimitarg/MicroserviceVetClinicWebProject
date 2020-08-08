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
    public ResponseEntity<List<AnimalServiceModel>> getAnimals(@PathVariable String username) {
        List<AnimalServiceModel> animals = animalService.getCurrentUserAnimal(username)
                .stream()
                .map(s -> {
                    AnimalServiceModel animal = new AnimalServiceModel();
                    animal.setId(s.getId());
                    animal.setName(s.getName());
                    animal.setBreed(s.getBreed());
                    animal.setAge(s.getAge());
                    setDisease(s, animal);
                    setMedicine(s, animal);
                    return animal;
                })
                .collect(Collectors.toList());

        if (animals.size() != 0){
            return ResponseEntity.ok().body(animals);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/animal/{animalName}")
    public ResponseEntity<AnimalApiControllerModel> getAnimalByName(@PathVariable String animalName) {
        AnimalApiControllerModel animal = modelMapper.map(animalService.findByName(animalName), AnimalApiControllerModel.class);

        if (animal != null){
            return ResponseEntity.ok().body(animal);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/animal/add-animal/{username}")
    public ResponseEntity<AddAnimalApiControllerModel> addUserAnimal(@RequestBody AddAnimalApiControllerModel model, @PathVariable String username) {
        animalService.save(modelMapper.map(model, AnimalServiceModel.class), username);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/animal/delete-animal/{animalId}")
    public ResponseEntity<AddAnimalApiControllerModel> deleteUserAnimal(@PathVariable String animalId) {
        animalService.deleteAnimal(animalId);

        return ResponseEntity.ok().build();

    }

    @PostMapping("/animal/add-treatment/{animalId}")
    public ResponseEntity<AddTreatmentControllerModel> addTreatment(@RequestBody AddTreatmentControllerModel model, @PathVariable String animalId){
        animalService.addMedicineDisease(animalId, model.getMedicine(), model.getDisease());

        return ResponseEntity.ok().build();
    }

    private void setMedicine(AnimalServiceModel s, AnimalServiceModel animal) {
        animal.setMedicine("-");
        if (s.getMedicine() != null){
            animal.setMedicine(s.getMedicine());
        }
    }

    private void setDisease(AnimalServiceModel s, AnimalServiceModel animal) {
        animal.setDisease("-");
        if (s.getDisease() != null){
            animal.setDisease(s.getDisease());
        }
    }
}
