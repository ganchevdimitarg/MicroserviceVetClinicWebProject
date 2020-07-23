package d2g.vetclinicwebproject.services.services.animal;

import d2g.vetclinicwebproject.data.models.Animal;
import d2g.vetclinicwebproject.data.models.User;
import d2g.vetclinicwebproject.data.repositories.AnimalRepository;
import d2g.vetclinicwebproject.data.repositories.UserRepository;
import d2g.vetclinicwebproject.errors.AnimalErrorHandlerException;
import d2g.vetclinicwebproject.errors.UserNotFoundException;
import d2g.vetclinicwebproject.services.models.AnimalServiceModel;
import d2g.vetclinicwebproject.services.models.UserServiceModel;
import d2g.vetclinicwebproject.services.services.TestBase;
import d2g.vetclinicwebproject.services.services.animal.validation.AnimalValidationService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AnimalServiceImplTest extends TestBase {
    private final static String INCORRECTLY_ENTERED_DATA = "Incorrectly entered data! Please try again!";
    private final static String NOT_FOUNT_ANIMAL = "No such animal";
    private final static String NOT_FOUNT_USER = "No such user";

    @Mock
    UserRepository userRepository;
    @Mock
    AnimalRepository animalRepository;
    @Mock
    AnimalValidationService validationService;

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    AnimalService animalService;

    @Test
    void save_whenAnimalInfoIsValid_shouldSaveAnimalInDB() {
        AnimalServiceModel animal = new AnimalServiceModel("1", "dog", "Max", 19.0, "", "none", "none");
        User user = new User("ivan", "1", "Ivan", "ivan@abv.bg", "Varna", "0888888888", new ArrayList<>());
        Mockito.when(validationService.isValidAnimalInfo(animal)).thenReturn(true);
        Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(java.util.Optional.of(user));
        animalService.save(animal, user.getUsername());

        ArgumentCaptor<Animal> argument = ArgumentCaptor.forClass(Animal.class);
        Mockito.verify(animalRepository).saveAndFlush(argument.capture());

        Animal animalInDB = argument.getValue();
        assertNotNull(animalInDB);
    }

    @Test
    void save_whenAnimalInfoIsNotValid_shouldThrowException() {
        Exception exception = assertThrows(AnimalErrorHandlerException.class, () -> {
            AnimalServiceModel animal = new AnimalServiceModel("1", "", "", 19.0, "", "none", "none");
            String username = "stamen";
            animalService.save(animal, username);
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(INCORRECTLY_ENTERED_DATA));
    }

    @Test
    void save_whenUserNotFound_shouldThrowException() {
        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            AnimalServiceModel animal = new AnimalServiceModel("1", "dog", "Max", 19.0, "", "none", "none");
            String username = "stamen";
            animalService.save(animal, username);
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(NOT_FOUNT_USER));

    }

    @Test
    void findByName_whenNameExists_shouldReturnAnimal() {
        Animal animalExpect = new Animal("dog", "Max", 19.0, "Bacterial", "antibiotics",  new User());
        String name = "Max";
        Mockito.when(animalRepository.findByName(name)).thenReturn(java.util.Optional.of(animalExpect));

        Animal animalActual = animalRepository.findByName(name).get();

        assertEquals(animalExpect, animalActual);
    }

    @Test
    void findByName_whenNameNotFound_shouldThrowException() {
        Exception exception = assertThrows(AnimalErrorHandlerException.class, () -> {
            animalService.findByName("ivan");
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(NOT_FOUNT_ANIMAL));
    }

    @Test
    void getCurrentUserAnimal_whenUserExists_shouldReturnListOfAnimals() {
        String username = "ivan";
        User user = new User("ivan",
                "111111",
                "Ivan",
                "ivan@abv.bg",
                "Varna, Republica",
                "0888888888",
                List.of(new Animal("", "Max", 1, "", "", new User()),
                        new Animal("", "Rock", 1, "", "", new User())));

        List<AnimalServiceModel> animals = animalService.getCurrentUserAnimal(username);

        assertEquals(user.getAnimals().get(0).getName(), animals.get(0).getName());
    }

    @Test
    void getCurrentUserAnimal_whenUserNotExists_shouldReturnThrowException() {
        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            String username = "stamen";
            animalService.getCurrentUserAnimal(username);
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(NOT_FOUNT_USER));
    }

    @Test
    void addMedicineDisease_whenMedicineAndDiseaseIsValid_shouldSaveInDB() {
        Animal animalExpect = new Animal("dog", "Max", 19.0, "", "",  new User());
        animalExpect.setId("1");
        Mockito.when(animalRepository.findById("1")).thenReturn(Optional.of(animalExpect));
        animalService.addMedicineDisease("1", "antibiotic", "Bacterial");

        assertEquals(animalExpect.getMedicine(), "antibiotic");
    }

    @Test
    void addMedicineDisease_whenMedicineIsLessLength_shouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            animalService.addMedicineDisease("1", "", "Bacterial");
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(INCORRECTLY_ENTERED_DATA));
    }

    @Test
    void addMedicineDisease_whenMedicinesMoreThanLength_shouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            animalService.addMedicineDisease("1", "antibiotics", "Bacterial");
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(INCORRECTLY_ENTERED_DATA));
    }

    @Test
    void addMedicineDisease_whenDiseaseIsLessLength_shouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            animalService.addMedicineDisease("1", "antibiotic", "");
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(INCORRECTLY_ENTERED_DATA));

    }

    @Test
    void addMedicineDisease_whenDiseaseMoreThanLength_shouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            animalService.addMedicineDisease("1", "antibiotic", "Bacterial infections");
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(INCORRECTLY_ENTERED_DATA));
    }

    @Test
    void deleteAnimal() {
        String animalId = "1";
        Animal animal = new Animal();
        animal.setId(animalId);
        animalService.deleteAnimal(animalId);

        assertNotNull(animal);
    }
}