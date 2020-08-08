package d2g.vetclinicwebproject.services.services.animal;

import d2g.vetclinicwebproject.data.models.Animal;
import d2g.vetclinicwebproject.data.models.User;
import d2g.vetclinicwebproject.data.repositories.AnimalRepository;
import d2g.vetclinicwebproject.data.repositories.UserRepository;
import d2g.vetclinicwebproject.errors.AnimalErrorHandlerException;
import d2g.vetclinicwebproject.errors.UserNotFoundException;
import d2g.vetclinicwebproject.services.models.AnimalServiceModel;
import d2g.vetclinicwebproject.services.TestBase;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AnimalServiceImplTest extends TestBase {
    private final static String INCORRECTLY_ENTERED_DATA = "Incorrectly entered data! Please try again!";
    private final static String NOT_FOUNT_ANIMAL = "No such animal";
    private final static String NOT_FOUNT_USER = "No such user";

    @MockBean
    UserRepository userRepository;
    @MockBean
    AnimalRepository animalRepository;
    @MockBean
    AnimalValidationService validationService;

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    AnimalService animalService;

    @Test
    void save_whenAnimalInfoIsValid_shouldSaveAnimalInDB() {
        String username = "Dimitar";
        AnimalServiceModel animal = mock(AnimalServiceModel.class);
        User user = mock(User.class);

        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        Mockito.when(!validationService.isValidAnimalInfo(animal)).thenReturn(true);

        animalService.save(animal, username);

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
            AnimalServiceModel animal = mock(AnimalServiceModel.class);
            String username = "stamen";
            Mockito.when(!validationService.isValidAnimalInfo(animal)).thenReturn(true);
            animalService.save(animal, username);
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(NOT_FOUNT_USER));

    }

    @Test
    void findByName_whenNameExists_shouldReturnAnimal() {
        Animal animalExpect = mock(Animal.class);
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
        String username = "ivan@abv.bg";
        User user = new User("ivan@abv.bg",
                "111111",
                "ivan@abv.bg",
                "Varna, Republica",
                "0888888888",
                "",
                List.of(new Animal("", "Max", 1, "", "", new User()),
                        new Animal("", "Rock", 1, "", "", new User())));

        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.ofNullable(user));
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
        Animal animalExpect = new Animal();
        animalExpect.setMedicine("antibiotic");
        animalExpect.setDisease("Bacterial");

        Mockito.when(animalRepository.findById("1")).thenReturn(Optional.of(animalExpect));
        animalService.addMedicineDisease("1", "antibiotic", "Bacterial");

        assertEquals(animalExpect.getMedicine(), "antibiotic");
    }

    @Test
    void addMedicineDisease_whenAnimalNotExist_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Animal animalExpect = new Animal();
            animalExpect.setMedicine("antibiotic");
            animalExpect.setDisease("Bacterial");

            Mockito.when(animalRepository.findById("2")).thenReturn(Optional.of(animalExpect));
            animalService.addMedicineDisease("1", "antibiotic", "Bacterial");
        });
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