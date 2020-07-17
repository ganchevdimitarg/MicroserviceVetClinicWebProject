package d2g.vetclinicwebproject.services.services.animal;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
//import d2g.vetclinicwebproject.data.models.User;
//import d2g.vetclinicwebproject.data.repositories.AnimalRepository;
//import d2g.vetclinicwebproject.data.repositories.UserRepository;
//import d2g.vetclinicwebproject.errors.AnimalErrorHandlerException;
import d2g.vetclinicwebproject.services.base.TestBase;
//import d2g.vetclinicwebproject.services.models.AnimalServiceModel;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AnimalServiceImplTest extends TestBase {
//    @MockBean
//    AnimalRepository animalRepository;
//    @MockBean
//    UserRepository userRepository;
//
//    @Autowired
//    AnimalService animalService;
//
//    @Test
//    void saveUser_whenAllRequiredDataIsValid_shouldAddAnimal() {
//        AnimalServiceModel animal = new AnimalServiceModel();
//        animal.setBreed("dog");
//        animal.setName("Max");
//        animal.setAge(1);
//
//        String userName = "Dimitar";
//        User user = new User();
//        user.setName(userName);
//        user.setId("1");
//
//        Mockito.when(userRepository.findByUsername(userName)).thenReturn(Optional.of(user));
//
//        assertThrows(RuntimeException.class, () -> animalService.save(animal, user.getName()));
//    }
//
//    @Test
//    void saveUser_whenSameOfRequiredDataIsNotValid_shouldThrowException() {
//        AnimalServiceModel animal = new AnimalServiceModel();
//        animal.setBreed("dog");
//        animal.setName("Max");
//        animal.setAge(1);
//
//        String userName = "Dimitar";
//        User user = new User();
//        user.setName(userName);
//        user.setId("1");
//
//        Mockito.when(userRepository.findByUsername(userName)).thenReturn(Optional.empty());
//
//        assertThrows(AnimalErrorHandlerException.class, () -> animalService.save(animal, user.getName()));
//    }
//
//    @Test
//    void findByName() {
//
//    }
//
//    @Test
//    void getCurrentUserAnimal() {
//    }
}