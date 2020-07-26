package d2g.vetclinicwebproject.services.services.animal;

import d2g.vetclinicwebproject.services.TestBase;
import d2g.vetclinicwebproject.services.models.AnimalServiceModel;
import d2g.vetclinicwebproject.services.services.animal.validation.AnimalValidationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class AnimalValidationServiceImplTest extends TestBase {
    @Autowired
    AnimalValidationService validation;

    @Test
    void isValid_whenAllInfoIsValid_shouldReturnTrue() {
        AnimalServiceModel animal = new AnimalServiceModel("1", "dog", "Max", 19.0, "", "none", "none");

        assertTrue(validation.isValidAnimalInfo(animal));
    }

    @Test
    void isValid_whenBreedIsEmpty_shouldReturnFalse() {
        AnimalServiceModel animal = new AnimalServiceModel("1", "", "Max", 19.0, "", "none", "none");

        assertFalse(validation.isValidAnimalInfo(animal));
    }

    @Test
    void isValid_whenBreedIsLessMinLength_shouldReturnFalse() {
        AnimalServiceModel animal = new AnimalServiceModel("1", "aa", "Max", 19.0, "", "none", "none");

        assertFalse(validation.isValidAnimalInfo(animal));
    }

    @Test
    void isValid_whenBreedIsLessMaxLength_shouldReturnFalse() {
        AnimalServiceModel animal = new AnimalServiceModel("1", "aadasdsad", "Max", 19.0, "", "none", "none");

        assertFalse(validation.isValidAnimalInfo(animal));
    }

    @Test
    void isValid_whenNameIsEmpty_shouldReturnFalse() {
        AnimalServiceModel animal = new AnimalServiceModel("1", "dog", "", 19.0, "", "none", "none");

        assertFalse(validation.isValidAnimalInfo(animal));
    }

    @Test
    void isValid_whenNameIsLessMinLength_shouldReturnFalse() {
        AnimalServiceModel animal = new AnimalServiceModel("1", "dog", "Mx", 19.0, "", "none", "none");

        assertFalse(validation.isValidAnimalInfo(animal));
    }

    @Test
    void isValid_whenNameIsLessMaxLength_shouldReturnFalse() {
        AnimalServiceModel animal = new AnimalServiceModel("1", "dog", "Maxsasaas", 19.0, "", "none", "none");

        assertFalse(validation.isValidAnimalInfo(animal));
    }

    @Test
    void isValid_whenAgeIsLessThanZero_shouldReturnFalse() {
        AnimalServiceModel animal = new AnimalServiceModel("1", "dog", "Max", -1, "", "none", "none");

        assertFalse(validation.isValidAnimalInfo(animal));
    }

    @Test
    void isValid_whenAgeIsEqualsToZero_shouldReturnFalse() {
        AnimalServiceModel animal = new AnimalServiceModel("1", "dog", "Max", 0, "", "none", "none");

        assertFalse(validation.isValidAnimalInfo(animal));
    }

    @Test
    void isValidMedicineDisease_whenDiseaseIsLessMaxLength_shouldReturnFalse() {
        AnimalServiceModel animal = new AnimalServiceModel("1", "dog", "Maxsasaas", 19.0, "", "nodadasdasadasdasdadasdne", "none");

        assertFalse(validation.isValidAnimalInfo(animal));
    }

    @Test
    void isValidMedicineDisease_whenMedicineIsLessMaxLength_shouldReturnFalse() {
        AnimalServiceModel animal = new AnimalServiceModel("1", "dog", "Maxsasaas", 19.0, "", "none", "nodadasdasadasdasdadasdne");

        assertFalse(validation.isValidAnimalInfo(animal));
    }
}