package d2g.vetclinicwebproject.services.services.animal.validation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import d2g.vetclinicwebproject.data.repositories.AnimalRepository;
import d2g.vetclinicwebproject.services.models.AnimalServiceModel;

@Service
@AllArgsConstructor
public class AnimalValidationServiceImpl implements AnimalValidationService {
       @Override
    public boolean isValidAnimalInfo(AnimalServiceModel animal) {
        return isBreedValid(animal) && isNameValid(animal) && isAgeValid(animal);
    }

    @Override
    public boolean isValidMedicineDisease(String medicine, String disease) {
        return isValidMedicine(medicine) && isValidDisease(disease);
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

    private boolean isValidMedicine(String medicine) {
        return medicine.length() >= 3 && medicine.length() <= 10;
    }

    private boolean isValidDisease(String disease) {
        return disease.length() >= 3 && disease.length() <= 10;
    }
}
