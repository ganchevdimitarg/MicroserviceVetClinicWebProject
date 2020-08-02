package d2g.vetclinicwebproject.services.services.animal;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import d2g.vetclinicwebproject.services.models.AnimalServiceModel;

@Service
@AllArgsConstructor
public class AnimalValidationServiceImpl implements AnimalValidationService {
       @Override
    public boolean isValidAnimalInfo(AnimalServiceModel animal) {
        return isBreedValid(animal) && isNameValid(animal) && isAgeValid(animal);
    }

    public boolean isBreedValid(AnimalServiceModel animal) {
        return !animal.getBreed().isEmpty() && (animal.getBreed().length() >= 3 && animal.getBreed().length() <= 6);
    }

    public boolean isNameValid(AnimalServiceModel animal) {
        return !animal.getName().isEmpty() && (animal.getName().length() >= 3 && animal.getName().length() <= 6);
    }

    public boolean isAgeValid(AnimalServiceModel animal) {
        return animal.getAge() > 0;
    }
}
