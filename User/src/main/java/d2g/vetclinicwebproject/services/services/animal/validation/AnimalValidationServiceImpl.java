package d2g.vetclinicwebproject.services.services.animal.validation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import d2g.vetclinicwebproject.data.repositories.AnimalRepository;
import d2g.vetclinicwebproject.services.models.AnimalServiceModel;

@Service
@AllArgsConstructor
public class AnimalValidationServiceImpl implements AnimalValidationService {
    private final AnimalRepository animalRepository;

    @Override
    public boolean isValid(AnimalServiceModel animal) {
        return isBreedValid(animal) && isNameValid(animal) && isAgeValid(animal);
    }

    public boolean isBreedValid(AnimalServiceModel animalBreed) {
        return !animalBreed.getBreed().isEmpty() && animalBreed.getBreed().length() >= 3 && animalBreed.getBreed().length() <= 6;
    }

    public boolean isNameValid(AnimalServiceModel animalName) {
        return !animalName.getName().isEmpty() && (animalName.getName().length() >= 3 || animalName.getName().length() <= 6);
    }

    public boolean isAgeValid(AnimalServiceModel animalAge) {
        return animalAge.getAge() != 0 || animalAge.getAge() > 0;
    }
}
